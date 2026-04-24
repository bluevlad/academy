"""PII 마스킹·referential 매핑.

동일 원본 user_id 는 동일 더미 user_id·이름으로 매핑되어야 통계·답변 관계가
깨지지 않는다. 해시 접두 8자 + Faker 이름 조합. 캐시는 JSON 파일에 영속.
"""
from __future__ import annotations

import hashlib
import json
from pathlib import Path
from typing import Dict

from faker import Faker


class PiiMapper:
    """원 user_id → {masked_id, masked_name} 매핑.

    - masked_id: usr_<sha256(원)[:8]>  — 결정적, 동일 원값 동일 결과
    - masked_name: Faker 한국어 이름. Faker seed 고정 시 재현 가능하지만
      랜덤 이름 회전 회피 위해 캐시에 저장.
    """

    def __init__(self, cache_path: Path, faker_locale: str = "ko_KR", faker_seed: int = 42):
        self._cache_path = cache_path
        self._fake = Faker(faker_locale)
        Faker.seed(faker_seed)
        self._map: Dict[str, Dict[str, str]] = {}
        if cache_path.exists():
            try:
                self._map = json.loads(cache_path.read_text(encoding="utf-8"))
            except Exception:
                self._map = {}

    def mask(self, original_id: str | None) -> tuple[str, str]:
        """원 user_id → (masked_id, masked_name).

        None·빈값은 ("anonymous", "익명사용자") 반환.
        """
        if not original_id:
            return ("anonymous", "익명사용자")

        key = original_id.strip()
        if not key:
            return ("anonymous", "익명사용자")

        if key in self._map:
            row = self._map[key]
            return (row["id"], row["name"])

        digest = hashlib.sha256(key.encode("utf-8")).hexdigest()[:8]
        masked_id = f"usr_{digest}"
        masked_name = self._fake.name()
        self._map[key] = {"id": masked_id, "name": masked_name}
        return (masked_id, masked_name)

    def flush(self) -> None:
        """캐시 파일에 저장. 0600 권한 권장 (운영자 수동 설정)."""
        self._cache_path.write_text(
            json.dumps(self._map, ensure_ascii=False, indent=2),
            encoding="utf-8",
        )

    def __len__(self) -> int:
        return len(self._map)
