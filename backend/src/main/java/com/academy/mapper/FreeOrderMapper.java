package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.freeOrder.service.FreeOrderVO;

/**
 * 무료 수강신청 관리에 관한 데이터 접근 클래스를 정의한다.
 * @author system
 * @since 2025.12.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.11         system          신규 생성
 * </pre>
 */
@Mapper
public interface FreeOrderMapper {

    // =====================================================
    // 수강신청 관련
    // =====================================================

    /**
     * 수강신청 회원 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 수강신청 회원 목록
     */
    List<JSONObject> selectMemberFreeOrderList(FreeOrderVO freeOrderVO);

    /**
     * 수강신청 회원 목록 건수 조회
     * @param freeOrderVO 검색조건
     * @return int 수강신청 회원 목록 건수
     */
    int selectMemberFreeOrderListCount(FreeOrderVO freeOrderVO);

    /**
     * 강의 마스터 정보 조회 (수강신청 등록 팝업용)
     * @param freeOrderVO 검색조건
     * @return JSONObject 강의 마스터 정보
     */
    JSONObject selectLectureMstInfo(FreeOrderVO freeOrderVO);

    /**
     * 강의 마스터 정보 조회 (수강변경 팝업용 - 이전 강의)
     * @param freeOrderVO 검색조건
     * @return JSONObject 강의 마스터 정보
     */
    JSONObject selectLectureMstInfoPrev(FreeOrderVO freeOrderVO);

    // =====================================================
    // 강의선택 팝업 관련
    // =====================================================

    /**
     * 카테고리 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 카테고리 목록
     */
    List<JSONObject> selectCategoryList(FreeOrderVO freeOrderVO);

    /**
     * 학습형태 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 학습형태 목록
     */
    List<JSONObject> selectLearningFormList(FreeOrderVO freeOrderVO);

    /**
     * 과목 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 과목 목록
     */
    List<JSONObject> selectSubjectList(FreeOrderVO freeOrderVO);

    /**
     * 강의선택 팝업 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 강의 목록
     */
    List<JSONObject> selectLectureListForFreeOrder(FreeOrderVO freeOrderVO);

    /**
     * 강의선택 팝업 목록 건수 조회
     * @param freeOrderVO 검색조건
     * @return int 강의 목록 건수
     */
    int selectLectureListForFreeOrderCount(FreeOrderVO freeOrderVO);

    // =====================================================
    // 수강신청 등록 관련
    // =====================================================

    /**
     * 주문번호 채번
     * @param freeOrderVO 채번 조건
     * @return String 주문번호
     */
    String selectNextOrderNo(FreeOrderVO freeOrderVO);

    /**
     * 사용자 존재여부 확인
     * @param freeOrderVO 검색조건
     * @return JSONObject 사용자 정보
     */
    JSONObject selectMemberExists(FreeOrderVO freeOrderVO);

    /**
     * 주문관리번호 등록 (tb_order_mgnt_no)
     * @param freeOrderVO 주문 정보
     */
    void insertOrderMgntNo(FreeOrderVO freeOrderVO);

    /**
     * 주문 등록 (tb_orders)
     * @param freeOrderVO 주문 정보
     */
    void insertOrders(FreeOrderVO freeOrderVO);

    /**
     * 결제승인 등록 (tb_approvals)
     * @param freeOrderVO 결제 정보
     */
    void insertApprovals(FreeOrderVO freeOrderVO);

    /**
     * 수강신청 등록 (TB_MYLECTURE)
     * @param freeOrderVO 수강신청 정보
     */
    void insertMyLecture(FreeOrderVO freeOrderVO);

    /**
     * 연회원패키지 등록 (TB_ORDER_YEARPACKAGE)
     * @param freeOrderVO 수강신청 정보
     */
    void insertOrderYearPackage(FreeOrderVO freeOrderVO);

    /**
     * 종합반 강좌 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 강좌 목록
     */
    List<JSONObject> selectPackageLectureList(FreeOrderVO freeOrderVO);

    // =====================================================
    // 수강변경 관련
    // =====================================================

    /**
     * 수강변경 목록 조회
     * @param freeOrderVO 검색조건
     * @return List 수강변경 목록
     */
    List<JSONObject> selectChangeLectureList(FreeOrderVO freeOrderVO);

    /**
     * 수강변경 목록 건수 조회
     * @param freeOrderVO 검색조건
     * @return int 수강변경 목록 건수
     */
    int selectChangeLectureListCount(FreeOrderVO freeOrderVO);

    /**
     * 수강변경 상세 목록 조회 (강의/도서 정보)
     * @param freeOrderVO 검색조건
     * @return List 수강변경 상세 목록
     */
    List<JSONObject> selectChangeViewList(FreeOrderVO freeOrderVO);

    /**
     * 수강변경 상세 목록 조회 (주문자 정보)
     * @param freeOrderVO 검색조건
     * @return JSONObject 수강변경 상세 정보
     */
    JSONObject selectChangeViewOrderInfo(FreeOrderVO freeOrderVO);

    /**
     * 주문 취소 처리 (수강변경 시)
     * @param freeOrderVO 주문 정보
     */
    void updateOrderCancel(FreeOrderVO freeOrderVO);

    /**
     * 수강변경 이력 등록 (TB_RENEW_HISTORY)
     * @param freeOrderVO 변경 이력 정보
     */
    void insertRenewHistory(FreeOrderVO freeOrderVO);

    // =====================================================
    // 주문 상세 관련
    // =====================================================

    /**
     * 결제승인 상세 조회
     * @param freeOrderVO 검색조건
     * @return JSONObject 결제승인 상세 정보
     */
    JSONObject selectApprovalsDetail(FreeOrderVO freeOrderVO);

    /**
     * 배송정보 건수 조회
     * @param freeOrderVO 검색조건
     * @return int 배송정보 건수
     */
    int selectDeliversCount(FreeOrderVO freeOrderVO);

    /**
     * 배송정보 상세 조회
     * @param freeOrderVO 검색조건
     * @return JSONObject 배송정보 상세
     */
    JSONObject selectDeliversDetail(FreeOrderVO freeOrderVO);
}
