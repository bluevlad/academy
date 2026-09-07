# services/agent — Academy CS AI Agent (Phase B)

CS 1:1문의의 **분류 · 유사추천 · 월간집계 · 재배정 피드백** 을 처리하는 Python FastAPI 서비스.
**로컬 Ollama** 로만 모델 추론 (외부 API 호출 0).

관련 플랜: `Ai-Legacy-bluevlad/services/academy/CS_INQUIRY_AI_PLAN.md` (메타 repo, private)

## 아키텍처

```
backend (Spring :9001) ─HTTP→ academy-agent (:9011)
                                ├─ /classify         (문의 1건 분류)
                                ├─ /suggest-related  (유사 문의 top-K)
                                ├─ /analyze-monthly  (월간 트렌드)
                                └─ /route-feedback   (재배정 기록)
                                    │
                                    ├─ Ollama (host :11434)
                                    │    - qwen2.5:7b    (분류)
                                    │    - nomic-embed-text (임베딩)
                                    └─ MariaDB (host :3306)
                                         - acm_board_cs
                                         - acm_inquiry_analysis / routing_log / embedding
```

## 엔드포인트

### GET /health
서비스 기동 확인.

### GET /health/ollama
Ollama 서버 연결 + 설치 모델 목록.

### POST /classify
```json
{ "title": "환불 문의", "body": "강의를 한번만 들었는데…", "cs_seq": 123 }
→ { "category": "ORDER", "confidence": 0.95, "reasoning": "환불은 결제 영역",
    "model": "qwen2.5:7b", "latency_ms": 1800, "used_fallback": false }
```

### POST /suggest-related
```json
{ "draft_body": "강의 환불 절차가 궁금합니다", "top_k": 3 }
→ { "items": [{ "cs_seq": 42, "title": "환불 규정", "answer_excerpt": "…",
                "similarity": 0.87, "category": "ORDER" }, …] }
```

### POST /analyze-monthly
```json
{ "year_month": "2026-04" }
→ { "category_trends": [{ "category": "ORDER", "total_count": 120, "resolved_count": 105,
                          "mom_delta_pct": -12.3, "is_decreasing": true }, …],
    "total_inquiries": 300, "overall_resolution_rate": 0.87 }
```

### POST /route-feedback
```json
{ "cs_seq": 123, "from_category": "ORDER", "to_category": "ACADEMIC",
  "to_user": "admin", "changed_by": "admin", "is_ai_error": true }
→ { "log_seq": 88, "learning_queued": true }
```

## 실행 (로컬 개발)

```bash
cd services/agent
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt

# 환경 변수
export DB_USER=root
export DB_PASSWORD='<password>'
export OLLAMA_BASE_URL=http://localhost:11434

uvicorn app.main:app --host 0.0.0.0 --port 9011 --reload
```

호스트에 Ollama 모델이 설치돼 있어야:

```bash
ollama pull qwen2.5:7b
ollama pull nomic-embed-text
```

## 실행 (Docker — production)

```bash
cd /Users/rainend/GIT/academy
docker compose --profile all up -d academy-agent
# 헬스체크
curl http://localhost:9011/health
curl http://localhost:9011/health/ollama
```

## 배치 스크립트

acm_board_cs 데이터가 이관돼 있어야 함 (Phase A ETL 완료 조건).

```bash
# 임베딩 생성 (5,430건 기준 ~20-30분 예상, nomic-embed-text)
python scripts/build_embeddings.py --limit 100 --dry-run
python scripts/build_embeddings.py

# 분류 (5,430건 기준 ~2-3시간 예상, qwen2.5:7b)
python scripts/batch_classify.py --limit 100 --dry-run
python scripts/batch_classify.py
```

## 성능 기대 (Apple M5 · 24GB · Metal 4 기준 추정)

| 작업 | 모델 | 건당 시간 |
|------|------|----------|
| classify 1건 | qwen2.5:7b | 1-2초 |
| embed 1건 | nomic-embed-text | 0.1-0.3초 |
| suggest-related (5,430건 코사인) | numpy | < 100ms |

## 한계 · 개선 여지

- ChromaDB 대신 MariaDB BLOB 사용 (5,430건 수준 OK, 10만+ 에서 재검토)
- 분류 초기 정확도 70-80% 기대 — 재배정 피드백 누적 후 개선 (Phase E)
- 첨부파일 PII 스크러빙은 Phase A ETL 수행. 본 agent 는 스크럽된 본문 가정

## 보안

- 외부 API 호출 0건 (Ollama 로컬 only)
- DB credential 은 환경변수만. 로그 노출 금지
- raw_output 컬럼에 LLM 원 응답 저장되지만 PII 는 Phase A 에서 이미 마스킹됨
