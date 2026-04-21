# Academy Backend - Spring Boot REST API

Small Online Academy backend service - A comprehensive educational platform management system built with Spring Boot 3.2.0.

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **MyBatis**: 3.0.4
- **Database**: MySQL 8.x
- **JWT**: JJWT 0.11.5
- **Jakarta EE**: jakarta.servlet.*
- **API Documentation**: SpringDoc OpenAPI 2.3.0 (Swagger UI)

## Quick Start

### Prerequisites

- JDK 17+
- Maven 3.6+
- MySQL 8.x

### Build & Run

```bash
# Build the project
mvn clean package

# Run development server
mvn spring-boot:run

# Run from JAR
java -jar target/academy-0.0.1-SNAPSHOT.jar
```

Server runs on: `http://localhost:8080`

### API Documentation (Swagger UI)

After starting the server, access the API documentation:

| URL                                     | Description |
|-----------------------------------------|-------------|
| http://localhost:8080/swagger-ui.html   | Swagger UI - Interactive API documentation |
| http://localhost:8080/v3/api-docs       | OpenAPI 3.0 JSON specification |

**Features:**
- Browse all API endpoints grouped by module
- Test APIs directly in browser
- View request/response schemas
- Export OpenAPI specification

### Database Setup

1. Create MySQL schema:
```sql
CREATE SCHEMA acm_basic;
```

2. Execute DDL scripts:
```bash
# From MySQL client or workbench
source ddls/member.sql
source ddls/board.sql
```

### Testing

```bash
mvn test
```

## Architecture

### Layered Architecture Pattern

This project follows a **layered architecture with direct Mapper usage** (no DAO layer):

```
com.academy/
├── [module]/                           # Feature modules
│   ├── [Module]Api.java               # REST Controller
│   └── service/
│       ├── [Module]Service.java       # Service interface
│       └── [Module]ServiceImpl.java   # Service implementation
├── mapper/                             # MyBatis Mapper interfaces
│   └── [Module]Mapper.java
└── common/                             # Shared utilities
```

**Key Pattern**: ServiceImpl classes directly inject Mapper interfaces - **NO DAO layer**.

### Package Structure

