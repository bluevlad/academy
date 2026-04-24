"""문의 분류 로직 — Ollama 기반 + 룰베이스 fallback."""
from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path

from app.core.ollama_client import OllamaError, generate, parse_json_response
from app.settings import settings

PROMPT_PATH = Path(__file__).parent.parent / "prompts" / "classify_v1.txt"
PROMPT_TEMPLATE = PROMPT_PATH.read_text(encoding="utf-8")


# 룰베이스 fallback 키워드 (LLM 실패/OTHER 저신뢰 시 보조)
_RULES: dict[str, list[str]] = {
    "ORDER": ["환불", "결제", "취소", "수강기간", "배송", "쿠폰", "포인트", "입금", "카드"],
    "SYSTEM": ["로그인", "비밀번호", "오류", "에러", "재생", "접속", "멈춰", "안 나와", "안나와", "재생안", "끊김"],
    "ACADEMIC": ["강의", "교재", "진도", "커리큘럼", "강사", "교수", "수업", "문제집", "출결"],
}


@dataclass
class ClassifyResult:
    category: str
    confidence: float
    reasoning: str
    model: str
    latency_ms: int
    raw_output: str
    used_fallback: bool


def _rule_based(title: str, body: str) -> tuple[str, float] | None:
    """간단 키워드 매칭. 매치된 카테고리 중 최다 득점. 0건이면 None."""
    text = f"{title} {body}"
    scores: dict[str, int] = {}
    for cat, kws in _RULES.items():
        scores[cat] = sum(1 for kw in kws if kw in text)
    best = max(scores, key=scores.get)
    if scores[best] == 0:
        return None
    # 후보수로 신뢰도 추정 (1=0.55 / 2=0.65 / 3+=0.75)
    conf = min(0.55 + 0.1 * (scores[best] - 1), 0.75)
    return (best, conf)


async def classify_inquiry(title: str, body: str) -> ClassifyResult:
    """제목+본문을 ACADEMIC/ORDER/SYSTEM/OTHER 중 하나로 분류.

    1. qwen2.5:7b 호출 (JSON 모드)
    2. 파싱 실패/카테고리 이상 → rule fallback
    3. OTHER 이면서 rule match 있으면 rule 우선 (LLM 의 OTHER 는 분류 포기)
    """
    # 본문 너무 길면 4000자로 절단 (한글 tokenizer 기준 약 2000 토큰)
    body_short = (body or "")[:4000]
    prompt = PROMPT_TEMPLATE.format(title=title or "(제목없음)", body=body_short)

    raw = ""
    latency = 0
    parsed: dict | None = None
    try:
        raw, latency = await generate(prompt, format_="json")
        parsed = parse_json_response(raw)
    except OllamaError:
        parsed = None

    llm_category: str | None = None
    llm_confidence = 0.0
    llm_reasoning = ""
    if parsed:
        cat = str(parsed.get("category", "")).upper().strip()
        if cat in settings.categories:
            llm_category = cat
            try:
                llm_confidence = float(parsed.get("confidence", 0.0))
            except (TypeError, ValueError):
                llm_confidence = 0.0
            llm_reasoning = str(parsed.get("reasoning", ""))[:100]

    rule = _rule_based(title, body_short)

    # 최종 결정 로직
    if llm_category and llm_category != "OTHER" and llm_confidence >= 0.6:
        return ClassifyResult(
            category=llm_category,
            confidence=llm_confidence,
            reasoning=llm_reasoning,
            model=settings.classifier_model,
            latency_ms=latency,
            raw_output=raw,
            used_fallback=False,
        )

    if rule:
        rule_cat, rule_conf = rule
        # LLM 이 OTHER 거나 저신뢰일 때 rule 채택
        return ClassifyResult(
            category=rule_cat,
            confidence=rule_conf,
            reasoning=f"rule: {rule_cat} 키워드 매치",
            model=f"rule+{settings.classifier_model}",
            latency_ms=latency,
            raw_output=raw,
            used_fallback=True,
        )

    # 둘 다 실패 → OTHER 반환
    return ClassifyResult(
        category=llm_category or "OTHER",
        confidence=max(llm_confidence, 0.3),
        reasoning=llm_reasoning or "rule·LLM 모두 분류 불가",
        model=settings.classifier_model,
        latency_ms=latency,
        raw_output=raw,
        used_fallback=True,
    )
