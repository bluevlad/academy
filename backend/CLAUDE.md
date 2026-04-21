# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 실행 환경 감지 (SSH 재접속 금지)

- Claude는 현재 호스트에서 직접 실행 중 — **SSH 재접속을 시도하지 말 것**
- `uname -s` = `Darwin` → MacBook 운영환경 (172.30.1.72), docker/docker compose 직접 실행 가능
- `uname -s` 결과가 Windows/MINGW/MSYS → Windows 개발환경 (172.30.1.100)
- Docker 명령은 현재 호스트에서 바로 실행 (별도 SSH 접속 불필요)
- compose 파일 선택: Darwin → `docker-compose.yml` / Windows → `docker-compose.local.yml`

## Project Overview

Small Online Academy Admin Backend Service built with Spring Boot 3.x, MyBatis, and MySQL. Provides REST APIs for managing an online academy admin system including boards, members, lectures, books, exams, lockers, and dashboards.

**Tech Stack:**
- Java 17
- Spring Boot 3.2.0
- MyBatis 3.0.4
- MySQL 8.x (acm_basic database)
- JJWT 0.11.5 for JWT token authentication
- Maven for build management

EgovFramework -> SpringBoot + React

## Build and Run Commands

**Build:**
```bash
mvn clean package
```

**Run locally:**
```bash
mvn spring-boot:run
```

**Run built JAR:**
```bash
java -jar target/academy-admin-0.0.1-SNAPSHOT.jar
```

**Run tests:**
```bash
mvn test
```

Default server port: 8080

## Database Setup

1. Create MySQL schema as configured in environment
2. Configure connection in `src/main/resources/application.properties`
3. **Important:** Use environment variables or `.env` file for credentials (never commit secrets)
4. MyBatis mapper XMLs are in `src/main/resources/mapper/*.xml`

> See `CLAUDE.local.md` for local development credentials (not tracked in git)

## Architecture Overview

### Three-Tier Architecture Pattern

The codebase follows a layered architecture with two distinct patterns:

**Pattern 1: Direct Service with Mapper (Preferred, Modern)**
Used by: board, member, book, exam, locker, dashboard, menu
```
Api (Controller) → Service → Mapper (MyBatis Interface) → XML Mapper
```

**Pattern 2: Legacy Service-DAO Pattern**
Used by: bookCmmt, bookOrder (legacy modules)
```
Controller → ServiceImpl → DAO → XML Mapper
```

### Package Structure by Domain Module

Each domain module (board, member, lecture, exam, etc.) is organized as:
```
com.academy.<module>/
  ├── <Module>Api.java          # REST Controller (@RestController)
  ├── service/
  │   ├── <Module>Service.java  # Business logic (@Service)
  │   └── <Module>VO.java       # Value Object (DTO)
  └── (for legacy modules)
      └── service/impl/
          ├── <Module>ServiceImpl.java
          └── <Module>DAO.java
```

### MyBatis Mappers

MyBatis mappers are centralized:
```
com.academy.mapper/
  ├── BoardMapper.java          # @Mapper interface
  ├── BookMapper.java
  ├── LectureMstMapper.java
  └── ...
```

Corresponding XML files:
```
src/main/resources/mapper/
  ├── board.xml                 # namespace="com.academy.mapper.BoardMapper"
  ├── book.xml
  ├── lectureMstSQL.xml
  └── ...
```

### Common Components

Located in `com.academy.common/`:
- `CORSFilter`: Base class for API controllers with CORS configuration
- `JwtUtil`: JWT token generation, validation, and extraction
- `PaginationInfo`: Pagination helper (pageUnit=10, pageSize=10)
- `Configurations`: Properties file reader
- `DBUtil`: Database utilities
- `CommonUtil`, `CommonVO`: Shared utility functions and VOs

## API Controller Conventions

All API controllers:
1. Extend `CORSFilter` for CORS support
2. Use `@RestController` and `@RequestMapping("/api/<module>")`
3. Return `JSONObject` (from org.json.simple)
4. Accept VOs via `@ModelAttribute` or `@RequestBody`
5. Follow this response structure:
   ```java
   HashMap<String,Object> jsonObject = new HashMap<>();
   jsonObject.put("data", result);
   jsonObject.put("retMsg", "OK"); // or "FAIL"
   return new JSONObject(jsonObject);
   ```

## Service Class Conventions

All Service classes must:
1. **Implement `Serializable`** for session serialization support
2. Use `@Service` annotation
3. Inject dependencies via constructor injection (preferred over field injection)
4. Follow naming convention: `<Module>Service.java`

Example:
```java
@Service
public class BookService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    // Business logic methods...
}
```

### Standard CRUD Endpoint Patterns

**GET endpoints** (list/detail):
```java
@GetMapping(value = "/get<Module>List")
public JSONObject get<Module>List(@ModelAttribute <Module>VO vo)

@GetMapping(value = "/get<Module>Detail")
public JSONObject get<Module>Detail(@ModelAttribute <Module>VO vo)
```

**POST endpoints** (create/update/delete):
```java
@PostMapping(value = "/insert<Module>")
@PostMapping(value = "/update<Module>")
@PostMapping(value = "/delete<Module>")
```

**RESTful endpoints** (newer modules like lecture):
```java
@GetMapping, @PostMapping, @PutMapping, @DeleteMapping
```

## Pagination

Most list APIs implement pagination using `PaginationInfo`:
```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(vo.getPageIndex());
paginationInfo.setRecordCountPerPage(vo.getPageUnit());
paginationInfo.setPageSize(vo.getPageSize());

vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
vo.setLastIndex(paginationInfo.getLastRecordIndex());
```

