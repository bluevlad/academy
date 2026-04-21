package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.MacAddressManagerMapper;

/**
 * MacAddressManager Service
 * MAC 주소 관리 서비스 (직접 구현)
 */
@Service(value="macaddressmanagerservice")
public class MacAddressManagerService {

    @Autowired
    private MacAddressManagerMapper macAddressManagerMapper;

    /**
     * MAC주소 관리 목록 조회
     */
    public List<HashMap<String, String>> macaddressmanagerList(MacAddressManagerVO vo){
        return macAddressManagerMapper.macaddressmanagerList(vo);
    }

    /**
     * 디바이스 목록 조회
     */
    public List<HashMap<String, String>> devicelist(MacAddressManagerVO vo){
        return macAddressManagerMapper.devicelist(vo);
    }

    /**
     * MAC주소 상세 조회
     */
    public List<HashMap<String, String>> macaddressView(MacAddressManagerVO vo){
        return macAddressManagerMapper.macaddressView(vo);
    }

    /**
     * MAC주소 관리 목록 개수 조회
     */
    public int macaddressmanagerListCount(MacAddressManagerVO vo){
        return macAddressManagerMapper.macaddressmanagerListCount(vo);
    }

    /**
     * MAC주소 업데이트
     */
    public void macaddressmanagerUpdate(MacAddressManagerVO vo){
        macAddressManagerMapper.macaddressmanagerUpdate(vo);
    }

    /**
     * MAC주소 업데이트1 (모바일 초기화)
     */
    public void macaddressmanagerUpdate1(MacAddressManagerVO vo){
        macAddressManagerMapper.macaddressmanagerUpdate1(vo);
    }
}