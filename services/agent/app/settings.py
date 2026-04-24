"""서비스 설정 — 환경변수 기반.

docker-compose.yml 에서 주입. 로컬 개발시 .env 또는 직접 export.
"""
from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    model_config = SettingsConfigDict(env_file=".env", extra="ignore")

    # HTTP 서비스
    agent_port: int = 9011

    # Ollama (host machine)
    ollama_base_url: str = "http://host.docker.internal:11434"
    classifier_model: str = "qwen2.5:7b"
    embedding_model: str = "nomic-embed-text"

    # LLM 호출 파라미터
    llm_temperature: float = 0.1
    llm_timeout_sec: int = 120
    embed_timeout_sec: int = 30

    # DB
    db_host: str = "host.docker.internal"
    db_port: int = 3306
    db_user: str = "root"
    db_password: str = ""
    db_name: str = "acm_basic"

    # 분류 카테고리 (수정 시 prompts/classify_v1.txt 도 갱신)
    categories: list[str] = ["ACADEMIC", "ORDER", "SYSTEM", "OTHER"]

    # 유사문의 검색
    suggest_top_k: int = 3
    suggest_min_similarity: float = 0.5


settings = Settings()
