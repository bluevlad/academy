"""MariaDB 커넥션 헬퍼 (동기 · 단기 작업).

agent 서비스는 분류·추천을 stateless 로 처리하므로 단발성 커넥션으로 충분.
배치 스크립트는 별도 연결·트랜잭션 관리.
"""
from __future__ import annotations

from contextlib import contextmanager

import mariadb

from app.settings import settings


def get_connection() -> mariadb.Connection:
    return mariadb.connect(
        host=settings.db_host,
        port=settings.db_port,
        user=settings.db_user,
        password=settings.db_password,
        database=settings.db_name,
        autocommit=False,
    )


@contextmanager
def connection():
    conn = get_connection()
    try:
        yield conn
    finally:
        conn.close()
