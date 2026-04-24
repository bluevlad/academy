"""POST /route-feedback — 담당자 재배정 피드백 기록.

Phase B 에선 로그만. Phase E 에서 주간 few-shot 갱신 트리거 연결.
"""
from __future__ import annotations

from fastapi import APIRouter
from pydantic import BaseModel

from app.core.db import connection

router = APIRouter(prefix="/route-feedback", tags=["feedback"])


class FeedbackRequest(BaseModel):
    cs_seq: int
    from_category: str | None = None
    to_category: str
    from_user: str | None = None
    to_user: str
    reason: str | None = None
    changed_by: str
    is_ai_error: bool = False


class FeedbackResponse(BaseModel):
    log_seq: int
    learning_queued: bool


@router.post("", response_model=FeedbackResponse)
async def feedback(req: FeedbackRequest) -> FeedbackResponse:
    with connection() as conn:
        with conn.cursor() as c:
            c.execute("""
                INSERT INTO acm_inquiry_routing_log (
                    cs_seq, from_category, to_category, from_user, to_user,
                    reason, changed_by, is_ai_error
                ) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)
            """, (
                req.cs_seq, req.from_category, req.to_category,
                req.from_user, req.to_user, req.reason, req.changed_by,
                "Y" if req.is_ai_error else "N",
            ))
            log_seq = c.lastrowid

            # cs row 의 actual_category / assigned_to / reroute_count 업데이트
            c.execute("""
                UPDATE acm_board_cs
                   SET actual_category = %s,
                       assigned_to = %s,
                       reroute_count = reroute_count + 1,
                       upd_dt = CURRENT_TIMESTAMP
                 WHERE cs_seq = %s
            """, (req.to_category, req.to_user, req.cs_seq))
        conn.commit()

    return FeedbackResponse(log_seq=log_seq, learning_queued=bool(req.is_ai_error))
