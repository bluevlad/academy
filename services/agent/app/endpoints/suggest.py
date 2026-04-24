"""POST /suggest-related — 유사 문의 top-K 추천.

임베딩 저장소: acm_inquiry_embedding 테이블 (BLOB float32 array).
주 저장소가 ChromaDB 로 확장 시 이 모듈만 교체.
"""
from __future__ import annotations

import struct

import numpy as np
from fastapi import APIRouter
from pydantic import BaseModel

from app.core.db import connection
from app.core.ollama_client import embed
from app.settings import settings

router = APIRouter(prefix="/suggest-related", tags=["suggest"])


class SuggestRequest(BaseModel):
    draft_body: str
    user_id: str | None = None
    top_k: int | None = None


class RelatedItem(BaseModel):
    cs_seq: int
    title: str
    answer_excerpt: str | None
    similarity: float
    category: str | None


class SuggestResponse(BaseModel):
    items: list[RelatedItem]
    query_embedding_dim: int
    model: str


def _decode(blob: bytes, dim: int) -> np.ndarray:
    return np.frombuffer(blob, dtype="<f4", count=dim)


def _cosine(a: np.ndarray, b: np.ndarray) -> float:
    na, nb = np.linalg.norm(a), np.linalg.norm(b)
    if na == 0 or nb == 0:
        return 0.0
    return float(np.dot(a, b) / (na * nb))


@router.post("", response_model=SuggestResponse)
async def suggest(req: SuggestRequest) -> SuggestResponse:
    top_k = req.top_k or settings.suggest_top_k

    q_vec = np.asarray(await embed(req.draft_body), dtype="float32")

    # 전체 임베딩 로드 (5,430 건 x ~768 dim ≈ 16MB). 대용량화 시 ChromaDB 로 교체.
    rows: list[tuple[int, str, str | None, str | None, bytes, int]] = []
    with connection() as conn:
        with conn.cursor() as c:
            c.execute("""
                SELECT e.cs_seq, b.inquiry_title, b.answer_body, b.actual_category,
                       e.embedding, e.dim
                FROM acm_inquiry_embedding e
                JOIN acm_board_cs b ON b.cs_seq = e.cs_seq
                WHERE e.model_name = %s
                  AND b.is_deleted = 'N'
            """, (settings.embedding_model,))
            rows = c.fetchall()

    if not rows:
        return SuggestResponse(items=[], query_embedding_dim=int(q_vec.shape[0]),
                               model=settings.embedding_model)

    scored: list[tuple[float, int, str, str | None, str | None]] = []
    for cs_seq, title, answer, category, blob, dim in rows:
        vec = _decode(blob, dim)
        if vec.shape != q_vec.shape:
            continue
        sim = _cosine(q_vec, vec)
        if sim >= settings.suggest_min_similarity:
            scored.append((sim, cs_seq, title, answer, category))

    scored.sort(reverse=True)
    items = [
        RelatedItem(
            cs_seq=cs_seq,
            title=title,
            answer_excerpt=(answer[:200] + "…") if answer and len(answer) > 200 else answer,
            similarity=round(sim, 4),
            category=category,
        )
        for sim, cs_seq, title, answer, category in scored[:top_k]
    ]

    return SuggestResponse(
        items=items,
        query_embedding_dim=int(q_vec.shape[0]),
        model=settings.embedding_model,
    )


def encode_embedding(vec: list[float]) -> bytes:
    """배치 스크립트에서 쓰는 인코더 (float32 little-endian)."""
    return struct.pack(f"<{len(vec)}f", *vec)