```
com.academy/
├── AcademyApplication.java            # Spring Boot main class
│
├── board/                             # Bulletin board system
│   ├── BoardApi.java
│   └── service/
│       ├── BoardService.java
│       └── BoardServiceImpl.java
│
├── book/                              # Textbook management
│   ├── BookApi.java
│   └── service/
│       ├── BookService.java
│       └── BookServiceImpl.java
│
├── bookCmmt/                          # Book comments
│   └── service/
│       ├── BookCmmtService.java
│       └── BookCmmtServiceImpl.java
│
├── bookOrder/                         # Book ordering
│   └── service/
│       ├── BookOrderService.java
│       └── BookOrderServiceImpl.java
│
├── dashboard/                         # Admin dashboard
│   ├── DashBoardApi.java
│   └── service/
│       ├── DashBoardService.java
│       └── DashBoardServiceImpl.java
│
├── exam/                              # Examination system
│   ├── ExamApi.java
│   ├── ExamBankApi.java
│   └── service/
│       ├── ExamService.java
│       ├── ExamServiceImpl.java
│       ├── ExamBankService.java
│       └── ExamBankServiceImpl.java
│
├── lecture/                           # Course/Lecture management (largest module)
│   ├── CategoryApi.java              # Lecture categories
│   ├── FormApi.java                  # Lecture formats
│   ├── KindApi.java                  # Lecture types
│   ├── LectureApi.java               # Main lecture CRUD
│   ├── LectureMstApi.java            # Lecture master data
│   ├── MacAddressManagerApi.java     # MAC address management
│   ├── OpenLectureApi.java           # Open lecture management
│   ├── ProductEventApi.java          # Product events/promotions
│   ├── SeriesApi.java                # Lecture series
│   ├── SubjectApi.java               # Subject management
│   ├── TeacherApi.java               # Instructor management
│   └── service/
│       ├── CategoryService.java
│       ├── CategoryServiceImpl.java
│       ├── FormService.java
│       ├── FormServiceImpl.java
│       ├── KindService.java
│       ├── KindServiceImpl.java
│       ├── LectureService.java
│       ├── LectureServiceImpl.java
│       ├── LectureMstService.java
│       ├── LectureMstServiceImpl.java
│       ├── MacAddressManagerService.java
│       ├── MacAddressManagerServiceImpl.java
│       ├── OpenLectureService.java
│       ├── OpenLectureServiceImpl.java
│       ├── ProductEventService.java
│       ├── ProductEventServiceImpl.java
│       ├── SeriesService.java
│       ├── SeriesServiceImpl.java
│       ├── SubjectService.java
│       ├── SubjectServiceImpl.java
│       ├── TeacherService.java
│       └── TeacherServiceImpl.java
│
├── locker/                            # Locker assignment
│   ├── LockerApi.java
│   └── service/
│       ├── LockerService.java
│       └── LockerServiceImpl.java
│
├── login/                             # Authentication
│   ├── LoginApi.java
│   └── service/
│       ├── LoginService.java
│       └── LoginServiceImpl.java
│
├── main/                              # Main page
│   ├── MainApi.java
│   └── service/
│       ├── MainService.java
│       └── MainServiceImpl.java
│
├── member/                            # User management
│   ├── MemberApi.java
│   └── service/
│       ├── MemberService.java
│       └── MemberServiceImpl.java
│
├── menu/                              # Menu management
│   ├── MenuApi.java
│   └── service/
│       ├── MenuService.java
│       └── MenuServiceImpl.java
│
├── banner/                            # Banner management
│   ├── BannerApi.java
│   └── service/
│       ├── BannerService.java
│       └── BannerVO.java
│
├── box/                               # Box management
│   ├── BoxApi.java
│   └── service/
│       ├── BoxService.java
│       └── BoxVO.java
│
├── coop/                              # Affiliate/Partner management
│   ├── CoopApi.java
│   └── service/
│       ├── CoopService.java
│       ├── CoopVO.java
│       ├── CoopIpVO.java
│       ├── CoopBoardVO.java
│       └── CoopOrderVO.java
│
├── counsel/                           # Counseling management
│   ├── CounselApi.java
│   └── service/
│       ├── CounselService.java
│       ├── CounselScheduleVO.java
│       ├── CounselRequestVO.java
│       └── PresentRequestVO.java
│
├── dday/                              # D-Day management
│   ├── DdayApi.java
│   └── service/
│       ├── DdayService.java
│       └── DdayVO.java
│
├── event/                             # Event management
│   ├── EventApi.java
│   └── service/
│       ├── EventService.java
│       ├── EventVO.java
│       ├── EventOptionVO.java
│       ├── EventFileVO.java
│       ├── EventResultVO.java
│       └── LecEventVO.java
│
├── productorder/                      # Product ordering
│   ├── CouponMngApi.java
│   ├── ProductOrderApi.java
│   └── service/
│       ├── CouponMngService.java
│       ├── CouponMngServiceImpl.java
│       ├── ProductOrderService.java
│       └── ProductOrderServiceImpl.java
│
├── manage/                            # Academy management
│   ├── categorySale/                  # Category sales management
│   │   ├── CategorySaleApi.java
│   │   └── service/
│   │       ├── CategorySaleService.java
│   │       └── CategorySaleVO.java
│   ├── lectureYear/                   # Yearly lecture management
│   │   ├── LectureYearApi.java
│   │   └── service/
│   │       ├── LectureYearService.java
│   │       └── LectureYearVO.java
│   └── teacherCalculate/              # Teacher settlement management
│       ├── TeacherCalculateApi.java
│       └── service/
│           ├── TeacherCalculateService.java
│           └── TeacherCalculateVO.java
│
├── mapper/                            # MyBatis Mapper interfaces
│   ├── AdminAuthMapper.java
│   ├── AdminCodeMapper.java
│   ├── AdminMenuMapper.java
│   ├── BannerMapper.java
│   ├── BoardManagementMapper.java
│   ├── BoardMapper.java
│   ├── BoardNotAnswerMapper.java
│   ├── BoardTeacherMapper.java
│   ├── BookMapper.java
│   ├── BookCmmtMapper.java
│   ├── BookOrderMapper.java
│   ├── BoxMapper.java
│   ├── CategoryMapper.java
│   ├── CategorySaleMapper.java
│   ├── CategorySeriesMapper.java
│   ├── CmmUseMapper.java
│   ├── CoopMapper.java
│   ├── CounselMapper.java
│   ├── CouponMngMapper.java
│   ├── DashBoardMapper.java
│   ├── DdayMapper.java
│   ├── EventMapper.java
│   ├── ExamBankMapper.java
│   ├── ExamMapper.java
│   ├── FormMapper.java
│   ├── FreeOrderMapper.java
│   ├── GosiMapper.java
│   ├── IndexMapper.java
│   ├── KindMapper.java
│   ├── LectureFeesMapper.java
│   ├── LectureMapper.java
│   ├── LectureMstMapper.java
│   ├── LectureOffMapper.java
│   ├── LectureYearMapper.java
│   ├── LockerMapper.java
│   ├── LoginMapper.java
│   ├── MacAddressManagerMapper.java
│   ├── MainMapper.java
│   ├── MemberMapper.java
│   ├── MenuMapper.java
│   ├── MockBoardMapper.java
│   ├── MouiApplyMapper.java
│   ├── MouiExamMapper.java
│   ├── NoteMapper.java
│   ├── OffExamMapper.java
│   ├── OffExamRegMapper.java
│   ├── OpenLectureMapper.java
│   ├── PersonStatsMapper.java
│   ├── PopupMapper.java
│   ├── ProductEventMapper.java
│   ├── ProductOrderMapper.java
│   ├── PubMapper.java
│   ├── SalesStatMapper.java
│   ├── SeriesMapper.java
│   ├── SubjectMapper.java
│   ├── SurveyMapper.java
│   ├── TeacherCalculateMapper.java
│   ├── TeacherMapper.java
│   └── TotalStatsMapper.java
│
├── index/                             # Index/Menu management
│   ├── IndexApi.java
│   └── service/
│       ├── IndexService.java
│       └── IndexVO.java
│
├── gosi/                              # Gosi (Civil Service Exam) management
│   ├── GosiApi.java
│   ├── PubApi.java
│   └── service/
│       ├── GosiService.java
│       ├── GosiVO.java
│       ├── PubService.java
│       └── PubVO.java
│
├── freeOrder/                         # Free lecture registration
│   ├── FreeOrderApi.java
│   └── service/
│       ├── FreeOrderService.java
│       └── FreeOrderVO.java
│
├── lectureOff/                        # Offline lecture management
│   ├── LectureOffApi.java
│   └── service/
│       ├── LectureOffService.java
│       └── LectureOffVO.java
│
├── common/                            # Common utilities
│   ├── ApiInfoApi.java               # API information endpoint
│   ├── CORSFilter.java               # Base class for all API controllers
│   ├── JwtUtil.java                  # JWT token utilities
│   ├── PaginationInfo.java           # Pagination helper
│   ├── CommonUtil.java               # Common utility methods
│   ├── DBUtil.java                   # Database utilities
│   ├── Configurations.java           # Spring configuration beans
│   ├── CommonVO.java                 # Common value object
│   ├── ComDefaultVO.java             # Default value object
│   ├── ComDefaultCodeVO.java         # Default code value object
│   └── service/
│       ├── CmmUseService.java        # Common service
│       └── CmmnDetailCode.java       # Common detail code
│
└── config/                            # Configuration classes
    └── OpenApiConfig.java            # Swagger/OpenAPI configuration
```

