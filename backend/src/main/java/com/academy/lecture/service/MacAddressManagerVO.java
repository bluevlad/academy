package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * MacAddressManager Value Object
 * TB_USER_INFO 및 관련 MAC 주소 테이블 매핑 VO
 */
public class MacAddressManagerVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // User 관련 fields
    private String userId;          // USER_ID - 사용자 ID
    private String userNm;          // USER_NM - 사용자명
    private String email;           // EMAIL - 이메일
    private String phoneNo;         // PHONE_NO - 전화번호

    // MAC Address 관련 fields
    private String macAddress;      // MAC_ADDRESS - MAC 주소
    private String deviceType;      // DEVICE_TYPE - 디바이스 타입
    private String deviceName;      // DEVICE_NAME - 디바이스명

    // Search 관련 fields (searchType, searchText inherited from CommonVO)

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}