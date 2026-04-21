package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * CouponMng Mapper Interface
 */
@Mapper
public interface CouponMngMapper {

    List<HashMap<String, String>> getCoopList(HashMap<String, String> params);

    List<HashMap<String, String>> getCouponList(HashMap<String, String> params);

    int getCouponListCount(HashMap<String, String> params);

    HashMap<String, String> getCouponOne(HashMap<String, String> params);

    void insertCoupon(HashMap<String, String> params);

    void updateCoupon(HashMap<String, String> params);

    List<HashMap<String, String>> getCouponUserList(HashMap<String, String> params);

    int getCouponUserListCount(HashMap<String, String> params);

    List<HashMap<String, String>> getCoopLectureList(HashMap<String, String> params);

    int getCoopLectureListCount(HashMap<String, String> params);

    List<HashMap<String, String>> getCoopCouponList(HashMap<String, String> params);

    int getCoopCouponListCount(HashMap<String, String> params);

    void insertCoopCoupon(HashMap<String, String> params);

    void deleteCoopCoupon(HashMap<String, String> params);

    List<HashMap<String, String>> getCouponOrderList(HashMap<String, String> params);
}