## API Modules

### Core Business Modules

| Module | Description | API Endpoints | Key Features |
|--------|-------------|---------------|--------------|
| **lecture** | Course/Lecture management | 11 APIs | Largest module with category, teacher, subject, form, kind, series, lecture master, MAC address, open lecture, product events |
| **exam** | Examination system | 2 APIs | Exam management and exam bank |
| **member** | User management | 1 API | Student/instructor registration, profile management |
| **locker** | Locker assignment | 1 API | Locker booking and management |
| **book** | Textbook management | 1 API | Course materials and textbooks |
| **productorder** | Product ordering | 2 APIs | Coupon management, product orders |

### Content & Support Modules

| Module | Description | API Endpoints | Key Features |
|--------|-------------|---------------|--------------|
| **board** | Bulletin board | 1 API | Announcements, notices, discussions |
| **bookOrder** | Book ordering | Service only | Book purchase and delivery |
| **bookCmmt** | Book comments | Service only | User reviews and comments |
| **dashboard** | Admin dashboard | 1 API | Statistics and overview |
| **login** | Authentication | 1 API | Session-based authentication |
| **main** | Main page | 1 API | Landing page content |
| **menu** | Menu management | 1 API | Navigation menu configuration |
| **banner** | Banner management | 1 API | Banner CRUD, display management |
| **box** | Box management | 1 API | Content box management |
| **coop** | Affiliate management | 1 API | Partner/affiliate management, IP whitelist, orders |
| **counsel** | Counseling management | 1 API | Counseling schedules, requests, presentations |
| **dday** | D-Day management | 1 API | Important dates (exams, deadlines) |
| **event** | Event management | 1 API | Online/offline events, lecture events |

