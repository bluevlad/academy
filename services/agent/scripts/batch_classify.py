"""acm_board_cs 의 미분류 행을 배치로 classify → predicted_category 채움.

실행 방법:
    cd services/agent
    source .venv/bin/activate  # or venv 생성 후
    export DB_PASSWORD=... OLLAMA_BASE_URL=http://localhost:11434
    python scripts/batch_classify.py --limit 100 --dry-run
    python scripts/batch_classify.py          # 전체 (몇 시간 걸릴 수 있음)

사용 패턴:
    --limit N    처음 N 건만
    --dry-run    DB 쓰기 없이 결과 출력
    --only-new   predicted_category IS NULL 인 행만 (기본값)
    --force      이미 분류된 행도 재분류
"""
from __future__ import annotations

import asyncio
import sys
from datetime import datetime
from pathlib import Path

import click

# app.* 을 import 하기 위한 경로 보정
sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from app.core.classifier import classify_inquiry  # noqa: E402
from app.core.db import connection                # noqa: E402
from app.settings import settings                 # noqa: E402


@click.command()
@click.option("--limit", type=int, default=0, help="처리 건수 제한 (0=전체)")
@click.option("--dry-run", is_flag=True, help="DB 쓰기 없이 결과만 출력")
@click.option("--force", is_flag=True, help="이미 분류된 행도 재분류")
def main(limit: int, dry_run: bool, force: bool) -> None:
    where = "" if force else "WHERE predicted_category IS NULL AND is_deleted = 'N'"
    limit_clause = f" LIMIT {limit}" if limit > 0 else ""
    sql_select = f"""
        SELECT cs_seq, inquiry_title, inquiry_body, actual_category
        FROM acm_board_cs {where}
        ORDER BY cs_seq
        {limit_clause}
    """

    with connection() as conn:
        with conn.cursor(dictionary=True) as c:
            c.execute(sql_select)
            rows = c.fetchall()

    click.echo(f"대상: {len(rows)} 건  model={settings.classifier_model}  dry_run={dry_run}")
    if not rows:
        return

    correct = 0
    total = 0
    errors = 0

    async def run():
        nonlocal correct, total, errors
        with connection() as conn:
            for row in rows:
                cs_seq = row["cs_seq"]
                try:
                    result = await classify_inquiry(
                        row.get("inquiry_title") or "",
                        row.get("inquiry_body") or "",
                    )
                except Exception as e:
                    errors += 1
                    click.echo(f"[{cs_seq}] ERROR: {e}", err=True)
                    continue

                total += 1
                actual = row.get("actual_category")
                if actual and actual == result.category:
                    correct += 1

                if total % 50 == 0:
                    click.echo(
                        f"[{total}/{len(rows)}] cs_seq={cs_seq} "
                        f"pred={result.category} ({result.confidence:.2f}) "
                        f"actual={actual or '-'} lat={result.latency_ms}ms"
                    )

                if dry_run:
                    continue

                now = datetime.now()
                with conn.cursor() as c:
                    c.execute("""
                        UPDATE acm_board_cs
                           SET predicted_category = %s,
                               predicted_confidence = %s,
                               classified_by_model = %s,
                               classified_at = %s
                         WHERE cs_seq = %s
                    """, (result.category, result.confidence, result.model, now, cs_seq))
                    c.execute("""
                        INSERT INTO acm_inquiry_analysis (
                            cs_seq, model_name, prompt_template, raw_output,
                            parsed_category, confidence, latency_ms
                        ) VALUES (%s,%s,%s,%s,%s,%s,%s)
                    """, (cs_seq, result.model, "v1",
                          result.raw_output[:8000] if result.raw_output else None,
                          result.category, result.confidence, result.latency_ms))
                conn.commit()

    asyncio.run(run())

    click.echo("")
    click.echo(f"완료: total={total}  errors={errors}")
    if correct > 0:
        click.echo(f"Ground Truth 대비 정확도: {correct}/{total} = {correct / total:.2%}")


if __name__ == "__main__":
    main()
