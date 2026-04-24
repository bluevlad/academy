"""본문 텍스트 내 PII 정규식 스크러버.

원본 본문 (tb_board_cs.CONTENT) 에 노출된:
- 휴대폰 / 일반 전화 → 010-XXXX-XXXX
- 주민등록번호 → XXXXXX-XXXXXXX
- 이메일 → [email]
- 이름 (3음절 한글) — 강사·직원 화이트리스트 제외

HTML 태그는 보존 (원본이 에디터 HTML 이므로). 분석 단계에서 태그 스트립 가능.
"""
from __future__ import annotations

import re
from typing import Iterable


# --- 정규식 ---------------------------------------------------------------
_RE_PHONE = re.compile(r"\b01[016789][- ]?\d{3,4}[- ]?\d{4}\b")
_RE_LAND = re.compile(r"\b0[2-9]\d?[- ]?\d{3,4}[- ]?\d{4}\b")
_RE_RRN = re.compile(r"\b\d{6}[- ]?[1-4]\d{6}\b")
_RE_EMAIL = re.compile(r"[\w\.-]+@[\w\.-]+\.\w{2,}", re.IGNORECASE)
# 3음절 한글 이름 후보 (보수적, 추정)
_RE_KOR_NAME = re.compile(r"(?<![가-힣])[가-힣]{2,4}(?![가-힣])")


def scrub(
    text: str | bytes | None,
    *,
    name_whitelist: Iterable[str] = (),
) -> str:
    """본문 내 PII 를 치환한 텍스트 반환.

    - bytes (longblob) 은 UTF-8 디코딩 시도. cp949 fallback.
    - 이름 치환은 화이트리스트 제외만 적용 (너무 적극적이면 과치환).
    - HTML 태그 보존.
    """
    if text is None:
        return ""

    if isinstance(text, bytes):
        for enc in ("utf-8", "cp949", "euc-kr", "latin-1"):
            try:
                text = text.decode(enc)
                break
            except UnicodeDecodeError:
                continue
        else:
            text = text.decode("utf-8", errors="replace")

    if not isinstance(text, str):
        text = str(text)

    text = _RE_RRN.sub("XXXXXX-XXXXXXX", text)
    text = _RE_PHONE.sub("010-XXXX-XXXX", text)
    text = _RE_LAND.sub("0X-XXXX-XXXX", text)
    text = _RE_EMAIL.sub("[email]", text)

    wl = {n.strip() for n in name_whitelist if n and n.strip()}
    if wl:
        # 화이트리스트 외 3-4음절 한글 이름 의심 단어는 [이름] 으로
        def _repl(m: re.Match[str]) -> str:
            token = m.group(0)
            if token in wl:
                return token
            if len(token) < 2 or len(token) > 4:
                return token
            # 성씨 1글자 + 이름 2-3글자 구성인지 보수적 판단 (과치환 방지)
            if not _is_likely_korean_name(token):
                return token
            return "[이름]"

        text = _RE_KOR_NAME.sub(_repl, text)

    return text


# 한국 성씨 상위 빈도 (보수적)
_COMMON_SURNAMES = {
    "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임",
    "한", "오", "서", "신", "권", "황", "안", "송", "류", "홍",
}


def _is_likely_korean_name(token: str) -> bool:
    """2-4음절 한글, 첫글자가 상위 성씨인 경우만 이름 후보로."""
    if not (2 <= len(token) <= 4):
        return False
    return token[0] in _COMMON_SURNAMES