### System & Utility Modules

| Module | Description | API Endpoints | Key Features |
|--------|-------------|---------------|--------------|
| **common/ApiInfo** | API Information | 2 APIs | List all APIs, API summary by module |
| **config** | Configuration | - | OpenAPI/Swagger configuration |

### Lecture Module (Most Complex)

The lecture module is the largest and most feature-rich module with **11 separate APIs**:

1. **CategoryApi** (`/api/lecture/category`) - Lecture category management
2. **TeacherApi** (`/api/lecture/teacher`) - Instructor management (supports up to 32 images)
3. **SubjectApi** (`/api/lecture/subject`) - Subject/course management
4. **FormApi** (`/api/lecture/form`) - Lecture format types
5. **KindApi** (`/api/lecture/kind`) - Lecture kind/classification
6. **SeriesApi** (`/api/lecture/series`) - Lecture series management
7. **LectureApi** (`/api/lecture`) - Main lecture CRUD operations
8. **LectureMstApi** (`/api/lecture/mst`) - Lecture master data
9. **MacAddressManagerApi** (`/api/lecture/macaddress`) - Device MAC address management
10. **OpenLectureApi** (`/api/lecture/open`) - Open lecture scheduling
11. **ProductEventApi** (`/api/lecture/event`) - Promotional events

**Key Features:**
- Automatic code generation (e.g., `D202400001`, `M202400001`)
- Bridge table pattern for many-to-many relationships
- Extensive file upload support
- Complex business logic with multiple related entities

## REST API Pattern

### Controller Pattern

All API controllers follow this standard pattern:

```java
@RestController
@RequestMapping("/api/[module]")
public class ExampleApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final ExampleService exampleService;

    @Autowired
    public ExampleApi(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/list")
    public JSONObject list(@ModelAttribute HashMap<String, String> params,
                          HttpServletRequest request) throws Exception {
        // Implementation
    }

    @PostMapping("/insert")
    public JSONObject insert(@ModelAttribute HashMap<String, String> params,
                            HttpServletRequest request) throws Exception {
        // Implementation
    }
}
```

### Key Characteristics

- **Extend CORSFilter**: All controllers extend `CORSFilter` for CORS support
- **Constructor Injection**: Use constructor-based dependency injection
- **JSONObject Response**: All endpoints return `JSONObject` from org.json.simple
- **HTTP Method Mapping**: Use specific annotations (`@GetMapping`, `@PostMapping`, etc.)

### Standard Response Format

**Success Response:**
```json
{
  "result": "success",
  "message": "작업이 완료되었습니다",
  "list": [...],
  "totalCount": 100,
  "currentPage": 1,
  "totalPage": 10
}
```

**Error Response:**
- Transaction rollback with exception handling

### Pagination

All list endpoints support pagination:
- **Request params**: `currentPage` (default: 1), `pageRow` (default: from `pageUnit` property)
- **Response fields**: `list`, `totalCount`, `totalPage`, `currentPage`
- **Helper class**: `PaginationInfo`

## Service Layer Pattern

### Direct Mapper Injection (No DAO)

ServiceImpl classes directly use MyBatis Mapper interfaces:

```java
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleMapper exampleMapper;  // Direct Mapper injection

    @Override
    public List<HashMap<String, String>> getList(HashMap<String, String> params) {
        return exampleMapper.exampleList(params);  // Direct call
    }

    @Override
    @Transactional
    public void insert(HashMap<String, String> params) {
        exampleMapper.exampleInsert(params);
    }
}
```

**Important**:
- NO DAO layer exists in this architecture
- ServiceImpl classes are in `service` package (NOT `service.impl`)
- Direct dependency on Mapper interfaces

## MyBatis Mapper Layer

### Mapper Interface Pattern

```java
package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface ExampleMapper {
    List<HashMap<String, String>> exampleList(HashMap<String, String> params);
    int exampleListCount(HashMap<String, String> params);
    HashMap<String, String> exampleView(HashMap<String, String> params);
    void exampleInsert(HashMap<String, String> params);
    void exampleUpdate(HashMap<String, String> params);
    void exampleDelete(HashMap<String, String> params);
}
```

