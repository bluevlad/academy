package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.productorder.service.ProductOrderVO;

/**
 * ProductOrder Mapper Interface
 */
@Mapper
public interface ProductOrderMapper {

    List<HashMap<String, Object>> getOrderStatuscodeList(ProductOrderVO vo);

    List<HashMap<String, Object>> getPaymentList(ProductOrderVO vo);

    List<HashMap<String, Object>> getOrderListDB_0(ProductOrderVO vo);

    int getOrderListCount_0(ProductOrderVO vo);

    List<HashMap<String, Object>> getOrderListDB_freelec(ProductOrderVO vo);

    int getOrderListCount_freelec(ProductOrderVO vo);

    List<HashMap<String, Object>> getOrderListDB(ProductOrderVO vo);

    int getOrderListCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblOrderMgntListDB(ProductOrderVO vo);

    List<HashMap<String, String>> getOrderExcelListDB(ProductOrderVO vo);

    int setPayKindUpdate(ProductOrderVO vo);

    int updateDepositStatus1(ProductOrderVO vo);

    int getDepositStatusCount(ProductOrderVO vo);

    int updateDepositStatus2(ProductOrderVO vo);

    int updateDepositStatus3(ProductOrderVO vo);

    int updateDepositStatus4(ProductOrderVO vo);

    int updateDepositStatus5(ProductOrderVO vo);

    int updateBookStockPlus(ProductOrderVO vo);

    List<HashMap<String, Object>> getPmpdownloadListPop(ProductOrderVO vo);

