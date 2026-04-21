package com.academy.freeOrder.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.FreeOrderMapper;

/**
 * 무료 수강신청 관리 서비스 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            무료 수강신청 관리 신규 생성
 * </pre>
 */
@Service
public class FreeOrderService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final FreeOrderMapper freeOrderMapper;

    @Autowired
    public FreeOrderService(FreeOrderMapper freeOrderMapper) {
        this.freeOrderMapper = freeOrderMapper;
    }

    // =====================================================
    // 수강신청 관련
    // =====================================================

    /**
     * 수강신청 회원 목록 조회
     */
    public List<JSONObject> getMemberFreeOrderList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectMemberFreeOrderList(freeOrderVO);
    }

    /**
     * 수강신청 회원 목록 건수 조회
     */
    public int getMemberFreeOrderListCount(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectMemberFreeOrderListCount(freeOrderVO);
    }

    /**
     * 강의 마스터 정보 조회 (수강신청 등록 팝업용)
     */
    public JSONObject getLectureMstInfo(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectLectureMstInfo(freeOrderVO);
    }

    /**
     * 강의 마스터 정보 조회 (수강변경 팝업용 - 이전 강의)
     */
    public JSONObject getLectureMstInfoPrev(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectLectureMstInfoPrev(freeOrderVO);
    }

    // =====================================================
    // 강의선택 팝업 관련
    // =====================================================

    /**
     * 카테고리 목록 조회
     */
    public List<JSONObject> getCategoryList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectCategoryList(freeOrderVO);
    }

    /**
     * 학습형태 목록 조회
     */
    public List<JSONObject> getLearningFormList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectLearningFormList(freeOrderVO);
    }

    /**
     * 과목 목록 조회
     */
    public List<JSONObject> getSubjectList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectSubjectList(freeOrderVO);
    }

    /**
     * 강의선택 팝업 목록 조회
     */
    public List<JSONObject> getLectureListForFreeOrder(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectLectureListForFreeOrder(freeOrderVO);
    }

    /**
     * 강의선택 팝업 목록 건수 조회
     */
    public int getLectureListForFreeOrderCount(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectLectureListForFreeOrderCount(freeOrderVO);
    }

    // =====================================================
    // 수강신청 등록 관련
    // =====================================================

    /**
     * 주문번호 채번
     */
    public String getNextOrderNo(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectNextOrderNo(freeOrderVO);
    }

    /**
     * 사용자 존재여부 확인
     */
    public JSONObject getMemberExists(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectMemberExists(freeOrderVO);
    }

    /**
     * 수강신청 등록 처리 (단과)
     */
    @Transactional
    public void insertFreeOrder(FreeOrderVO freeOrderVO) {
        // 1. 주문관리번호 등록
        freeOrderMapper.insertOrderMgntNo(freeOrderVO);
        // 2. 주문 등록
        freeOrderMapper.insertOrders(freeOrderVO);
        // 3. 결제승인 등록
        freeOrderMapper.insertApprovals(freeOrderVO);
        // 4. 수강신청 등록
        freeOrderMapper.insertMyLecture(freeOrderVO);
    }

    /**
     * 수강신청 등록 처리 (종합반)
     */
    @Transactional
    public void insertFreeOrderPackage(FreeOrderVO freeOrderVO) {
        // 1. 주문관리번호 등록
        freeOrderMapper.insertOrderMgntNo(freeOrderVO);
        // 2. 주문 등록
        freeOrderMapper.insertOrders(freeOrderVO);
        // 3. 결제승인 등록
        freeOrderMapper.insertApprovals(freeOrderVO);
        // 4. 종합반 강좌 목록 조회 후 각 강좌별 수강신청 등록
        List<JSONObject> lectureList = freeOrderMapper.selectPackageLectureList(freeOrderVO);
        if (lectureList != null && !lectureList.isEmpty()) {
            for (JSONObject lecture : lectureList) {
                FreeOrderVO lectureVO = new FreeOrderVO();
                lectureVO.setOrderno(freeOrderVO.getOrderno());
                lectureVO.setUserid(freeOrderVO.getUserid());
                lectureVO.setMgntno(freeOrderVO.getMgntno());
                lectureVO.setLeccode((String) lecture.get("MST_LECCODE"));
                lectureVO.setStartDate(freeOrderVO.getStartDate());
                lectureVO.setSubjectPeriod(freeOrderVO.getSubjectPeriod());
                freeOrderMapper.insertMyLecture(lectureVO);
            }
        }
    }

    /**
     * 연회원패키지 등록 처리
     */
    @Transactional
    public void insertFreeOrderYearPackage(FreeOrderVO freeOrderVO) {
        // 1. 주문관리번호 등록
        freeOrderMapper.insertOrderMgntNo(freeOrderVO);
        // 2. 주문 등록
        freeOrderMapper.insertOrders(freeOrderVO);
        // 3. 결제승인 등록
        freeOrderMapper.insertApprovals(freeOrderVO);
        // 4. 연회원패키지 등록
        freeOrderMapper.insertOrderYearPackage(freeOrderVO);
    }

    /**
     * 종합반 강좌 목록 조회
     */
    public List<JSONObject> getPackageLectureList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectPackageLectureList(freeOrderVO);
    }

    // =====================================================
    // 수강변경 관련
    // =====================================================

    /**
     * 수강변경 목록 조회
     */
    public List<JSONObject> getChangeLectureList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectChangeLectureList(freeOrderVO);
    }

    /**
     * 수강변경 목록 건수 조회
     */
    public int getChangeLectureListCount(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectChangeLectureListCount(freeOrderVO);
    }

    /**
     * 수강변경 상세 목록 조회 (강의/도서 정보)
     */
    public List<JSONObject> getChangeViewList(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectChangeViewList(freeOrderVO);
    }

    /**
     * 수강변경 상세 목록 조회 (주문자 정보)
     */
    public JSONObject getChangeViewOrderInfo(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectChangeViewOrderInfo(freeOrderVO);
    }

    /**
     * 수강변경 처리
     */
    @Transactional
    public void updateChangeLecture(FreeOrderVO freeOrderVO) {
        // 1. 기존 주문 취소 처리
        freeOrderMapper.updateOrderCancel(freeOrderVO);
        // 2. 새 주문관리번호 등록
        freeOrderMapper.insertOrderMgntNo(freeOrderVO);
        // 3. 수강변경 이력 등록
        freeOrderMapper.insertRenewHistory(freeOrderVO);
        // 4. 새 수강신청 등록
        freeOrderMapper.insertMyLecture(freeOrderVO);
    }

    // =====================================================
    // 주문 상세 관련
    // =====================================================

    /**
     * 결제승인 상세 조회
     */
    public JSONObject getApprovalsDetail(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectApprovalsDetail(freeOrderVO);
    }

    /**
     * 배송정보 건수 조회
     */
    public int getDeliversCount(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectDeliversCount(freeOrderVO);
    }

    /**
     * 배송정보 상세 조회
     */
    public JSONObject getDeliversDetail(FreeOrderVO freeOrderVO) {
        return freeOrderMapper.selectDeliversDetail(freeOrderVO);
    }
}