### XML Mapper Pattern

Location: `src/main/resources/mapper/[Module]Mapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.academy.mapper.ExampleMapper">

    <select id="exampleList" parameterType="hashMap" resultType="hashMap">
        SELECT *
        FROM TB_EXAMPLE
        WHERE 1=1
        <if test="searchKeyword != null and searchKeyword != ''">
            AND NAME LIKE CONCAT('%', #{searchKeyword}, '%')
        </if>
        ORDER BY REG_DT DESC
        LIMIT #{firstIndex}, #{recordCountPerPage}
    </select>

    <select id="exampleListCount" parameterType="hashMap" resultType="int">
        SELECT COUNT(*)
        FROM TB_EXAMPLE
        WHERE 1=1
    </select>

    <insert id="exampleInsert" parameterType="hashMap">
        INSERT INTO TB_EXAMPLE (
            TITLE, CONTENT, REG_ID, REG_DT
        ) VALUES (
            #{title}, #{content}, #{regId}, NOW()
        )
    </insert>

</mapper>
```

### Mapper Method Naming Conventions

| Operation | Method Name | Description |
|-----------|-------------|-------------|
| List | `[module]List` | Get list of records |
| Count | `[module]ListCount` | Count total records |
| View | `[module]View` | Get single record |
| Insert | `[module]Insert` | Insert new record |
| Update | `[module]Update` | Update existing record |
| Delete | `[module]Delete` | Delete record |

## API Documentation (SpringDoc OpenAPI)

### Swagger UI Access

After starting the application, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

### Configuration

The OpenAPI configuration is defined in `com.academy.config.OpenApiConfig`:

```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Academy Backend API")
                        .description("Small Online Academy Backend Service API Documentation")
                        .version("1.0.0"));
    }
}
```

### Adding Swagger Annotations

To document your APIs, use these annotations:

**Controller Level:**
```java
@Tag(name = "Board", description = "게시판 관리 API")
@RestController
@RequestMapping("/api/board")
public class BoardApi extends CORSFilter {
    // ...
}
```

**Method Level:**
```java
@Operation(summary = "게시판 목록 조회", description = "페이징 처리된 게시판 목록을 조회합니다.")
@GetMapping("/getBoardList")
public JSONObject getBoardList(@ModelAttribute BoardVO boardVO) {
    // ...
}
```

**Parameter Level:**
```java
@Parameter(description = "게시판 ID", required = true)
@RequestParam String boardId
```

### API Info Endpoints

The system provides built-in API discovery endpoints:

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/info/getApiList` | GET | Get all API endpoints list |
| `/api/info/getApiList?module=board` | GET | Filter APIs by module |
| `/api/info/getApiSummary` | GET | Get API count summary by module |

**Example Response (`/api/info/getApiList`):**
```json
{
  "totalCount": 25,
  "retMsg": "OK",
  "apiList": [
    {
      "url": "/api/board/getBoardList",
      "httpMethod": "GET",
      "controller": "BoardApi",
      "method": "getBoardList",
      "package": "board"
    }
  ]
}
```

**Example Response (`/api/info/getApiSummary`):**
```json
{
  "totalCount": 25,
  "moduleCount": 12,
  "retMsg": "OK",
  "summary": [
    {
      "module": "board",
      "count": 5,
      "apis": [...]
    }
  ]
}
```

## Common Utilities

### CORSFilter

Base class for all API controllers providing CORS support:

```java
public class CORSFilter {
    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type");
    }
}
```

### JwtUtil

JWT token generation and validation:

```java
public class JwtUtil {
    public static String generateToken(String userId);
    public static boolean validateToken(String token);
    public static String getUserIdFromToken(String token);
}
```

### PaginationInfo

Pagination calculation helper:

```java
public class PaginationInfo {
    private int currentPageNo;
    private int recordCountPerPage;
    private int pageSize;
    private int totalRecordCount;

    public void setTotalRecordCount(int totalRecordCount);
    public int getFirstRecordIndex();
    public int getLastRecordIndex();
    public int getTotalPageCount();
}
```

### CommonUtil

Common utility methods:

```java
public class CommonUtil {
    public static String nullToBlank(Object obj);
    public static String getClientIP(HttpServletRequest request);
    public static String getCurrentTimestamp();
}
```

### DBUtil

Database connection utilities for direct JDBC operations when needed.

## Authentication

### Session-Based Authentication

- **Session Key**: `AdmUserInfo.USER_ID`
- **Usage**: Automatically populated in `REG_ID` and `UPD_ID` fields
- **Access Pattern**:

```java
// Get user from session
Object userInfo = request.getSession().getAttribute("AdmUserInfo");
HashMap<String, String> user = (HashMap<String, String>) userInfo;
String userId = user.get("USER_ID");

