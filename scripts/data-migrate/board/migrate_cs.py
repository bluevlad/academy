"""tb_board_cs → acm_board_cs 이관 스크립트.

사용법:
    cp .env.example .env
    # .env 에 DB 접속·DATA_MIGRATE_ENV=dev 설정
    pip install -r requirements.txt
    python migrate_cs.py --dry-run          # 100건만 변환해 결과 미리보기 (DB 쓰기 없음)
    python migrate_cs.py                     # 전체 이관
    python migrate_cs.py --limit 500         # 처음 500건만
    python migrate_cs.py --only-new          # 이미 이관된 legacy_board_seq 제외

환경 가드:
    DATA_MIGRATE_ENV 가 'dev' 또는 'staging' 이어야 실행. 'prod' 면 즉시 abort.
    타겟 DB 가 localhost 또는 명시적 staging host 가 아니면 확인 프롬프트.
"""
from __future__ import annotations

import os
import sys
import json
from datetime import datetime, date
from pathlib import Path

import click
import mariadb

from pii_mapping import PiiMapper
from content_scrubber import scrub


ALLOWED_ENVS = {"dev", "staging"}
HERE = Path(__file__).parent


# --- 환경 가드 ------------------------------------------------------------
def load_env() -> dict:
    """.env 파일 + os.environ 머지."""
    env = dict(os.environ)
    env_file = HERE / ".env"
    if env_file.exists():
        for line in env_file.read_text(encoding="utf-8").splitlines():
            line = line.strip()
            if not line or line.startswith("#"):
                continue
            if "=" not in line:
                continue
            k, v = line.split("=", 1)
            env.setdefault(k.strip(), v.strip())
    return env


def assert_safe_env(env: dict) -> None:
    mig_env = env.get("DATA_MIGRATE_ENV", "").lower()
    if mig_env not in ALLOWED_ENVS:
        click.echo(
            click.style(
                f"[!] DATA_MIGRATE_ENV='{mig_env}' 는 허용 안됨. dev 또는 staging 만.",
                fg="red",
                bold=True,
            ),
            err=True,
        )
        sys.exit(2)

    target_host = env.get("TARGET_DB_HOST", "")
    if target_host not in ("localhost", "127.0.0.1") and "staging" not in target_host:
        click.echo(
            click.style(
                f"[!] TARGET_DB_HOST='{target_host}' 가 명시 staging/localhost 가 아님. "
                "--force-non-local 없이는 실행 불가.",
                fg="red",
                bold=True,
            ),
            err=True,
        )
        sys.exit(2)


# --- DB 커넥션 ------------------------------------------------------------
def connect(env: dict, *, role: str) -> mariadb.Connection:
    prefix = "SOURCE_DB" if role == "source" else "TARGET_DB"
    return mariadb.connect(
        host=env[f"{prefix}_HOST"],
        port=int(env.get(f"{prefix}_PORT", 3306)),
        user=env[f"{prefix}_USER"],
        password=env[f"{prefix}_PASSWORD"],
        database=env[f"{prefix}_NAME"],
        autocommit=False,
    )


# --- 변환 ---------------------------------------------------------------
def to_datetime(d) -> datetime | None:
    if d is None:
        return None
    if isinstance(d, datetime):
        return d
    if isinstance(d, date):
        return datetime(d.year, d.month, d.day)
    try:
        return datetime.fromisoformat(str(d))
    except Exception:
        return None


def transform_row(row: dict, mapper: PiiMapper) -> dict:
    """legacy row → acm_board_cs 삽입용 dict."""
    masked_id, masked_name = mapper.mask(row.get("REG_ID"))
    # 화이트리스트는 필요시 env 로 주입 (강사 실명 보존 등)
    name_wl: list[str] = []

    title = (row.get("SUBJECT") or "").strip() or "(제목없음)"
    body = scrub(row.get("CONTENT"), name_whitelist=name_wl)
    answer = scrub(row.get("ANSWER"), name_whitelist=name_wl)

    inquiry_date = to_datetime(row.get("REG_DT")) or datetime.now()

    # AI 분류는 Phase B 에서 채움. actual_category 는 legacy CS_DIV 에서 힌트
    actual_category = {
        "CSCOUNSEL": "ACADEMIC",  # 상담성 문의는 학사 계열로 가정 (운영자 재배정 유도)
        "CSREFUND": "ORDER",      # 환불 문의
    }.get((row.get("CS_DIV") or "").upper(), None)

    counselor = row.get("COUNSELOR_ID")
    answered_by = None
    if counselor:
        # 담당자도 마스킹 (직원 프라이버시)
        answered_by, _ = mapper.mask(counselor)

    return {
        "legacy_board_seq": (row.get("BOARD_SEQ") or "")[:20] or None,
        "legacy_cs_div": (row.get("CS_DIV") or "")[:20] or None,
        "legacy_cs_kind": (row.get("CS_KIND") or "")[:20] or None,
        "inquiry_user_id": masked_id,
        "inquiry_name": masked_name,
        "inquiry_title": title[:300],
        "inquiry_body": body,
        "inquiry_date": inquiry_date,
        "actual_category": actual_category,
        "assigned_to": answered_by,
        "answer_body": answer or None,
        "answered_by": answered_by,
        "answered_at": inquiry_date if answer else None,
        "resolution_state": "ANSWERED" if answer else "OPEN",
    }


