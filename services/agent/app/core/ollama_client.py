"""Ollama HTTP 클라이언트.

호스트의 Ollama(:11434) 를 `host.docker.internal` 로 호출. Apple M5 Metal 가속
을 활용하려면 컨테이너 안 Ollama 가 아닌 호스트 Ollama 사용이 필수.
"""
from __future__ import annotations

import json
import time
from typing import Any

import httpx

from app.settings import settings


class OllamaError(RuntimeError):
    """Ollama 응답 이상."""


async def generate(
    prompt: str,
    model: str | None = None,
    *,
    format_: str | None = "json",
    temperature: float | None = None,
) -> tuple[str, int]:
    """Ollama /api/generate 호출. 응답 text 와 latency_ms 반환."""
    model = model or settings.classifier_model
    temperature = temperature if temperature is not None else settings.llm_temperature

    payload: dict[str, Any] = {
        "model": model,
        "prompt": prompt,
        "stream": False,
        "options": {"temperature": temperature},
    }
    if format_:
        payload["format"] = format_

    t0 = time.monotonic()
    async with httpx.AsyncClient(timeout=settings.llm_timeout_sec) as client:
        try:
            r = await client.post(f"{settings.ollama_base_url}/api/generate", json=payload)
            r.raise_for_status()
        except httpx.HTTPError as e:
            raise OllamaError(f"generate 호출 실패: {e}") from e

    latency_ms = int((time.monotonic() - t0) * 1000)
    data = r.json()
    if "response" not in data:
        raise OllamaError(f"response 필드 없음: {data}")
    return data["response"], latency_ms


async def embed(text: str, model: str | None = None) -> list[float]:
    """Ollama /api/embeddings 호출. 임베딩 벡터 반환."""
    model = model or settings.embedding_model
    async with httpx.AsyncClient(timeout=settings.embed_timeout_sec) as client:
        try:
            r = await client.post(
                f"{settings.ollama_base_url}/api/embeddings",
                json={"model": model, "prompt": text},
            )
            r.raise_for_status()
        except httpx.HTTPError as e:
            raise OllamaError(f"embed 호출 실패: {e}") from e

    data = r.json()
    if "embedding" not in data:
        raise OllamaError(f"embedding 필드 없음: {data}")
    return data["embedding"]


async def health() -> dict[str, Any]:
    """Ollama 서버 기동 + 설치 모델 목록."""
    async with httpx.AsyncClient(timeout=5) as client:
        try:
            r = await client.get(f"{settings.ollama_base_url}/api/tags")
            r.raise_for_status()
            tags = r.json().get("models", [])
            names = [m.get("name") for m in tags]
            return {"ok": True, "models": names}
        except Exception as e:
            return {"ok": False, "error": str(e)}


def parse_json_response(raw: str) -> dict:
    """LLM 출력이 JSON 형식이 아닐 때 최선 복구."""
    raw = raw.strip()
    try:
        return json.loads(raw)
    except json.JSONDecodeError:
        # 첫 { 부터 마지막 } 까지 추출
        start = raw.find("{")
        end = raw.rfind("}")
        if start >= 0 and end > start:
            try:
                return json.loads(raw[start : end + 1])
            except json.JSONDecodeError:
                pass
    raise OllamaError(f"JSON 파싱 실패: {raw[:200]}")
