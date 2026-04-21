package com.academy.bookOrder.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.BookOrderMapper;

/**
 * BookOrder Service
 * 교재 배송/매출 관리 서비스
 */
@Service
public class BookOrderService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BookOrderMapper bookOrderMapper;

    @Autowired
    public BookOrderService(BookOrderMapper bookOrderMapper) {
        this.bookOrderMapper = bookOrderMapper;
    }

    // =====================================================
    // 교재배송 리스트 (택배)
    // =====================================================

    /**
     * 교재배송 리스트 (택배) 조회
     */
    public List<HashMap<String, Object>> getJuOrderListDB(BookOrderVO vo) {
        return bookOrderMapper.getJuOrderListDB(vo);
    }

    /**
     * 교재배송 리스트 (택배) 총 건수
     */
    public int getJuOrderListCount(BookOrderVO vo) {
        return bookOrderMapper.getJuOrderListCount(vo);
    }

    /**
     * 교재배송 2번째 리스트 (택배/직접수령)
     */
    public List<HashMap<String, Object>> getTblJuOrderMgntListDB(BookOrderVO vo) {
        return bookOrderMapper.getTblJuOrderMgntListDB(vo);
    }

    /**
     * 엑셀 리스트
     */
    public List<HashMap<String, Object>> getJuOrderExelListDB(BookOrderVO vo) {
        return bookOrderMapper.getJuOrderExelListDB(vo);
    }

    // =====================================================
    // 전체상품주문관리
    // =====================================================

    /**
     * 전체상품주문관리 주문번호 상세 (승인정보)
     */
    public List<HashMap<String, Object>> getTblApprovalsViewList(BookOrderVO vo) {
        return bookOrderMapper.getTblApprovalsViewList(vo);
    }

    /**
     * 전체상품배송관리 주문건수
     */
    public int getTblDeliversViewListCount(BookOrderVO vo) {
        return bookOrderMapper.getTblDeliversViewListCount(vo);
    }

    /**
     * 전체상품배송관리 리스트
     */
    public List<HashMap<String, Object>> getTblDeliversViewList(BookOrderVO vo) {
        return bookOrderMapper.getTblDeliversViewList(vo);
    }

    /**
     * 전체상품주문관리 주문번호 상세 (주문정보)
     */
    public List<HashMap<String, Object>> getTblOrdersViewList(BookOrderVO vo) {
        return bookOrderMapper.getTblOrdersViewList(vo);
    }

    /**
     * 전체상품주문관리 강의 상세
     */
    public List<HashMap<String, Object>> getLecMstViewList(BookOrderVO vo) {
        return bookOrderMapper.getLecMstViewList(vo);
    }

    // =====================================================
    // 수정/업데이트
    // =====================================================

    /**
     * 배송정보 수정
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateDelivers(BookOrderVO vo) {
        return bookOrderMapper.updateDelivers(vo);
    }

    /**
     * 승인정보 수정
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateApprovals(BookOrderVO vo) {
        return bookOrderMapper.updateApprovals(vo);
    }

    /**
     * WMV/PMP 구분 수정
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateDeliversWmv(BookOrderVO vo) {
        return bookOrderMapper.updateDeliversWmv(vo);
    }

    /**
     * 금액 수정 1
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateMoney1(BookOrderVO vo) {
        return bookOrderMapper.updateMoney1(vo);
    }

    /**
     * 금액 합계 조회
     */
    public int getMoneySum(BookOrderVO vo) {
        return bookOrderMapper.getMoneySum(vo);
    }

    /**
     * 금액 수정 2
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateMoney2(BookOrderVO vo) {
        return bookOrderMapper.updateMoney2(vo);
    }

    /**
     * 환불 삭제
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int refundMoneyDelete(BookOrderVO vo) {
        return bookOrderMapper.refund_money_delete(vo);
    }

    /**
     * 송장저장 1
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateSendNo1(BookOrderVO vo) {
        return bookOrderMapper.updateSendNo1(vo);
    }

    /**
     * 송장저장 2
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateSendNo2(BookOrderVO vo) {
        return bookOrderMapper.updateSendNo2(vo);
    }

    // =====================================================
    // 교재배송관리 팝업
    // =====================================================

    /**
     * 교재배송관리 'P' 팝업 리스트
     */
    public List<HashMap<String, Object>> getPopPrintView(BookOrderVO vo) {
        return bookOrderMapper.getPopPrintView(vo);
    }

    /**
     * 교재배송관리 'P' 팝업 정보
     */
    public List<HashMap<String, Object>> getPopPrintInfoView(BookOrderVO vo) {
        return bookOrderMapper.getPopPrintInfoView(vo);
    }

    /**
     * 교재배송관리 'P' 팝업 주문정보
     */
    public List<HashMap<String, Object>> getPopPrintOrderInfoView(BookOrderVO vo) {
        return bookOrderMapper.getPopPrintOrderInfoView(vo);
    }

    // =====================================================
    // 교재배송 리스트 (직접수령)
    // =====================================================

    /**
     * 교재배송 리스트 (직접수령) 조회
     */
    public List<HashMap<String, Object>> getJicOrderListDB(BookOrderVO vo) {
        return bookOrderMapper.getJicOrderListDB(vo);
    }

    /**
     * 교재배송 리스트 (직접수령) 총 건수
     */
    public int getJicOrderListCount(BookOrderVO vo) {
        return bookOrderMapper.getJicOrderListCount(vo);
    }

    /**
     * 수령완료 1
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateSendNo3(BookOrderVO vo) {
        return bookOrderMapper.updateSendNo3(vo);
    }

    /**
     * 수령완료 2
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateSendNo4(BookOrderVO vo) {
        return bookOrderMapper.updateSendNo4(vo);
    }

    // =====================================================
    // 교재매출내역
    // =====================================================

    /**
     * 교재매출내역 리스트 조회
     */
    public List<HashMap<String, Object>> getTolOrderListDB(BookOrderVO vo) {
        return bookOrderMapper.getTolOrderListDB(vo);
    }

    /**
     * 교재매출내역 리스트 총 건수
     */
    public int getTolOrderListCount(BookOrderVO vo) {
        return bookOrderMapper.getTolOrderListCount(vo);
    }

    /**
     * 교재매출내역 리스트 2번째 리스트
     */
    public List<HashMap<String, Object>> getPutLecMstDB(BookOrderVO vo) {
        return bookOrderMapper.getPutLecMstDB(vo);
    }

    /**
     * 교재매출내역 엑셀 리스트
     */
    public List<HashMap<String, Object>> getTolOrderExelListDB(BookOrderVO vo) {
        return bookOrderMapper.getTolOrderExelListDB(vo);
    }

    // =====================================================
    // 경찰 택배정보 조회 (_COP)
    // =====================================================

    /**
     * 경찰 교재배송 리스트 (택배)
     */
    public List<HashMap<String, Object>> getJuOrderListDB_COP(BookOrderVO vo) {
        return bookOrderMapper.getJuOrderListDB_COP(vo);
    }

    /**
     * 경찰 교재배송 리스트 (택배) 총 건수
     */
    public int getJuOrderListCount_COP(BookOrderVO vo) {
        return bookOrderMapper.getJuOrderListCount_COP(vo);
    }

    /**
     * 경찰 교재배송 2번째 리스트
     */
    public List<HashMap<String, Object>> getTblJuOrderMgntListDB_COP(BookOrderVO vo) {
        return bookOrderMapper.getTblJuOrderMgntListDB_COP(vo);
    }

    /**
     * 경찰 엑셀 리스트
     */
    public List<HashMap<String, Object>> getJuOrderExelListDB_COP(BookOrderVO vo) {
        return bookOrderMapper.getJuOrderExelListDB_COP(vo);
    }

    /**
     * 경찰 송장저장 1
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateSendNo1_COP(BookOrderVO vo) {
        return bookOrderMapper.updateSendNo1_COP(vo);
    }

    /**
     * 경찰 송장저장 2
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateSendNo2_COP(BookOrderVO vo) {
        return bookOrderMapper.updateSendNo2_COP(vo);
    }

    /**
     * 경찰 강의 상세
     */
    public List<HashMap<String, Object>> getLecMstViewList_COP(BookOrderVO vo) {
        return bookOrderMapper.getLecMstViewList_COP(vo);
    }

    /**
     * 경찰 배송 상세
     */
    public List<HashMap<String, Object>> getTblDeliversViewList_COP(BookOrderVO vo) {
        return bookOrderMapper.getTblDeliversViewList_COP(vo);
    }
}
