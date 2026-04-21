package com.academy.productorder.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.CouponMngMapper;

/**
 * CouponMng Service
 * 쿠폰 관리 서비스
 */
@Service
public class CouponMngService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CouponMngMapper couponMngMapper;

    @Autowired
    public CouponMngService(CouponMngMapper couponMngMapper) {
        this.couponMngMapper = couponMngMapper;
    }

    public List<HashMap<String, String>> getCoopList(HashMap<String, String> params) {
        return couponMngMapper.getCoopList(params);
    }

    public List<HashMap<String, String>> getCouponList(HashMap<String, String> params) {
        return couponMngMapper.getCouponList(params);
    }

    public int getCouponListCount(HashMap<String, String> params) {
        return couponMngMapper.getCouponListCount(params);
    }

    public HashMap<String, String> getCouponOne(HashMap<String, String> params) {
        return couponMngMapper.getCouponOne(params);
    }

    public void insertCoupon(HashMap<String, String> params) {
        couponMngMapper.insertCoupon(params);
    }

    public void updateCoupon(HashMap<String, String> params) {
        couponMngMapper.updateCoupon(params);
    }

    public List<HashMap<String, String>> getCouponUserList(HashMap<String, String> params) {
        return couponMngMapper.getCouponUserList(params);
    }

    public int getCouponUserListCount(HashMap<String, String> params) {
        return couponMngMapper.getCouponUserListCount(params);
    }

    public List<HashMap<String, String>> getCoopLectureList(HashMap<String, String> params) {
        return couponMngMapper.getCoopLectureList(params);
    }

    public int getCoopLectureListCount(HashMap<String, String> params) {
        return couponMngMapper.getCoopLectureListCount(params);
    }

    public List<HashMap<String, String>> getCoopCouponList(HashMap<String, String> params) {
        return couponMngMapper.getCoopCouponList(params);
    }

    public int getCoopCouponListCount(HashMap<String, String> params) {
        return couponMngMapper.getCoopCouponListCount(params);
    }

    public void insertCoopCoupon(HashMap<String, String> params) {
        couponMngMapper.insertCoopCoupon(params);
    }

    public void deleteCoopCoupon(HashMap<String, String> params) {
        couponMngMapper.deleteCoopCoupon(params);
    }

    public List<HashMap<String, String>> getCouponOrderList(HashMap<String, String> params) {
        return couponMngMapper.getCouponOrderList(params);
    }
}