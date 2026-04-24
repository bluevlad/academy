"""POST /analyze-monthly — 월간 카테고리 트렌드 집계.

Phase B 에선 SQL 집계만. Phase D 에서 exaone3.5:7.8b 로 자연어 인사이트 추가.
"""
from __future__ import annotations

from fastapi import APIRouter
from pydantic import BaseModel

from app.core.db import connection

router = APIRouter(prefix="/analyze-monthly", tags=["analyze"])


class AnalyzeRequest(BaseModel):
    year_month: str  # YYYY-MM


class CategoryTrend(BaseModel):
    category: str
    total_count: int
    resolved_count: int
    mom_delta_pct: float | None
    is_decreasing: bool


class AnalyzeResponse(BaseModel):
    year_month: str
    category_trends: list[CategoryTrend]
    total_inquiries: int
    overall_resolution_rate: float | None


@router.post("", response_model=AnalyzeResponse)
async def analyze(req: AnalyzeRequest) -> AnalyzeResponse:
    ym = req.year_month
    with connection() as conn:
        with conn.cursor(dictionary=True) as c:
            c.execute("""
                SELECT COALESCE(actual_category, predicted_category, 'OTHER') AS category,
                       COUNT(*) AS total_count,
                       SUM(CASE WHEN resolution_state IN ('RESOLVED','ANSWERED')
                                 THEN 1 ELSE 0 END) AS resolved_count
                FROM acm_board_cs
                WHERE DATE_FORMAT(inquiry_date, '%Y-%m') = %s
                  AND is_deleted = 'N'
                GROUP BY category
            """, (ym,))
            rows = c.fetchall()

            # 전월 집계로 delta 계산
            from datetime import datetime
            y, m = map(int, ym.split("-"))
            prev_m = 12 if m == 1 else m - 1
            prev_y = y - 1 if m == 1 else y
            prev_ym = f"{prev_y:04d}-{prev_m:02d}"

            c.execute("""
                SELECT COALESCE(actual_category, predicted_category, 'OTHER') AS category,
                       COUNT(*) AS total_count
                FROM acm_board_cs
                WHERE DATE_FORMAT(inquiry_date, '%Y-%m') = %s
                  AND is_deleted = 'N'
                GROUP BY category
            """, (prev_ym,))
            prev = {r["category"]: r["total_count"] for r in c.fetchall()}

    trends: list[CategoryTrend] = []
    total = 0
    resolved = 0
    for row in rows:
        cat = row["category"]
        cnt = int(row["total_count"])
        res = int(row["resolved_count"] or 0)
        total += cnt
        resolved += res
        prev_cnt = prev.get(cat, 0)
        delta: float | None = None
        if prev_cnt > 0:
            delta = round(((cnt - prev_cnt) / prev_cnt) * 100, 2)
        trends.append(CategoryTrend(
            category=cat,
            total_count=cnt,
            resolved_count=res,
            mom_delta_pct=delta,
            is_decreasing=(delta is not None and delta < 0),
        ))

    res_rate = round(resolved / total, 4) if total > 0 else None
    return AnalyzeResponse(
        year_month=ym,
        category_trends=trends,
        total_inquiries=total,
        overall_resolution_rate=res_rate,
    )
