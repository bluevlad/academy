package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Lecture Value Object
 * TB_LEC_MST 테이블 매핑 VO
 */
public class LectureVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Lecture specific fields
    private Integer seq;                        // SEQ - 시퀀스
    private String leccode;                     // LECCODE - 강의 코드
    private String categoryCd;                  // CATEGORY_CD - 카테고리 코드
    private String learningCd;                  // LEARNING_CD - 학습형태 코드
    private String subjectTeacher;              // SUBJECT_TEACHER - 강사
    private String subjectTeacherPayment;       // SUBJECT_TEACHER_PAYMENT - 강사 급여
    private String orgLeccode;                  // ORG_LECCODE - 원본 강의 코드
    private String subjectTitle;                // SUBJECT_TITLE - 강의 제목
    private String subjectDesc;                 // SUBJECT_DESC - 강의 설명 (CLOB)
    private String subjectMemo;                 // SUBJECT_MEMO - 강의 메모 (CLOB)
    private String subjectKeyword;              // SUBJECT_KEYWORD - 키워드
    private String subjectPeriod;               // SUBJECT_PERIOD - 수강기간
    private String subjectOffOpenYear;          // SUBJECT_OFF_OPEN_YEAR - 오프라인 개강년도
    private String subjectOffOpenMonth;         // SUBJECT_OFF_OPEN_MONTH - 오프라인 개강월
    private String subjectOffOpenDay;           // SUBJECT_OFF_OPEN_DAY - 오프라인 개강일
    private String subjectDiscount;             // SUBJECT_DISCOUNT - 할인율
    private String subjectPrice;                // SUBJECT_PRICE - 가격
    private String subjectPoint;                // SUBJECT_POINT - 포인트
    private String subjectMovie;                // SUBJECT_MOVIE - 동영상
    private String subjectPmp;                  // SUBJECT_PMP - PMP
    private String subjectMoviePmp;             // SUBJECT_MOVIE_PMP - 동영상+PMP
    private String subjectMovieMp4;             // SUBJECT_MOVIE_MP4 - 동영상 MP4
    private String subjectMovieVodMp4;          // SUBJECT_MOVIE_VOD_MP4 - VOD MP4
    private String subjectOption;               // SUBJECT_OPTION - 옵션
    private String subjectIsuse;                // SUBJECT_ISUSE - 사용여부
    private String subjectSjtCd;                // SUBJECT_SJT_CD - 과목 코드
    private String subjectVodDefaultPath;       // SUBJECT_VOD_DEFAULT_PATH - VOD 기본 경로
    private String subjectWideDefaultPath;      // SUBJECT_WIDE_DEFAULT_PATH - WIDE 기본 경로
    private String subjectMp4DefaultPath;       // SUBJECT_MP4_DEFAULT_PATH - MP4 기본 경로
    private String subjectPmpDefaultPath;       // SUBJECT_PMP_DEFAULT_PATH - PMP 기본 경로
    private String subjectPass;                 // SUBJECT_PASS - 패스
    private String subjectJang;                 // SUBJECT_JANG - 장
    private String reCourse;                    // RE_COURSE - 재수강
    private String lecTypeChoice;               // LEC_TYPE_CHOICE - 강의 타입 선택
    private String lecGubun;                    // LEC_GUBUN - 강의 구분
    private String recGubun;                    // REC_GUBUN - 녹화 구분
    private String iconGubun;                   // ICON_GUBUN - 아이콘 구분
    private String lecSchedule;                 // LEC_SCHEDULE - 강의 일정
    private String lecCount;                    // LEC_COUNT - 강의 수
    private String plan;                        // PLAN - 계획 (CLOB)
    private String giftFlag;                    // GIFT_FLAG - 선물 플래그
    private String giftCouponCcode;             // GIFT_COUPON_CCODE - 선물 쿠폰 코드
    private String giftLeccode;                 // GIFT_LECCODE - 선물 강의 코드
    private String couponName;                  // COUPON_NAME - 쿠폰 이름
    private String giftName;                    // GIFT_NAME - 선물 이름
    private String teacherno;                   // TEACHERNO - 강사 번호
    private String moCouponName;                // MO_COUPON_NAME - 모바일 쿠폰 이름
    private String moCouponCcode;               // MO_COUPON_CCODE - 모바일 쿠폰 코드
    private String freeTab;                     // FREE_TAB - 무료 탭
    private String subjectMonitormode;          // SUBJECT_MONITORMODE - 모니터 모드
    private String mustPrfImg;                  // MUST_PRF_IMG - 필수 프로필 이미지
    private String selPrfImg;                   // SEL_PRF_IMG - 선택 프로필 이미지
    private String lecBaesu;                    // LEC_BAESU - 강의 배수
    // regDt, regId, updDt, updId, isUse inherited from CommonVO

    // Fields from LectureMstVO (integrated)
    private String mstcode;                     // MSTCODE - 마스터 코드
    private String bridgeLeccode;               // BRIDGE_LECCODE - 브릿지 마스터 코드
    private String subjectCd;                   // SUBJECT_CD - 과목 코드
    private String teacherCd;                   // TEACHER_CD - 강사 코드
    private String formCd;                      // FORM_CD - 학습형태 코드
    private String jseq;                        // JSEQ - J시퀀스
    private String movIng;                      // MOV_ING - 동영상 진행 중
    private String mstUseYn;                    // MST_USE_YN - 마스터 사용 여부
    private String teacherSubject;              // TEACHER_SUBJECT - 강사 과목
    private String orderno;                     // ORDERNO - 주문 번호
    private String orderId;                     // ORDERID - 주문 ID
    private String addBookArea;                 // ADDBOOKAREA - 추가 도서 영역
    private String addArea;                     // ADDAREA - 추가 영역
    private String mainBook;                    // MAIN_BOOK - 주교재
    private String subBook;                     // SUB_BOOK - 부교재
    private String studentBook;                 // STUDENT_BOOK - 학생교재
    private String subjectInfo;                 // SUBJECT_INFO - 강의 정보
    private String contents;                    // CONTENTS - 콘텐츠
    private String teacherNm;                   // TEACHER_NM - 강사명 (별칭)
    private String formNm;                      // FORM_NM - 학습형태명
    private String useYn;                       // USE_YN - 사용 여부
    private String isOpen;                      // IS_OPEN - 공개 여부

    // Additional fields for API (searchType, searchText, searchKind, searchGubn, updateFlag inherited from CommonVO)
    private String searchPayYn;                 // SEARCHPAYYN - 검색 결제 여부
    private String searchPayType;               // SEARCHPAYTYPE - 검색 결제 타입
    private String searchForm;                  // SEARCHFORM - 검색 형태
    private String searchYear;                  // SEARCHYEAR - 검색 연도
    private String searchOpenPage;              // SEARCHOPENPAGE - 검색 오픈 페이지
    private String searchCodeIsuse;             // SEARCHCODEISUSE - 검색 코드 사용여부
    private String rscId;                       // RSC_ID - 리소스 ID
    private String prefix;                      // PREFIX - 접두어
    private String flag;                        // FLAG - 플래그
    private String flag2;                       // FLAG2 - 플래그2
    private String bridgeLec;                   // BRIDGE_LEC - 브릿지 강의
    private String getCode;                     // GET_CODE - 코드 조회
    private String rcode;                       // RCODE - R코드
    private String[] categoryCdArr;             // CATEGORY_CD[] - 카테고리 코드 배열
    private String[] learningCdArr;             // LEARNING_CD[] - 학습형태 코드 배열
    private String[] juRscIdArr;                // JU_RSC_ID[] - 주교재 리소스 ID 배열
    private String[] buRscIdArr;                // BU_RSC_ID[] - 부교재 리소스 ID 배열
    private String[] suRscIdArr;                // SU_RSC_ID[] - 학생교재 리소스 ID 배열
    private String[] delArr;                    // DEL_ARR - 삭제 배열

    // Display fields (조인 결과용)
    private String categoryNm;                  // CATEGORY_NM - 카테고리명
    private String subjectNm;                   // SUBJECT_NM - 과목명
    private String subjectTeacherNm;            // SUBJECT_TEACHER_NM - 강사명
    private String learningNm;                  // LEARNING_NM - 학습형태명
    private String isUseNm;                     // ISUSENM - 사용여부명

    // Getters and Setters
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectTeacherPayment() {
        return subjectTeacherPayment;
    }

    public void setSubjectTeacherPayment(String subjectTeacherPayment) {
        this.subjectTeacherPayment = subjectTeacherPayment;
    }

    public String getOrgLeccode() {
        return orgLeccode;
    }

    public void setOrgLeccode(String orgLeccode) {
        this.orgLeccode = orgLeccode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectMemo() {
        return subjectMemo;
    }

    public void setSubjectMemo(String subjectMemo) {
        this.subjectMemo = subjectMemo;
    }

    public String getSubjectKeyword() {
        return subjectKeyword;
    }

    public void setSubjectKeyword(String subjectKeyword) {
        this.subjectKeyword = subjectKeyword;
    }

    public String getSubjectPeriod() {
        return subjectPeriod;
    }

    public void setSubjectPeriod(String subjectPeriod) {
        this.subjectPeriod = subjectPeriod;
    }

    public String getSubjectOffOpenYear() {
        return subjectOffOpenYear;
    }

    public void setSubjectOffOpenYear(String subjectOffOpenYear) {
        this.subjectOffOpenYear = subjectOffOpenYear;
    }

    public String getSubjectOffOpenMonth() {
        return subjectOffOpenMonth;
    }

    public void setSubjectOffOpenMonth(String subjectOffOpenMonth) {
        this.subjectOffOpenMonth = subjectOffOpenMonth;
    }

    public String getSubjectOffOpenDay() {
        return subjectOffOpenDay;
    }

    public void setSubjectOffOpenDay(String subjectOffOpenDay) {
        this.subjectOffOpenDay = subjectOffOpenDay;
    }

    public String getSubjectDiscount() {
        return subjectDiscount;
    }

    public void setSubjectDiscount(String subjectDiscount) {
        this.subjectDiscount = subjectDiscount;
    }

    public String getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(String subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public String getSubjectPoint() {
        return subjectPoint;
    }

    public void setSubjectPoint(String subjectPoint) {
        this.subjectPoint = subjectPoint;
    }

    public String getSubjectMovie() {
        return subjectMovie;
    }

    public void setSubjectMovie(String subjectMovie) {
        this.subjectMovie = subjectMovie;
    }

    public String getSubjectPmp() {
        return subjectPmp;
    }

    public void setSubjectPmp(String subjectPmp) {
        this.subjectPmp = subjectPmp;
    }

    public String getSubjectMoviePmp() {
        return subjectMoviePmp;
    }

    public void setSubjectMoviePmp(String subjectMoviePmp) {
        this.subjectMoviePmp = subjectMoviePmp;
    }

    public String getSubjectMovieMp4() {
        return subjectMovieMp4;
    }

    public void setSubjectMovieMp4(String subjectMovieMp4) {
        this.subjectMovieMp4 = subjectMovieMp4;
    }

    public String getSubjectMovieVodMp4() {
        return subjectMovieVodMp4;
    }

    public void setSubjectMovieVodMp4(String subjectMovieVodMp4) {
        this.subjectMovieVodMp4 = subjectMovieVodMp4;
    }

    public String getSubjectOption() {
        return subjectOption;
    }

    public void setSubjectOption(String subjectOption) {
        this.subjectOption = subjectOption;
    }

    public String getSubjectIsuse() {
        return subjectIsuse;
    }

    public void setSubjectIsuse(String subjectIsuse) {
        this.subjectIsuse = subjectIsuse;
    }

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getSubjectVodDefaultPath() {
        return subjectVodDefaultPath;
    }

    public void setSubjectVodDefaultPath(String subjectVodDefaultPath) {
        this.subjectVodDefaultPath = subjectVodDefaultPath;
    }

    public String getSubjectWideDefaultPath() {
        return subjectWideDefaultPath;
    }

    public void setSubjectWideDefaultPath(String subjectWideDefaultPath) {
        this.subjectWideDefaultPath = subjectWideDefaultPath;
    }

    public String getSubjectMp4DefaultPath() {
        return subjectMp4DefaultPath;
    }

    public void setSubjectMp4DefaultPath(String subjectMp4DefaultPath) {
        this.subjectMp4DefaultPath = subjectMp4DefaultPath;
    }

    public String getSubjectPmpDefaultPath() {
        return subjectPmpDefaultPath;
    }

    public void setSubjectPmpDefaultPath(String subjectPmpDefaultPath) {
        this.subjectPmpDefaultPath = subjectPmpDefaultPath;
    }

    public String getSubjectPass() {
        return subjectPass;
    }

    public void setSubjectPass(String subjectPass) {
        this.subjectPass = subjectPass;
    }

    public String getSubjectJang() {
        return subjectJang;
    }

    public void setSubjectJang(String subjectJang) {
        this.subjectJang = subjectJang;
    }

    public String getReCourse() {
        return reCourse;
    }

    public void setReCourse(String reCourse) {
        this.reCourse = reCourse;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getLecGubun() {
        return lecGubun;
    }

    public void setLecGubun(String lecGubun) {
        this.lecGubun = lecGubun;
    }

    public String getRecGubun() {
        return recGubun;
    }

    public void setRecGubun(String recGubun) {
        this.recGubun = recGubun;
    }

    public String getIconGubun() {
        return iconGubun;
    }

    public void setIconGubun(String iconGubun) {
        this.iconGubun = iconGubun;
    }

    public String getLecSchedule() {
        return lecSchedule;
    }

    public void setLecSchedule(String lecSchedule) {
        this.lecSchedule = lecSchedule;
    }

    public String getLecCount() {
        return lecCount;
    }

    public void setLecCount(String lecCount) {
        this.lecCount = lecCount;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getGiftFlag() {
        return giftFlag;
    }

    public void setGiftFlag(String giftFlag) {
        this.giftFlag = giftFlag;
    }

    public String getGiftCouponCcode() {
        return giftCouponCcode;
    }

    public void setGiftCouponCcode(String giftCouponCcode) {
        this.giftCouponCcode = giftCouponCcode;
    }

    public String getGiftLeccode() {
        return giftLeccode;
    }

    public void setGiftLeccode(String giftLeccode) {
        this.giftLeccode = giftLeccode;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno;
    }

    public String getMoCouponName() {
        return moCouponName;
    }

    public void setMoCouponName(String moCouponName) {
        this.moCouponName = moCouponName;
    }

    public String getMoCouponCcode() {
        return moCouponCcode;
    }

    public void setMoCouponCcode(String moCouponCcode) {
        this.moCouponCcode = moCouponCcode;
    }

    public String getFreeTab() {
        return freeTab;
    }

    public void setFreeTab(String freeTab) {
        this.freeTab = freeTab;
    }

    public String getSubjectMonitormode() {
        return subjectMonitormode;
    }

    public void setSubjectMonitormode(String subjectMonitormode) {
        this.subjectMonitormode = subjectMonitormode;
    }

    public String getMustPrfImg() {
        return mustPrfImg;
    }

    public void setMustPrfImg(String mustPrfImg) {
        this.mustPrfImg = mustPrfImg;
    }

    public String getSelPrfImg() {
        return selPrfImg;
    }

    public void setSelPrfImg(String selPrfImg) {
        this.selPrfImg = selPrfImg;
    }

    public String getLecBaesu() {
        return lecBaesu;
    }

    public void setLecBaesu(String lecBaesu) {
        this.lecBaesu = lecBaesu;
    }

    // Display fields getters and setters
    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getSubjectTeacherNm() {
        return subjectTeacherNm;
    }

    public void setSubjectTeacherNm(String subjectTeacherNm) {
        this.subjectTeacherNm = subjectTeacherNm;
    }

    public String getLearningNm() {
        return learningNm;
    }

    public void setLearningNm(String learningNm) {
        this.learningNm = learningNm;
    }

    public String getBridgeLeccode() {
        return bridgeLeccode;
    }

    public void setBridgeLeccode(String bridgeLeccode) {
        this.bridgeLeccode = bridgeLeccode;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    // Additional fields getters and setters (searchType, searchText, searchKind, searchGubn, updateFlag inherited from CommonVO)
    public String getSearchPayYn() {
        return searchPayYn;
    }

    public void setSearchPayYn(String searchPayYn) {
        this.searchPayYn = searchPayYn;
    }

    public String getSearchPayType() {
        return searchPayType;
    }

    public void setSearchPayType(String searchPayType) {
        this.searchPayType = searchPayType;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchOpenPage() {
        return searchOpenPage;
    }

    public void setSearchOpenPage(String searchOpenPage) {
        this.searchOpenPage = searchOpenPage;
    }

    public String getSearchCodeIsuse() {
        return searchCodeIsuse;
    }

    public void setSearchCodeIsuse(String searchCodeIsuse) {
        this.searchCodeIsuse = searchCodeIsuse;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    public String getBridgeLec() {
        return bridgeLec;
    }

    public void setBridgeLec(String bridgeLec) {
        this.bridgeLec = bridgeLec;
    }

    public String getGetCode() {
        return getCode;
    }

    public void setGetCode(String getCode) {
        this.getCode = getCode;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String[] getCategoryCdArr() {
        return categoryCdArr;
    }

    public void setCategoryCdArr(String[] categoryCdArr) {
        this.categoryCdArr = categoryCdArr;
    }

    public String[] getLearningCdArr() {
        return learningCdArr;
    }

    public void setLearningCdArr(String[] learningCdArr) {
        this.learningCdArr = learningCdArr;
    }

    public String[] getJuRscIdArr() {
        return juRscIdArr;
    }

    public void setJuRscIdArr(String[] juRscIdArr) {
        this.juRscIdArr = juRscIdArr;
    }

    public String[] getBuRscIdArr() {
        return buRscIdArr;
    }

    public void setBuRscIdArr(String[] buRscIdArr) {
        this.buRscIdArr = buRscIdArr;
    }

    public String[] getSuRscIdArr() {
        return suRscIdArr;
    }

    public void setSuRscIdArr(String[] suRscIdArr) {
        this.suRscIdArr = suRscIdArr;
    }

    public String[] getDelArr() {
        return delArr;
    }

    public void setDelArr(String[] delArr) {
        this.delArr = delArr;
    }

    // Getters and Setters for fields from LectureMstVO
    public String getMstcode() {
        return mstcode;
    }

    public void setMstcode(String mstcode) {
        this.mstcode = mstcode;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getTeacherCd() {
        return teacherCd;
    }

    public void setTeacherCd(String teacherCd) {
        this.teacherCd = teacherCd;
    }

    public String getFormCd() {
        return formCd;
    }

    public void setFormCd(String formCd) {
        this.formCd = formCd;
    }

    public String getJseq() {
        return jseq;
    }

    public void setJseq(String jseq) {
        this.jseq = jseq;
    }

    public String getMovIng() {
        return movIng;
    }

    public void setMovIng(String movIng) {
        this.movIng = movIng;
    }

    public String getMstUseYn() {
        return mstUseYn;
    }

    public void setMstUseYn(String mstUseYn) {
        this.mstUseYn = mstUseYn;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddBookArea() {
        return addBookArea;
    }

    public void setAddBookArea(String addBookArea) {
        this.addBookArea = addBookArea;
    }

    public String getAddArea() {
        return addArea;
    }

    public void setAddArea(String addArea) {
        this.addArea = addArea;
    }

    public String getMainBook() {
        return mainBook;
    }

    public void setMainBook(String mainBook) {
        this.mainBook = mainBook;
    }

    public String getSubBook() {
        return subBook;
    }

    public void setSubBook(String subBook) {
        this.subBook = subBook;
    }

    public String getStudentBook() {
        return studentBook;
    }

    public void setStudentBook(String studentBook) {
        this.studentBook = studentBook;
    }

    public String getSubjectInfo() {
        return subjectInfo;
    }

    public void setSubjectInfo(String subjectInfo) {
        this.subjectInfo = subjectInfo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getFormNm() {
        return formNm;
    }

    public void setFormNm(String formNm) {
        this.formNm = formNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}