package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.bookOrder.service.BookOrderVO;

/**
 * BookOrder Mapper Interface
 * 교재 배송/매출 관리 MyBatis Mapper
 */
@Mapper
public interface BookOrderMapper {

    // =====================================================
    // 교재배송 리스트 (택배)
    // =====================================================

    /**
     * 교재배송 리스트 (택배) 조회
     */
    List<HashMap<String, Object>> getJuOrderListDB(BookOrderVO vo);

    /**
     * 교재배송 리스트 (택배) 총 건수
     */
    int getJuOrderListCount(BookOrderVO vo);

    /**
     * 교재배송 2번째 리스트 (택배/직접수령)
     */
    List<HashMap<String, Object>> getTblJuOrderMgntListDB(BookOrderVO vo);

    /**
     * 엑셀 리스트
     */
    List<HashMap<String, Object>> getJuOrderExelListDB(BookOrderVO vo);

    // =====================================================
    // 전체상품주문관리
    // =====================================================

    /**
     * 전체상품주문관리 주문번호 상세 (승인정보)
     */
    List<HashMap<String, Object>> getTblApprovalsViewList(BookOrderVO vo);

    /**
     * 전체상품배송관리 주문건수
     */
    int getTblDeliversViewListCount(BookOrderVO vo);

    /**
     * 전체상품배송관리 리스트
     */
    List<HashMap<String, Object>> getTblDeliversViewList(BookOrderVO vo);

    /**
     * 전체상품주문관리 주문번호 상세 (주문정보)
     */
    List<HashMap<String, Object>> getTblOrdersViewList(BookOrderVO vo);

    /**
     * 전체상품주문관리 강의 상세
     */
    List<HashMap<String, Object>> getLecMstViewList(BookOrderVO vo);

    // =====================================================
    // 수정/업데이트
    // =====================================================

    /**
     * 배송정보 수정
     */
    int updateDelivers(BookOrderVO vo);

    /**
     * 승인정보 수정
     */
    int updateApprovals(BookOrderVO vo);

    /**
     * WMV/PMP 구분 수정
     */
    int updateDeliversWmv(BookOrderVO vo);

    /**
     * 금액 수정 1
     */
    int updateMoney1(BookOrderVO vo);

    /**
     * 금액 합계 조회
     */
    int getMoneySum(BookOrderVO vo);

    /**
     * 금액 수정 2
     */
    int updateMoney2(BookOrderVO vo);

    /**
     * 환불 삭제
     */
    int refund_money_delete(BookOrderVO vo);

    /**
     * 송장저장 1
     */
    int updateSendNo1(BookOrderVO vo);

    /**
     * 송장저장 2
     */
    int updateSendNo2(BookOrderVO vo);

    // =====================================================
    // 교재배송관리 팝업
    // =====================================================

    /**
     * 교재배송관리 'P' 팝업 리스트
     */
    List<HashMap<String, Object>> getPopPrintView(BookOrderVO vo);

    /**
     * 교재배송관리 'P' 팝업 정보
     */
    List<HashMap<String, Object>> getPopPrintInfoView(BookOrderVO vo);

    /**
     * 교재배송관리 'P' 팝업 주문정보
     */
    List<HashMap<String, Object>> getPopPrintOrderInfoView(BookOrderVO vo);

    // =====================================================
    // 교재배송 리스트 (직접수령)
    // =====================================================

    /**
     * 교재배송 리스트 (직접수령) 조회
     */
    List<HashMap<String, Object>> getJicOrderListDB(BookOrderVO vo);

    /**
     * 교재배송 리스트 (직접수령) 총 건수
     */
    int getJicOrderListCount(BookOrderVO vo);

    /**
     * 수령완료 1
     */
    int updateSendNo3(BookOrderVO vo);

    /**
     * 수령완료 2
     */
    int updateSendNo4(BookOrderVO vo);

    // =====================================================
    // 교재매출내역
    // =====================================================

    /**
     * 교재매출내역 리스트 조회
     */
    List<HashMap<String, Object>> getTolOrderListDB(BookOrderVO vo);

    /**
     * 교재매출내역 리스트 총 건수
     */
    int getTolOrderListCount(BookOrderVO vo);

    /**
     * 교재매출내역 리스트 2번째 리스트
     */
    List<HashMap<String, Object>> getPutLecMstDB(BookOrderVO vo);

    /**
     * 교재매출내역 엑셀 리스트
     */
    List<HashMap<String, Object>> getTolOrderExelListDB(BookOrderVO vo);

    // =====================================================
    // 경찰 택배정보 조회 (_COP)
    // =====================================================

    /**
     * 경찰 교재배송 리스트 (택배)
     */
    List<HashMap<String, Object>> getJuOrderListDB_COP(BookOrderVO vo);

    /**
     * 경찰 교재배송 리스트 (택배) 총 건수
     */
    int getJuOrderListCount_COP(BookOrderVO vo);

    /**
     * 경찰 교재배송 2번째 리스트
     */
    List<HashMap<String, Object>> getTblJuOrderMgntListDB_COP(BookOrderVO vo);

    /**
     * 경찰 엑셀 리스트
     */
    List<HashMap<String, Object>> getJuOrderExelListDB_COP(BookOrderVO vo);

    /**
     * 경찰 송장저장 1
     */
    int updateSendNo1_COP(BookOrderVO vo);

    /**
     * 경찰 송장저장 2
     */
    int updateSendNo2_COP(BookOrderVO vo);

    /**
     * 경찰 강의 상세
     */
    List<HashMap<String, Object>> getLecMstViewList_COP(BookOrderVO vo);

    /**
     * 경찰 배송 상세
     */
    List<HashMap<String, Object>> getTblDeliversViewList_COP(BookOrderVO vo);
}