## MyBatis XML Mapper Conventions

1. Use `LIMIT #{recordCountPerPage} OFFSET #{firstIndex}` for pagination
2. Dynamic SQL with `<if test="">` for optional fields
3. Auto-generate IDs using `<selectKey>` with UUID
4. Date fields use MySQL `now()` function
5. Namespace must match the Mapper interface fully qualified name

## Authentication

JWT-based authentication:
- `JwtUtil` generates tokens with 1-hour expiration
- **Security Note:** JWT secret key should be externalized via environment variables in production
- Login API validates credentials and returns JWT token
- Protected endpoints should validate token via `JwtUtil.validateToken()`

## Module Refactoring Status

Recent refactoring (see git status) moved from legacy DAO pattern to modern Mapper pattern:
- ✅ Completed: lecture module (Category, Form, Kind, LectureMst, Lecture, OpenLecture, ProductEvent, Series, Subject, Teacher, MacAddressManager)
- ✅ Completed: board, member, book, exam, locker, dashboard
- ⚠️ Legacy: bookCmmt, bookOrder (still use ServiceImpl/DAO pattern)

When working on lecture modules, note:
- Old: `src/main/java/com/academy/lecture/service/impl/*ServiceImpl.java` (deleted)
- New: `src/main/java/com/academy/lecture/service/*Service.java` (direct @Service)
- Old: `src/main/java/com/academy/lecture/service/impl/*DAO.java` (deleted)
- New: `src/main/java/com/academy/mapper/*Mapper.java` (MyBatis @Mapper)

## Help Page 관리

> 작성 표준: [HELP_PAGE_GUIDE.md](https://github.com/bluevlad/Claude-Opus-bluevlad/blob/main/standards/documentation/HELP_PAGE_GUIDE.md)
> HTML 템플릿: [help-page-template.html](https://github.com/bluevlad/Claude-Opus-bluevlad/blob/main/standards/documentation/templates/help-page-template.html)

- **기능 추가/변경/삭제 시 반드시 헬프 페이지도 함께 업데이트**
- 헬프 파일 위치: `src/main/resources/static/help/`
- 서비스 accent-color: `#3b82f6` (Blue)
- 대상 가이드 파일:
  - `admin-guide.html` — 관리자 콘솔 가이드
  - `api-guide.html` — 관리자 API 가이드

## 연동 프론트엔드

- **프로젝트**: `../frontend/`
- **URL**: `http://localhost:3000`
- **API 클라이언트**: `../frontend/src/shared/api/client.js`
- **API 모듈**: `../frontend/src/api/` (Axios 기반)

백엔드 API 추가/변경 시 프론트엔드 API 모듈도 함께 업데이트.

## Known Issues

**BOM Encoding:** If encountering compile errors with `\ufeff` character, ensure source files are saved as UTF-8 without BOM. This has been fixed in `Configurations.java` and `DBUtil.java`.

## Adding New Features

When adding new API endpoints or modules:

1. Create VO in `<module>/service/<Module>VO.java` extending `CommonVO`
2. Create Mapper interface in `com.academy.mapper/<Module>Mapper.java` with `@Mapper`
3. Create XML mapper in `src/main/resources/mapper/<module>.xml`
4. Create Service in `<module>/service/<Module>Service.java` with `@Service` **implementing `Serializable`**
5. Create API Controller in `<module>/<Module>Api.java` extending `CORSFilter`
6. Use constructor injection for dependencies (preferred over `@Autowired`)

Follow the pattern from modern modules (board, lecture) rather than legacy modules (bookCmmt, bookOrder).

## Fix 커밋 오류 추적

> 상세: [FIX_COMMIT_TRACKING_GUIDE.md](https://github.com/bluevlad/Claude-Opus-bluevlad/blob/main/standards/git/FIX_COMMIT_TRACKING_GUIDE.md) | [ERROR_TAXONOMY.md](https://github.com/bluevlad/Claude-Opus-bluevlad/blob/main/standards/git/ERROR_TAXONOMY.md)

`fix:` 커밋 시 footer에 오류 추적 메타데이터를 **필수** 포함합니다.

### 이 프로젝트에서 자주 발생하는 Root-Cause

| Root-Cause | 설명 | 예방 |
|-----------|------|------|
| `env-assumption` | profile별 설정 차이, 환경변수 가정 | `@Value` + `${VAR:?required}` 패턴 필수 |
| `null-handling` | NullPointerException | `Optional<T>` 반환, `@Nullable` 어노테이션 |
| `config-typo` | application.yml 키 오타, 들여쓰기 오류 | IDE YAML 검증, 프로파일별 설정 테스트 |
| `type-mismatch` | DTO ↔ Entity 매핑 타입 불일치 | MapStruct 또는 ModelMapper 사용, 단위 테스트 |
| `missing-auth` | API 인증/인가 누락 | `@PreAuthorize` 또는 SecurityFilterChain에 명시 |
| `cors-miscfg` | CORS 와일드카드 허용 | `allowedOrigins`에 명시적 도메인만 등록 |

### 예시

```
fix(auth): JWT 토큰 만료 검증 로직 수정

- JwtTokenProvider에서 만료 시간 비교 시 초/밀리초 단위 혼동
- Instant.now().getEpochSecond() 사용으로 통일

Root-Cause: unit-mismatch
Error-Category: logic-error
Affected-Layer: backend/auth
Recurrence: first
Prevention: 시간 비교 시 단위를 변수명에 명시 (epochSec, epochMs)

fixes #15
Co-Authored-By: Claude Opus 4.6 <noreply@anthropic.com>
```