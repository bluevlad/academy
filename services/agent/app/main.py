"""Academy CS Agent — FastAPI 진입점.

관련 플랜: Claude-Opus-bluevlad/services/academy/CS_INQUIRY_AI_PLAN.md (Phase B)
"""
from __future__ import annotations

from fastapi import FastAPI

from app.core import ollama_client
from app.endpoints import analyze, classify, feedback, suggest
from app.settings import settings

app = FastAPI(
    title="Academy CS Agent",
    version="0.1.0-phase-b",
    description="CS 문의 분류·유사추천·월간집계·재배정 피드백 agent. 로컬 Ollama 기반.",
)

app.include_router(classify.router)
app.include_router(suggest.router)
app.include_router(analyze.router)
app.include_router(feedback.router)


@app.get("/health")
async def health():
    """컨테이너 기동 확인용. Ollama 는 별도 확인."""
    return {"status": "ok", "service": "academy-agent", "version": app.version}


@app.get("/health/ollama")
async def health_ollama():
    """Ollama 서버 연결 + 설치 모델 확인."""
    return await ollama_client.health()


@app.get("/settings")
async def settings_dump():
    """비밀정보 제외 설정 덤프 (디버깅)."""
    return {
        "classifier_model": settings.classifier_model,
        "embedding_model": settings.embedding_model,
        "categories": settings.categories,
        "suggest_top_k": settings.suggest_top_k,
        "ollama_base_url": settings.ollama_base_url,
        "db_host": settings.db_host,
        "db_name": settings.db_name,
    }