    List<HashMap<String, Object>> getMobiledownloadListPop(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblOrderList(ProductOrderVO vo);

    List<HashMap<String, Object>> getOrderListPopDB(ProductOrderVO vo);

    List<HashMap<String, Object>> getCourse_type_code(ProductOrderVO vo);

    List<HashMap<String, Object>> getPlayyn(ProductOrderVO vo);

    List<HashMap<String, Object>> getPoint(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblOrderMgntNoPopViewList(ProductOrderVO vo);

    int getTblOrderMgntNoPopViewCount(ProductOrderVO vo);

    int getPrice_Sum(ProductOrderVO vo);

    int getOldRefundViewCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getRefund_Point(ProductOrderVO vo);

    List<HashMap<String, Object>> getOldRefundView(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblDeliver_refund_list(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblApprovalsViewList(ProductOrderVO vo);

    int getTblDeliversViewListCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblDeliversViewList(ProductOrderVO vo);

    List<HashMap<String, Object>> getTblOrdersViewList(ProductOrderVO vo);

    List<HashMap<String, Object>> getLecMstViewList(ProductOrderVO vo);

    int updateDelivers(ProductOrderVO vo);

    int updateApprovals(ProductOrderVO vo);

    int updateDeliversWmv(ProductOrderVO vo);

    int updateMoney1(ProductOrderVO vo);

    int updateStudy_Per(ProductOrderVO vo);

    int getMoneySum(ProductOrderVO vo);

    int updateMoney2(ProductOrderVO vo);

    int refund_money_delete(ProductOrderVO vo);

    List<HashMap<String, Object>> getMylecture(ProductOrderVO vo);

    int updateMylecture1(ProductOrderVO vo);

    int updateMylecture2(ProductOrderVO vo);

    int updateMylecture3(ProductOrderVO vo);

    int updateMylecture4(ProductOrderVO vo);

    int updateEndDateMyLecture(ProductOrderVO vo);

    int getMylectureCount(ProductOrderVO vo);

    int updateMylecture5(ProductOrderVO vo);

    int insertMylecture6(ProductOrderVO vo);

    int insertOrderMgntNo1(ProductOrderVO vo);

    int updateOrderMgntNo2(ProductOrderVO vo);

    int updateOrderMgntNo3(ProductOrderVO vo);

    int insertOrderMgntNo2(ProductOrderVO vo);

    int updateApprovals2(ProductOrderVO vo);

    int insertMileageHistory(ProductOrderVO vo);

    int insertMileageHistory3(ProductOrderVO vo);

    int updateMaMember(ProductOrderVO vo);

    int updateMylecture_1(ProductOrderVO vo);

    int updateMylecture_2(ProductOrderVO vo);

    int updateOrderMgntNo1(ProductOrderVO vo);

    int updateMileageHistory(ProductOrderVO vo);

    int updateMaMember2(ProductOrderVO vo);

    int updateApprovals3(ProductOrderVO vo);

    int deleteOrderMgntNo(ProductOrderVO vo);

    int updateMaMember3(ProductOrderVO vo);

    int updateApprovals4(ProductOrderVO vo);

    int insertMileageHistory2(ProductOrderVO vo);

    int updateMylecture_3(ProductOrderVO vo);

    int updateApprovals5(ProductOrderVO vo);

    int insertSendMsgMultiSendUser(ProductOrderVO vo);

    List<HashMap<String, Object>> getTmMember_View(ProductOrderVO vo);

    List<HashMap<String, Object>> getCsccode(ProductOrderVO vo);

    List<HashMap<String, Object>> Cs_board_list(ProductOrderVO vo);

    int getCsBoardListCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getTm_mycoupon_list_admin(ProductOrderVO vo);

    int getTm_mycoupon_listCount_admin(ProductOrderVO vo);

    List<HashMap<String, Object>> Tm_Class_List(ProductOrderVO vo);

    List<HashMap<String, Object>> Off_Class_List(ProductOrderVO vo);

    int getTmClassListCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getMemoList(ProductOrderVO vo);

    int getMemoListCount(ProductOrderVO vo);

    int insertBoardCs(ProductOrderVO vo);

    int updateMemo(ProductOrderVO vo);

    int getSelectBefore_Point(ProductOrderVO vo);

    List<HashMap<String, Object>> getTmCoupon(ProductOrderVO vo);

    List<HashMap<String, Object>> getTmCouponList(ProductOrderVO vo);

    List<HashMap<String, Object>> getTmMoCouponList(ProductOrderVO vo);

    int getTmCouponCount(ProductOrderVO vo);

    int getTmMoCouponCount(ProductOrderVO vo);

    int getCouponCount(ProductOrderVO vo);

    int insertTmCoupon(ProductOrderVO vo);

    int insertMyCoupon(ProductOrderVO vo);

    List<HashMap<String, Object>> getOffOrderListDB(ProductOrderVO vo);

    int getOffOrderListCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getOffTblOrderMgntListDB(ProductOrderVO vo);

    List<HashMap<String, String>> getOffOrderExcelListDB(ProductOrderVO vo);

    List<HashMap<String, Object>> getIdChk(ProductOrderVO vo);

    List<HashMap<String, Object>> getCaCatCdList(ProductOrderVO vo);

    List<HashMap<String, Object>> getVwMenuMstTree_lec(ProductOrderVO vo);

    List<HashMap<String, Object>> getCaSubjectCdList(ProductOrderVO vo);

    List<HashMap<String, Object>> getCbLecMstFreeOrderList(ProductOrderVO vo);

    int getCbLecMstListFreeOrderCount(ProductOrderVO vo);

    List<HashMap<String, Object>> getChangePrice(ProductOrderVO vo);

    List<HashMap<String, Object>> getUCount(ProductOrderVO vo);

    int insertCart(ProductOrderVO vo);

    List<HashMap<String, Object>> getSubCode2(ProductOrderVO vo);

    int deleteCart(ProductOrderVO vo);

    int deleteAllCart(ProductOrderVO vo);

    List<HashMap<String, Object>> getMCount(ProductOrderVO vo);

    int insertOffOrders(ProductOrderVO vo);

    int insertOffApprovals(ProductOrderVO vo);

    int insertOffOrderMgntNo(ProductOrderVO vo);

    int insertOffMylecture(ProductOrderVO vo);

    int modifyOffMylecture(ProductOrderVO vo);

    int insertOffMylecture_N(ProductOrderVO vo);

    List<HashMap<String, Object>> getUpdateDetail(ProductOrderVO vo);

    List<HashMap<String, Object>> getSubCodeUp(ProductOrderVO vo);

    List<HashMap<String, Object>> getSubList(ProductOrderVO vo);

    int updateOffOrders(ProductOrderVO vo);

    int Coupon_Del(ProductOrderVO vo);

    int updateOffApprovals(ProductOrderVO vo);

    int updateOffMgntNo(ProductOrderVO vo);

    int updateOffMgntNo_N(ProductOrderVO vo);

    int deleteOffOrderMgntNo(ProductOrderVO vo);

    int deleteOffMylecture(ProductOrderVO vo);

    int deleteOffOrders(ProductOrderVO vo);

    int deleteOffApprovals(ProductOrderVO vo);

    List<HashMap<String, Object>> getPrintPop(ProductOrderVO vo);

    int updatePrintOffOrders1(ProductOrderVO vo);

    int updatePrintOffOrders2(ProductOrderVO vo);

    int updateOffRefund(ProductOrderVO vo);

    int insertRefundOffOrderMgntNo(ProductOrderVO vo);

    int updateOffMylecture(ProductOrderVO vo);

    int updateOffMylectureRefund(ProductOrderVO vo);

    int insertOffRefund(ProductOrderVO vo);

    int deleteOffCancelOrderMgntNo(ProductOrderVO vo);

    int deleteOffCancelRefund(ProductOrderVO vo);

    int updateOffCancelMylecture(ProductOrderVO vo);

    int Delete_Year_Package_Point(ProductOrderVO vo);

    List<HashMap<String, String>> getTmPointHistory(ProductOrderVO vo);

    void insertTmBoard(ProductOrderVO vo);

    List<HashMap<String, String>> getTmBoardList(ProductOrderVO vo);

    List<HashMap<String, String>> getVOCCODEList(ProductOrderVO vo);

    List<HashMap<String, String>> getDUTYCODEList(ProductOrderVO vo);

    List<HashMap<String, String>> getOffApprovalsCount(ProductOrderVO vo);

    List<HashMap<String, String>> getCcode(ProductOrderVO vo);

    void insertMyLecture(ProductOrderVO vo);

    void insertMyLectureN(ProductOrderVO vo);

    int getPlusPoint(ProductOrderVO vo);

    void BookPointDel(ProductOrderVO vo);

    void BookPointIns(ProductOrderVO vo);

    void insertOffOrderLog(ProductOrderVO vo);
}
