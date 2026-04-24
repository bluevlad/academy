"""acm_board_cs 의 inquiry_title+inquiry_body 를 임베딩해 acm_inquiry_embedding 에 저장.

실행:
    cd services/agent
    source .venv/bin/activate
    python scripts/build_embeddings.py --limit 100 --dry-run
    python scripts/build_embeddings.py
"""
from __future__ import annotations

import asyncio
import struct
import sys
from pathlib import Path

import click

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from app.core.db import connection          # noqa: E402
from app.core.ollama_client import embed    # noqa: E402
from app.settings import settings           # noqa: E402


@click.command()
@click.option("--limit", type=int, default=0)
@click.option("--dry-run", is_flag=True)
@click.option("--force", is_flag=True, help="이미 있는 cs_seq 도 재생성")
def main(limit: int, dry_run: bool, force: bool) -> None:
    where = "" if force else """
        WHERE b.is_deleted = 'N'
          AND NOT EXISTS (
              SELECT 1 FROM acm_inquiry_embedding e
              WHERE e.cs_seq = b.cs_seq AND e.model_name = %s
          )
    """
    params: tuple = () if force else (settings.embedding_model,)
    limit_clause = f" LIMIT {limit}" if limit > 0 else ""
    sql = f"""
        SELECT b.cs_seq, b.inquiry_title, b.inquiry_body
        FROM acm_board_cs b
        {where}
        ORDER BY b.cs_seq
        {limit_clause}
    """

    with connection() as conn:
        with conn.cursor(dictionary=True) as c:
            c.execute(sql, params)
            rows = c.fetchall()

    click.echo(f"대상: {len(rows)} 건  model={settings.embedding_model}  dry_run={dry_run}")
    if not rows:
        return

    async def run():
        with connection() as conn:
            for i, row in enumerate(rows, 1):
                cs_seq = row["cs_seq"]
                text = (row.get("inquiry_title") or "") + "\n\n" + (row.get("inquiry_body") or "")
                text = text[:8000]
                try:
                    vec = await embed(text)
                except Exception as e:
                    click.echo(f"[{cs_seq}] embed error: {e}", err=True)
                    continue

                if i % 50 == 0:
                    click.echo(f"[{i}/{len(rows)}] cs_seq={cs_seq} dim={len(vec)}")

                if dry_run:
                    continue

                blob = struct.pack(f"<{len(vec)}f", *vec)
                with conn.cursor() as c:
                    c.execute("""
                        REPLACE INTO acm_inquiry_embedding (cs_seq, model_name, dim, embedding)
                        VALUES (%s, %s, %s, %s)
                    """, (cs_seq, settings.embedding_model, len(vec), blob))
                conn.commit()

    asyncio.run(run())
    click.echo("완료")


if __name__ == "__main__":
    main()
