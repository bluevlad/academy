"""POST /classify — 문의 1건 분류."""
from __future__ import annotations

from fastapi import APIRouter
from pydantic import BaseModel, Field

from app.core.classifier import classify_inquiry

router = APIRouter(prefix="/classify", tags=["classify"])


class ClassifyRequest(BaseModel):
    title: str = Field(..., description="문의 제목")
    body: str = Field(..., description="문의 본문 (HTML·텍스트 허용)")
    cs_seq: int | None = Field(None, description="참조용 — 분류 결과 저장시 backend 가 사용")


class ClassifyResponse(BaseModel):
    category: str
    confidence: float
    reasoning: str
    model: str
    latency_ms: int
    used_fallback: bool


@router.post("", response_model=ClassifyResponse)
async def classify(req: ClassifyRequest) -> ClassifyResponse:
    result = await classify_inquiry(req.title, req.body)
    return ClassifyResponse(
        category=result.category,
        confidence=round(result.confidence, 4),
        reasoning=result.reasoning,
        model=result.model,
        latency_ms=result.latency_ms,
        used_fallback=result.used_fallback,
    )