# --- 메인 ---------------------------------------------------------------
@click.command()
@click.option("--limit", type=int, default=0, help="처리 건수 제한 (0=전체)")
@click.option("--batch", type=int, default=None, help="배치 크기 (기본 .env)")
@click.option("--dry-run", is_flag=True, help="DB 쓰기 없이 변환만 (처음 5건 출력)")
@click.option("--only-new", is_flag=True, help="이미 이관된 legacy_board_seq 는 건너뜀")
def main(limit: int, batch: int | None, dry_run: bool, only_new: bool) -> None:
    env = load_env()
    assert_safe_env(env)

    batch_size = batch or int(env.get("BATCH_SIZE", 500))

    cache_path = Path(env.get("ID_MAP_CACHE_PATH", "./id_map.cache.json"))
    mapper = PiiMapper(
        cache_path=cache_path,
        faker_locale=env.get("FAKER_LOCALE", "ko_KR"),
        faker_seed=int(env.get("FAKER_SEED", 42)),
    )

    click.echo(f"DATA_MIGRATE_ENV={env.get('DATA_MIGRATE_ENV')}")
    click.echo(
        f"source={env['SOURCE_DB_HOST']}:{env['SOURCE_DB_PORT']}/"
        f"{env['SOURCE_DB_NAME']} → target={env['TARGET_DB_HOST']}:"
        f"{env['TARGET_DB_PORT']}/{env['TARGET_DB_NAME']}"
    )
    click.echo(f"dry_run={dry_run}  limit={limit or 'ALL'}  batch={batch_size}")

    src = connect(env, role="source")
    tgt = None if dry_run else connect(env, role="target")

    existing: set[str] = set()
    if only_new and tgt is not None:
        with tgt.cursor() as c:
            c.execute("SELECT legacy_board_seq FROM acm_board_cs WHERE legacy_board_seq IS NOT NULL")
            existing = {r[0] for r in c.fetchall()}
        click.echo(f"이미 이관된 legacy_board_seq = {len(existing)} 건 (제외)")

    src_cur = src.cursor(dictionary=True)
    sql_select = (
        "SELECT BOARD_MNG_SEQ, BOARD_SEQ, CS_DIV, CS_KIND, SUBJECT, CONTENT, "
        "REG_DT, REG_ID, CREATENAME, ANSWER, COUNSELOR_ID, ACTION_YN "
        "FROM tb_board_cs "
        "ORDER BY REG_DT ASC"
    )
    if limit > 0:
        sql_select += f" LIMIT {limit}"
    src_cur.execute(sql_select)

    total = 0
    skipped = 0
    buf: list[dict] = []

    for row in src_cur:
        legacy_seq = (row.get("BOARD_SEQ") or "").strip()
        if only_new and legacy_seq in existing:
            skipped += 1
            continue

        transformed = transform_row(row, mapper)
        buf.append(transformed)

        if dry_run and total < 5:
            preview = {k: (v if k != "inquiry_body" else (v[:200] + "…" if len(v) > 200 else v))
                       for k, v in transformed.items()}
            click.echo("--- 샘플 ---")
            click.echo(json.dumps(preview, ensure_ascii=False, indent=2, default=str))

        total += 1
        if len(buf) >= batch_size:
            if not dry_run:
                insert_batch(tgt, buf)
            buf.clear()

    if buf and not dry_run:
        insert_batch(tgt, buf)

    mapper.flush()

    if tgt is not None:
        tgt.commit()
        tgt.close()
    src.close()

    click.echo("")
    click.echo(click.style(f"✓ 처리 {total} 건 (skip {skipped})  id_map={len(mapper)}",
                           fg="green", bold=True))
    click.echo(f"  캐시: {cache_path.resolve()}  (0600 권한 설정 권장)")


def insert_batch(conn: mariadb.Connection, rows: list[dict]) -> None:
    sql = """
        INSERT INTO acm_board_cs (
            legacy_board_seq, legacy_cs_div, legacy_cs_kind,
            inquiry_user_id, inquiry_name,
            inquiry_title, inquiry_body, inquiry_date,
            actual_category, assigned_to,
            answer_body, answered_by, answered_at,
            resolution_state, reg_dt
        ) VALUES (
            %(legacy_board_seq)s, %(legacy_cs_div)s, %(legacy_cs_kind)s,
            %(inquiry_user_id)s, %(inquiry_name)s,
            %(inquiry_title)s, %(inquiry_body)s, %(inquiry_date)s,
            %(actual_category)s, %(assigned_to)s,
            %(answer_body)s, %(answered_by)s, %(answered_at)s,
            %(resolution_state)s, %(inquiry_date)s
        )
        ON DUPLICATE KEY UPDATE
            inquiry_title = VALUES(inquiry_title),
            inquiry_body  = VALUES(inquiry_body),
            answer_body   = VALUES(answer_body),
            upd_dt        = CURRENT_TIMESTAMP
    """
    with conn.cursor() as c:
        c.executemany(sql, rows)


if __name__ == "__main__":
    main()
