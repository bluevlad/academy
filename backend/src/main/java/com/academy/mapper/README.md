# Mapper 패키지

MyBatis 매퍼 인터페이스를 모아둔 패키지입니다.

## 패키지 구조

```
mapper/
├── AdminAuthMapper.java          # 관리자 권한 매퍼
├── AdminCodeMapper.java          # 관리자 코드 매퍼
├── AdminMenuMapper.java          # 관리자 메뉴 매퍼
├── BannerMapper.java             # 배너 매퍼
├── BoardManagementMapper.java    # 게시판 관리 매퍼
├── BoardMapper.java              # 게시판 매퍼
├── BoardNotAnswerMapper.java     # 미답변 게시판 매퍼
├── BoardTeacherMapper.java       # 강사 게시판 매퍼
├── BookMapper.java               # 교재 매퍼
├── BookCmmtMapper.java           # 교재 구매 후기 매퍼
├── BookOrderMapper.java          # 교재 주문 매퍼
├── BoxMapper.java                # 박스 매퍼
├── CategoryMapper.java           # 카테고리 매퍼
├── CategorySaleMapper.java       # 카테고리별 매출 매퍼
├── CategorySeriesMapper.java     # 카테고리 시리즈 매퍼
├── CmmUseMapper.java             # 공통 사용 매퍼
├── CoopMapper.java               # 제휴사 매퍼
├── CounselMapper.java            # 상담 매퍼
├── CouponMngMapper.java          # 쿠폰 관리 매퍼
├── DashBoardMapper.java          # 대시보드 매퍼
├── DdayMapper.java               # D-Day 매퍼
├── EventMapper.java              # 이벤트 매퍼
├── ExamMapper.java               # 시험 매퍼
├── ExamBankMapper.java           # 문제은행 매퍼
├── FormMapper.java               # 학습형태 매퍼
├── FreeOrderMapper.java          # 무료 수강신청 매퍼
├── GosiMapper.java               # 고시 매퍼
├── IndexMapper.java              # 인덱스/메뉴 매퍼
├── KindMapper.java               # 종류 매퍼
├── LectureFeesMapper.java        # 강의료 매퍼
├── LectureMapper.java            # 강의 매퍼
├── LectureMstMapper.java         # 강의 마스터 매퍼
├── LectureOffMapper.java         # 오프라인 강의 매퍼
├── LectureYearMapper.java        # 연도별 강의 매퍼
├── LockerMapper.java             # 사물함 매퍼
├── LoginMapper.java              # 로그인 매퍼
├── MacAddressManagerMapper.java  # MAC 주소 관리 매퍼
├── MainMapper.java               # 메인 매퍼
├── MemberMapper.java             # 회원 매퍼
├── MenuMapper.java               # 메뉴 매퍼
├── MockBoardMapper.java          # 모의고사 게시판 매퍼
├── MouiApplyMapper.java          # 모의고사 신청 매퍼
├── MouiExamMapper.java           # 모의고사 시험 매퍼
├── NoteMapper.java               # 노트 매퍼
├── OffExamMapper.java            # 오프라인 시험 매퍼
├── OffExamRegMapper.java         # 오프라인 시험 등록 매퍼
├── OpenLectureMapper.java        # 오픈 강의 매퍼
├── PersonStatsMapper.java        # 개인 통계 매퍼
├── PopupMapper.java              # 팝업 매퍼
├── ProductEventMapper.java       # 상품 이벤트 매퍼
├── ProductOrderMapper.java       # 상품 주문 매퍼
├── PubMapper.java                # 공지/게시판 매퍼
├── SalesStatMapper.java          # 매출 통계 매퍼
├── SeriesMapper.java             # 시리즈 매퍼
├── SubjectMapper.java            # 과목 매퍼
├── SurveyMapper.java             # 설문조사 매퍼
├── TeacherCalculateMapper.java   # 강사 정산 매퍼
├── TeacherMapper.java            # 강사 매퍼
└── TotalStatsMapper.java         # 전체 통계 매퍼
```

## 주요 특징

### MyBatis 매퍼 인터페이스
- `@Mapper` 애노테이션을 사용하여 자동 스캔
- XML 매퍼 파일과 1:1 매핑
- Type-safe한 데이터베이스 액세스

### 네이밍 규칙
- 인터페이스명: `{Domain}Mapper.java`
- XML 파일명: `{domain}SQL.xml` (resources/mapper)
- 메소드명: 명확한 동작을 나타내는 동사 + 명사

## 아키텍처

```
[Service Layer]
   ↓
[Mapper Interface] - Java Interface with @Mapper
   ↓
[MyBatis Framework]
   ↓
[XML Mapper File] - SQL definitions (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// Mapper 인터페이스 정의
@Mapper
public interface BookMapper {
    List<HashMap<String, Object>> bookList(BookVO bookVO);
    int bookListCount(BookVO bookVO);
    HashMap<String, Object> bookView(BookVO bookVO);
    void bookInsert(BookVO bookVO);
    void bookUpdate(BookVO bookVO);
    void bookDelete(BookVO bookVO);
}

// Service에서 사용
@Service
public class BookService implements Serializable {
    private final BookMapper bookMapper;
    
    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }
    
    public List<HashMap<String, Object>> bookList(BookVO bookVO) {
        return bookMapper.bookList(bookVO);
    }
}
```

## 참고사항

- 모든 매퍼는 `@Mapper` 애노테이션을 사용합니다
- XML 매퍼 파일은 `src/main/resources/mapper/` 디렉토리에 위치합니다
- Namespace는 매퍼 인터페이스의 풀 패키지명과 일치해야 합니다
- 복잡한 쿼리는 XML에서 동적 SQL을 사용합니다
- VO 객체를 파라미터로 받아 타입 안정성을 확보합니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