// Set audit fields
params.put("REG_ID", userId);
params.put("UPD_ID", userId);
```

### JWT Support

JWT utilities are available in `JwtUtil` class but session-based auth is primarily used for API endpoints.

## File Upload

### Upload Pattern

```java
@PostMapping("/upload")
public JSONObject upload(MultipartHttpServletRequest request) throws Exception {
    MultipartFile file = request.getFile("file");
    String uploadPath = "member_upload/";

    // Use FileUtil for upload
    String savedFileName = FileUtil.uploadFile(file, uploadPath);

    return response;
}
```

### Upload Directories

- `member_upload/` - Member-related files
- `openlecture_upload/` - Open lecture materials
- Teacher module supports up to 32 images

## Configuration

### application.properties

```properties
# Database
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://${DB_HOST:127.0.0.1}:${DB_PORT:3306}/${DB_NAME:acm_basic}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

# MyBatis
mybatis.mapper-locations=classpath:/mapper/*.xml
mybatis.type-aliases-package=com.academy

# Server
server.port=8080

# Pagination
pageUnit=10

# File Upload
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# SpringDoc OpenAPI (Swagger)
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.tags-sorter=alpha
springdoc.swagger-ui.operations-sorter=alpha
springdoc.packages-to-scan=com.academy
```

### Property Injection Pattern

Use `@Value` annotation (NOT EgovPropertyService):

```java
@Value("${pageUnit:10}")
private int pageUnit;

@Value("${uploadPath:/uploads}")
private String uploadPath;
```

## Development Guidelines

### File Encoding

**CRITICAL**: Always use **UTF-8 without BOM**
- BOM characters (`\ufeff`) cause compilation errors
- If compile errors occur with `\ufeff`, re-save files as UTF-8 without BOM

### Jakarta EE (Spring Boot 3.x)

- Always use `jakarta.servlet.*`, `jakarta.annotation.*`
- **NEVER** use `javax.*` - this project uses Spring Boot 3.x

### Adding a New Module

1. **Create Mapper Interface** in `com.academy.mapper`:
```java
@Mapper
public interface ExampleMapper {
    List<HashMap<String, String>> exampleList(HashMap<String, String> params);
}
```

2. **Create MyBatis XML Mapper** in `src/main/resources/mapper/`:
```xml
<mapper namespace="com.academy.mapper.ExampleMapper">
    <select id="exampleList" parameterType="hashMap" resultType="hashMap">
        SELECT * FROM TB_EXAMPLE
    </select>
</mapper>
```

3. **Create Service Interface** in `com.academy.[module].service`:
```java
public interface ExampleService {
    List<HashMap<String, String>> getList(HashMap<String, String> params);
}
```

4. **Create ServiceImpl** in same package:
```java
@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleMapper exampleMapper;

    @Override
    public List<HashMap<String, String>> getList(HashMap<String, String> params) {
        return exampleMapper.exampleList(params);
    }
}
```

5. **Create API Controller**:
```java
@RestController
@RequestMapping("/api/example")
public class ExampleApi extends CORSFilter {
    @Value("${pageUnit:10}")
    private int pageUnit;

    private final ExampleService exampleService;

    @Autowired
    public ExampleApi(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/list")
    public JSONObject list(@ModelAttribute HashMap<String, String> params) {
        // Implementation
    }
}
```

### Refactoring Legacy Code

When migrating from old DAO-based pattern:

1. Delete DAO classes and `service.impl` package
2. Create Mapper interface in `com.academy.mapper`
3. Move ServiceImpl to `service` package (not `service.impl`)
4. Update ServiceImpl to inject Mapper (not DAO)
5. Update XML namespace to point to Mapper
6. Replace all `dao.*` calls with `mapper.*`

## Testing

### Unit Tests

```bash
mvn test
```

### API Testing

Use tools like:
- **Swagger UI** (recommended): http://localhost:8080/swagger-ui.html
- Postman
- curl
- HTTPie

Example:
```bash
# Get lecture list
curl http://localhost:8080/api/lecture/list?currentPage=1&pageRow=10

# Get member info
curl http://localhost:8080/api/member/view?userId=user001
```

## Related Repositories

- **Frontend**: [React Admin Dashboard](https://github.com/bluevlad/react/tree/main/datta-able-free-react-admin-template)
- **Legacy Admin**: [eGov Framework Admin](https://github.com/bluevlad/Java/tree/master/Academy-egovframework)

## Database Schema

### Core Tables

- `TB_MEMBER` - User/member information
- `TB_BOARD` - Bulletin board posts
- `TB_LECTURE` - Lecture information
- `TB_TEACHER` - Instructor information
- `TB_EXAM` - Examination data
- `TB_LOCKER` - Locker assignments
- `TB_BOOK` - Textbook catalog
- `TB_PRODUCT_ORDER` - Product orders
- `TB_COUPON` - Coupon management

### Extended Tables (New Modules)

**D-Day Management:**
- `TB_DDAY` - D-Day master table
- `TB_CATEGORY_INFO` - Category information

**Event Management:**
- `TB_EVENT_INFO` - Event master table
- `TB_EVENT_FILE` - Event attachments
- `TB_EVENT_OPTION1~4` - Event options (application, comments, SMS, popup)
- `TB_EVENT_RESULT` - Event participants
- `TB_LECTURE_EVENT_INFO` - Lecture event master
- `TB_LECTURE_EVENT_LIST` - Lecture event course list

**Affiliate Management:**
- `COOP_MST` - Partner/affiliate master
- `COOP_USE_IP` - Partner IP whitelist
- `TB_BOARD_MEMBERSHIP` - Affiliate board

**Counseling Management:**
- `COUNSEL_SCH` - Counseling schedule
- `COUNSEL_RST` - Counseling requests
- `COUNSEL_TS` - Time slot settings
- `TB_PRESENT` - Presentation requests

**Banner & Box:**
- `TB_BANNER_INFO` - Banner information
- `TB_BOX_INFO` - Box content management

DDL scripts available in `ddls/` directory.

## API Endpoints Summary

| Module | Endpoint Base | Controllers |
|--------|--------------|-------------|
| Lecture | `/api/lecture/*` | 11 APIs (category, teacher, subject, form, kind, series, lecture, mst, macaddress, open, event) |
| Exam | `/api/exam/*` | 2 APIs (exam, exambank) |
| Member | `/api/member/*` | 1 API |
| Board | `/api/board/*` | 1 API |
| Book | `/api/book/*` | 1 API |
| Product Order | `/api/productorder/*` | 2 APIs (coupon, order) |
| Locker | `/api/locker/*` | 1 API |
| Dashboard | `/api/dashboard/*` | 1 API |
| Login | `/api/login/*` | 1 API |
| Main | `/api/main/*` | 1 API |
| Menu | `/api/menu/*` | 1 API |
| Banner | `/api/banner/*` | 1 API |
| Box | `/api/box/*` | 1 API |
| Coop | `/api/coop/*` | 1 API (partner management, IP whitelist, board, orders) |
| Counsel | `/api/counsel/*` | 1 API (schedule, request, presentation) |
| D-Day | `/api/dday/*` | 1 API |
| Event | `/api/event/*` | 1 API (event, lecture event) |
| API Info | `/api/info/*` | 2 APIs (getApiList, getApiSummary) |
| Index | `/api/index/*` | 1 API (menu management, site menu) |
| FreeOrder | `/api/freeOrder/*` | 1 API (free lecture registration) |
| Gosi | `/api/gosi/*`, `/api/pub/*` | 2 APIs (gosi management, pub) |
| LectureOff | `/api/lectureOff/*` | 1 API (offline lecture management) |
| Manage | `/api/manage/*` | 3 APIs (categorySale, lectureYear, teacherCalculate) |

**Total**: 40+ REST API Controllers

## Troubleshooting

### Port Conflicts

If port 8080 is in use:
- Change port in `application.properties`: `server.port=8081`
- Or stop the conflicting process

### Database Connection Issues

- Verify MySQL is running
- Check connection details in `application.properties`
- Ensure `acm_basic` schema exists

### BOM Encoding Errors

If you see compilation errors with `\ufeff`:
- Re-save files as UTF-8 without BOM
- Common in: `Configurations.java`, `DBUtil.java`

## Contributors

[Add contributor information]

## Support

For issues and questions, please refer to the project issue tracker.