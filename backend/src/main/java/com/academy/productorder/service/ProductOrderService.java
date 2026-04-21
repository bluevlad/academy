package com.academy.productorder.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.academy.mapper.ProductOrderMapper;

/**
 * ProductOrder Service
 * LoginService 패턴 적용 - ServiceImpl 없이 @Service 클래스로 직접 구현
 * last modified 2014-08-20
 */
@Service
public class ProductOrderService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ProductOrderMapper productOrderMapper;

	public ProductOrderService(ProductOrderMapper productOrderMapper) {
		this.productOrderMapper = productOrderMapper;
	}

	public List<HashMap<String, Object>> getOrderStatuscodeList(ProductOrderVO vo) {
		return productOrderMapper.getOrderStatuscodeList(vo);
	}

	public List<HashMap<String, Object>> getPaymentList(ProductOrderVO vo) {
		return productOrderMapper.getPaymentList(vo);
	}

	public List<HashMap<String, Object>> getOrderListDB_0(ProductOrderVO vo) {
		return productOrderMapper.getOrderListDB_0(vo);
	}

	public int getOrderListCount_0(ProductOrderVO vo) {
		return productOrderMapper.getOrderListCount_0(vo);
	}

	public List<HashMap<String, Object>> getOrderListDB_freelec(ProductOrderVO vo) {
		return productOrderMapper.getOrderListDB_freelec(vo);
	}

	public int getOrderListCount_freelec(ProductOrderVO vo) {
		return productOrderMapper.getOrderListCount_freelec(vo);
	}

	public List<HashMap<String, Object>> getOrderListDB(ProductOrderVO vo) {
		return productOrderMapper.getOrderListDB(vo);
	}

	public int getOrderListCount(ProductOrderVO vo) {
		return productOrderMapper.getOrderListCount(vo);
	}

	public List<HashMap<String, Object>> getTblOrderMgntListDB(ProductOrderVO vo) {
		return productOrderMapper.getTblOrderMgntListDB(vo);
	}

	public List<HashMap<String, String>> getOrderExcelListDB(ProductOrderVO vo) {
		return productOrderMapper.getOrderExcelListDB(vo);
	}

	public int setPayKindUpdate(ProductOrderVO vo) {
		return productOrderMapper.setPayKindUpdate(vo);
	}

	public int updateDepositStatus1(ProductOrderVO vo) {
		return productOrderMapper.updateDepositStatus1(vo);
	}

	public int getDepositStatusCount(ProductOrderVO vo) {
		return productOrderMapper.getDepositStatusCount(vo);
	}

	public int updateDepositStatus2(ProductOrderVO vo) {
		return productOrderMapper.updateDepositStatus2(vo);
	}

	public int updateDepositStatus3(ProductOrderVO vo) {
		return productOrderMapper.updateDepositStatus3(vo);
	}

	public int updateDepositStatus4(ProductOrderVO vo) {
		return productOrderMapper.updateDepositStatus4(vo);
	}

	public int updateDepositStatus5(ProductOrderVO vo) {
		return productOrderMapper.updateDepositStatus5(vo);
	}

	public int updateBookStockPlus(ProductOrderVO vo) {
		return productOrderMapper.updateBookStockPlus(vo);
	}

	public List<HashMap<String, Object>> getPmpdownloadListPop(ProductOrderVO vo) {
		return productOrderMapper.getPmpdownloadListPop(vo);
	}

	public List<HashMap<String, Object>> getMobiledownloadListPop(ProductOrderVO vo) {
		return productOrderMapper.getMobiledownloadListPop(vo);
	}

	public List<HashMap<String, Object>> getTblOrderList(ProductOrderVO vo) {
		return productOrderMapper.getTblOrderList(vo);
	}

	public List<HashMap<String, Object>> getOrderListPopDB(ProductOrderVO vo) {
		return productOrderMapper.getOrderListPopDB(vo);
	}

	public List<HashMap<String, Object>> getCourse_type_code(ProductOrderVO vo) {
		return productOrderMapper.getCourse_type_code(vo);
	}

	public List<HashMap<String, Object>> getPlayyn(ProductOrderVO vo) {
		return productOrderMapper.getPlayyn(vo);
	}

	public List<HashMap<String, Object>> getPoint(ProductOrderVO vo) {
		return productOrderMapper.getPoint(vo);
	}

	public List<HashMap<String, Object>> getTblOrderMgntNoPopViewList(ProductOrderVO vo) {
		return productOrderMapper.getTblOrderMgntNoPopViewList(vo);
	}

	public int getTblOrderMgntNoPopViewCount(ProductOrderVO vo) {
		return productOrderMapper.getTblOrderMgntNoPopViewCount(vo);
	}

	public int getPrice_Sum(ProductOrderVO vo) {
		return productOrderMapper.getPrice_Sum(vo);
	}

	public int getOldRefundViewCount(ProductOrderVO vo) {
		return productOrderMapper.getOldRefundViewCount(vo);
	}

	public List<HashMap<String, Object>> getRefund_Point(ProductOrderVO vo) {
		return productOrderMapper.getRefund_Point(vo);
	}

	public List<HashMap<String, Object>> getOldRefundView(ProductOrderVO vo) {
		return productOrderMapper.getOldRefundView(vo);
	}

	public List<HashMap<String, Object>> getTblDeliver_refund_list(ProductOrderVO vo) {
		return productOrderMapper.getTblDeliver_refund_list(vo);
	}

	public List<HashMap<String, Object>> getTblApprovalsViewList(ProductOrderVO vo) {
		return productOrderMapper.getTblApprovalsViewList(vo);
	}

	public int getTblDeliversViewListCount(ProductOrderVO vo) {
		return productOrderMapper.getTblDeliversViewListCount(vo);
	}

	public List<HashMap<String, Object>> getTblDeliversViewList(ProductOrderVO vo) {
		return productOrderMapper.getTblDeliversViewList(vo);
	}

	public List<HashMap<String, Object>> getTblOrdersViewList(ProductOrderVO vo) {
		return productOrderMapper.getTblOrdersViewList(vo);
	}

	public List<HashMap<String, Object>> getLecMstViewList(ProductOrderVO vo) {
		return productOrderMapper.getLecMstViewList(vo);
	}

	public int updateDelivers(ProductOrderVO vo) {
		return productOrderMapper.updateDelivers(vo);
	}

	public int updateApprovals(ProductOrderVO vo) {
		return productOrderMapper.updateApprovals(vo);
	}

	public int updateDeliversWmv(ProductOrderVO vo) {
		return productOrderMapper.updateDeliversWmv(vo);
	}

	public int updateMoney1(ProductOrderVO vo) {
		return productOrderMapper.updateMoney1(vo);
	}

	public int updateStudy_Per(ProductOrderVO vo) {
		return productOrderMapper.updateStudy_Per(vo);
	}

	public int getMoneySum(ProductOrderVO vo) {
		return productOrderMapper.getMoneySum(vo);
	}

	public int updateMoney2(ProductOrderVO vo) {
		return productOrderMapper.updateMoney2(vo);
	}

	public int refund_money_delete(ProductOrderVO vo) {
		return productOrderMapper.refund_money_delete(vo);
	}

	public List<HashMap<String, Object>> getMylecture(ProductOrderVO vo) {
		return productOrderMapper.getMylecture(vo);
	}

	public int updateMylecture1(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture1(vo);
	}

	public int updateMylecture2(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture2(vo);
	}

	public int updateMylecture3(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture3(vo);
	}

	public int updateMylecture4(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture4(vo);
	}

	public int updateEndDateMyLecture(ProductOrderVO vo) {
		return productOrderMapper.updateEndDateMyLecture(vo);
	}

	public int getMylectureCount(ProductOrderVO vo) {
		return productOrderMapper.getMylectureCount(vo);
	}

	public int updateMylecture5(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture5(vo);
	}

	public int insertMylecture6(ProductOrderVO vo) {
		return productOrderMapper.insertMylecture6(vo);
	}

	public int insertOrderMgntNo1(ProductOrderVO vo) {
		return productOrderMapper.insertOrderMgntNo1(vo);
	}

	public int updateOrderMgntNo2(ProductOrderVO vo) {
		return productOrderMapper.updateOrderMgntNo2(vo);
	}

	public int updateOrderMgntNo3(ProductOrderVO vo) {
		return productOrderMapper.updateOrderMgntNo3(vo);
	}

	public int insertOrderMgntNo2(ProductOrderVO vo) {
		return productOrderMapper.insertOrderMgntNo2(vo);
	}

	public int updateApprovals2(ProductOrderVO vo) {
		return productOrderMapper.updateApprovals2(vo);
	}

	public int insertMileageHistory(ProductOrderVO vo) {
		return productOrderMapper.insertMileageHistory(vo);
	}

	public int insertMileageHistory3(ProductOrderVO vo) {
		return productOrderMapper.insertMileageHistory3(vo);
	}

	public int updateMaMember(ProductOrderVO vo) {
		return productOrderMapper.updateMaMember(vo);
	}

	public int updateMylecture_1(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture_1(vo);
	}

	public int updateMylecture_2(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture_2(vo);
	}

	public int updateOrderMgntNo1(ProductOrderVO vo) {
		return productOrderMapper.updateOrderMgntNo1(vo);
	}

	public int updateMileageHistory(ProductOrderVO vo) {
		return productOrderMapper.updateMileageHistory(vo);
	}

	public int updateMaMember2(ProductOrderVO vo) {
		return productOrderMapper.updateMaMember2(vo);
	}

	public int updateApprovals3(ProductOrderVO vo) {
		return productOrderMapper.updateApprovals3(vo);
	}

	public int deleteOrderMgntNo(ProductOrderVO vo) {
		return productOrderMapper.deleteOrderMgntNo(vo);
	}

	public int updateMaMember3(ProductOrderVO vo) {
		return productOrderMapper.updateMaMember3(vo);
	}

	public int updateApprovals4(ProductOrderVO vo) {
		return productOrderMapper.updateApprovals4(vo);
	}

	public int insertMileageHistory2(ProductOrderVO vo) {
		return productOrderMapper.insertMileageHistory2(vo);
	}

	public int updateMylecture_3(ProductOrderVO vo) {
		return productOrderMapper.updateMylecture_3(vo);
	}

	public int updateApprovals5(ProductOrderVO vo) {
		return productOrderMapper.updateApprovals5(vo);
	}

	public int insertSendMsgMultiSendUser(ProductOrderVO vo) {
		return productOrderMapper.insertSendMsgMultiSendUser(vo);
	}

	public List<HashMap<String, Object>> getTmMember_View(ProductOrderVO vo) {
		return productOrderMapper.getTmMember_View(vo);
	}

	public List<HashMap<String, Object>> getCsccode(ProductOrderVO vo) {
		return productOrderMapper.getCsccode(vo);
	}

	public List<HashMap<String, Object>> Cs_board_list(ProductOrderVO vo) {
		return productOrderMapper.Cs_board_list(vo);
	}

	public int getCsBoardListCount(ProductOrderVO vo) {
		return productOrderMapper.getCsBoardListCount(vo);
	}

	public List<HashMap<String, Object>> getTm_mycoupon_list_admin(ProductOrderVO vo) {
		return productOrderMapper.getTm_mycoupon_list_admin(vo);
	}

	public int getTm_mycoupon_listCount_admin(ProductOrderVO vo) {
		return productOrderMapper.getTm_mycoupon_listCount_admin(vo);
	}

	public List<HashMap<String, Object>> Tm_Class_List(ProductOrderVO vo) {
		return productOrderMapper.Tm_Class_List(vo);
	}

	public List<HashMap<String, Object>> Off_Class_List(ProductOrderVO vo) {
		return productOrderMapper.Off_Class_List(vo);
	}

	public int getTmClassListCount(ProductOrderVO vo) {
		return productOrderMapper.getTmClassListCount(vo);
	}

	public List<HashMap<String, Object>> getMemoList(ProductOrderVO vo) {
		return productOrderMapper.getMemoList(vo);
	}

	public int getMemoListCount(ProductOrderVO vo) {
		return productOrderMapper.getMemoListCount(vo);
	}

	public int insertBoardCs(ProductOrderVO vo) {
		return productOrderMapper.insertBoardCs(vo);
	}

	public int updateMemo(ProductOrderVO vo) {
		return productOrderMapper.updateMemo(vo);
	}

	public int getSelectBefore_Point(ProductOrderVO vo) {
		return productOrderMapper.getSelectBefore_Point(vo);
	}

	public List<HashMap<String, Object>> getTmCoupon(ProductOrderVO vo) {
		return productOrderMapper.getTmCoupon(vo);
	}

	public List<HashMap<String, Object>> getTmCouponList(ProductOrderVO vo) {
		return productOrderMapper.getTmCouponList(vo);
	}

	public List<HashMap<String, Object>> getTmMoCouponList(ProductOrderVO vo) {
		return productOrderMapper.getTmMoCouponList(vo);
	}

	public int getTmCouponCount(ProductOrderVO vo) {
		return productOrderMapper.getTmCouponCount(vo);
	}

	public int getTmMoCouponCount(ProductOrderVO vo) {
		return productOrderMapper.getTmMoCouponCount(vo);
	}

	public int getCouponCount(ProductOrderVO vo) {
		return productOrderMapper.getCouponCount(vo);
	}

	public int insertTmCoupon(ProductOrderVO vo) {
		return productOrderMapper.insertTmCoupon(vo);
	}

	public int insertMyCoupon(ProductOrderVO vo) {
		return productOrderMapper.insertMyCoupon(vo);
	}

	public List<HashMap<String, Object>> getOffOrderListDB(ProductOrderVO vo) {
		return productOrderMapper.getOffOrderListDB(vo);
	}

	public int getOffOrderListCount(ProductOrderVO vo) {
		return productOrderMapper.getOffOrderListCount(vo);
	}

	public List<HashMap<String, Object>> getOffTblOrderMgntListDB(ProductOrderVO vo) {
		return productOrderMapper.getOffTblOrderMgntListDB(vo);
	}

	public List<HashMap<String, String>> getOffOrderExcelListDB(ProductOrderVO vo) {
		return productOrderMapper.getOffOrderExcelListDB(vo);
	}

	public List<HashMap<String, Object>> getIdChk(ProductOrderVO vo) {
		return productOrderMapper.getIdChk(vo);
	}

	public List<HashMap<String, Object>> getCaCatCdList(ProductOrderVO vo) {
		return productOrderMapper.getCaCatCdList(vo);
	}

	public List<HashMap<String, Object>> getVwMenuMstTree_lec(ProductOrderVO vo) {
		return productOrderMapper.getVwMenuMstTree_lec(vo);
	}

	public List<HashMap<String, Object>> getCaSubjectCdList(ProductOrderVO vo) {
		return productOrderMapper.getCaSubjectCdList(vo);
	}

	public List<HashMap<String, Object>> getCbLecMstFreeOrderList(ProductOrderVO vo) {
		return productOrderMapper.getCbLecMstFreeOrderList(vo);
	}

	public int getCbLecMstListFreeOrderCount(ProductOrderVO vo) {
		return productOrderMapper.getCbLecMstListFreeOrderCount(vo);
	}

	public List<HashMap<String, Object>> getChangePrice(ProductOrderVO vo) {
		return productOrderMapper.getChangePrice(vo);
	}

	public List<HashMap<String, Object>> getUCount(ProductOrderVO vo) {
		return productOrderMapper.getUCount(vo);
	}

	public int insertCart(ProductOrderVO vo) {
		return productOrderMapper.insertCart(vo);
	}

	public List<HashMap<String, Object>> getSubCode2(ProductOrderVO vo) {
		return productOrderMapper.getSubCode2(vo);
	}

	public int deleteCart(ProductOrderVO vo) {
		return productOrderMapper.deleteCart(vo);
	}

	public int deleteAllCart(ProductOrderVO vo) {
		return productOrderMapper.deleteAllCart(vo);
	}

	public List<HashMap<String, Object>> getMCount(ProductOrderVO vo) {
		return productOrderMapper.getMCount(vo);
	}

	public int insertOffOrders(ProductOrderVO vo) {
		return productOrderMapper.insertOffOrders(vo);
	}

	public int insertOffApprovals(ProductOrderVO vo) {
		return productOrderMapper.insertOffApprovals(vo);
	}

	public int insertOffOrderMgntNo(ProductOrderVO vo) {
		return productOrderMapper.insertOffOrderMgntNo(vo);
	}

	public int insertOffMylecture(ProductOrderVO vo) {
		return productOrderMapper.insertOffMylecture(vo);
	}

	public int modifyOffMylecture(ProductOrderVO vo) {
		return productOrderMapper.modifyOffMylecture(vo);
	}

	public int insertOffMylecture_N(ProductOrderVO vo) {
		return productOrderMapper.insertOffMylecture_N(vo);
	}

	public List<HashMap<String, Object>> getUpdateDetail(ProductOrderVO vo) {
		return productOrderMapper.getUpdateDetail(vo);
	}

	public List<HashMap<String, Object>> getSubCodeUp(ProductOrderVO vo) {
		return productOrderMapper.getSubCodeUp(vo);
	}

	public List<HashMap<String, Object>> getSubList(ProductOrderVO vo) {
		return productOrderMapper.getSubList(vo);
	}

	public int updateOffOrders(ProductOrderVO vo) {
		return productOrderMapper.updateOffOrders(vo);
	}

	public int Coupon_Del(ProductOrderVO vo) {
		return productOrderMapper.Coupon_Del(vo);
	}

	public int updateOffApprovals(ProductOrderVO vo) {
		return productOrderMapper.updateOffApprovals(vo);
	}

	public int updateOffMgntNo(ProductOrderVO vo) {
		return productOrderMapper.updateOffMgntNo(vo);
	}

	public int updateOffMgntNo_N(ProductOrderVO vo) {
		return productOrderMapper.updateOffMgntNo_N(vo);
	}

	public int deleteOffOrderMgntNo(ProductOrderVO vo) {
		return productOrderMapper.deleteOffOrderMgntNo(vo);
	}

	public int deleteOffMylecture(ProductOrderVO vo) {
		return productOrderMapper.deleteOffMylecture(vo);
	}

	public int deleteOffOrders(ProductOrderVO vo) {
		return productOrderMapper.deleteOffOrders(vo);
	}

	public int deleteOffApprovals(ProductOrderVO vo) {
		return productOrderMapper.deleteOffApprovals(vo);
	}

	public List<HashMap<String, Object>> getPrintPop(ProductOrderVO vo) {
		return productOrderMapper.getPrintPop(vo);
	}

	public int updatePrintOffOrders1(ProductOrderVO vo) {
		return productOrderMapper.updatePrintOffOrders1(vo);
	}

	public int updatePrintOffOrders2(ProductOrderVO vo) {
		return productOrderMapper.updatePrintOffOrders2(vo);
	}

	public int updateOffRefund(ProductOrderVO vo) {
		return productOrderMapper.updateOffRefund(vo);
	}

	public int insertRefundOffOrderMgntNo(ProductOrderVO vo) {
		return productOrderMapper.insertRefundOffOrderMgntNo(vo);
	}

	public int updateOffMylecture(ProductOrderVO vo) {
		return productOrderMapper.updateOffMylecture(vo);
	}

	public int updateOffMylectureRefund(ProductOrderVO vo) {
		return productOrderMapper.updateOffMylectureRefund(vo);
	}

	public int insertOffRefund(ProductOrderVO vo) {
		return productOrderMapper.insertOffRefund(vo);
	}

	public int deleteOffCancelOrderMgntNo(ProductOrderVO vo) {
		return productOrderMapper.deleteOffCancelOrderMgntNo(vo);
	}

	public int deleteOffCancelRefund(ProductOrderVO vo) {
		return productOrderMapper.deleteOffCancelRefund(vo);
	}

	public int updateOffCancelMylecture(ProductOrderVO vo) {
		return productOrderMapper.updateOffCancelMylecture(vo);
	}

	public int Delete_Year_Package_Point(ProductOrderVO vo) {
		return productOrderMapper.Delete_Year_Package_Point(vo);
	}

	public List<HashMap<String, String>> getTmPointHistory(ProductOrderVO vo) {
		return productOrderMapper.getTmPointHistory(vo);
	}

	public void insertTmBoard(ProductOrderVO vo) {
		productOrderMapper.insertTmBoard(vo);
	}

	public List<HashMap<String, String>> getTmBoardList(ProductOrderVO vo) {
		return productOrderMapper.getTmBoardList(vo);
	}

	public List<HashMap<String, String>> getVOCCODEList(ProductOrderVO vo) {
		return productOrderMapper.getVOCCODEList(vo);
	}

	public List<HashMap<String, String>> getDUTYCODEList(ProductOrderVO vo) {
		return productOrderMapper.getDUTYCODEList(vo);
	}

	public List<HashMap<String, String>> getOffApprovalsCount(ProductOrderVO vo) {
		return productOrderMapper.getOffApprovalsCount(vo);
	}

	public List<HashMap<String, String>> getCcode(ProductOrderVO vo) {
		return productOrderMapper.getCcode(vo);
	}

	public void insertMyLecture(ProductOrderVO vo) {
		productOrderMapper.insertMyLecture(vo);
	}

	public void insertMyLectureN(ProductOrderVO vo) {
		productOrderMapper.insertMyLectureN(vo);
	}

	public int getPlusPoint(ProductOrderVO vo) {
		return productOrderMapper.getPlusPoint(vo);
	}

	public void BookPointDel(ProductOrderVO vo) {
		productOrderMapper.BookPointDel(vo);
	}

	public void BookPointIns(ProductOrderVO vo) {
		productOrderMapper.BookPointIns(vo);
	}

	public void insertOffOrderLog(ProductOrderVO vo) {
		productOrderMapper.insertOffOrderLog(vo);
	}
}
