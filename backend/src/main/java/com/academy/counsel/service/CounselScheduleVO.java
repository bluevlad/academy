package com.academy.counsel.service;

import java.util.List;

import com.academy.common.CommonVO;

/**
 * 상담 일정 VO 클래스 (COUNSEL_SCH 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            상담 일정 관리 신규 생성
 * </pre>
 */
public class CounselScheduleVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 상담일 */
    private String schDay;

    /** 시간 인덱스 */
    private Integer tsIdx;

    /** 최대 인원 */
    private Integer maxUsr;

    /** 예약 인원 */
    private Integer reqCnt;

    /** 사용 여부 */
    private String isUse;

    /** 예약 타입 */
    private String reqType;

    /** 카테고리 코드 */
    private String catCd;

    /** 카테고리명 (조회용) */
    private String catNm;

    /** 시간 설정 (조회용) */
    private String timeSet;

    /** 요일 (조회용) */
    private String week;

    /** 일자 표시용 */
    private String vDay;

    /** 검색 시작일 */
    private String sDate;

    /** 검색 종료일 */
    private String eDate;

    /** 검색 카테고리 */
    private String searchCategory;

    /** 카테고리 목록 (검색용) */
    private List<String> cateList;

    /** 날짜 수 */
    private Integer dateCount;

    /** 시간 수 */
    private Integer timeCount;

    /** 수정 날짜 */
    private String updateDate;

    /** 카테고리 코드 목록 (등록용) */
    private String catCds;

    /** 삭제 날짜 */
    private String delDate;

    /** 삭제 배열 */
    private String[] delArr;

    // Getters and Setters
    public String getSchDay() {
        return schDay;
    }

    public void setSchDay(String schDay) {
        this.schDay = schDay;
    }

    public Integer getTsIdx() {
        return tsIdx;
    }

    public void setTsIdx(Integer tsIdx) {
        this.tsIdx = tsIdx;
    }

    public Integer getMaxUsr() {
        return maxUsr;
    }

    public void setMaxUsr(Integer maxUsr) {
        this.maxUsr = maxUsr;
    }

    public Integer getReqCnt() {
        return reqCnt;
    }

    public void setReqCnt(Integer reqCnt) {
        this.reqCnt = reqCnt;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public String getCatNm() {
        return catNm;
    }

    public void setCatNm(String catNm) {
        this.catNm = catNm;
    }

    public String getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(String timeSet) {
        this.timeSet = timeSet;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getvDay() {
        return vDay;
    }

    public void setvDay(String vDay) {
        this.vDay = vDay;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public List<String> getCateList() {
        return cateList;
    }

    public void setCateList(List<String> cateList) {
        this.cateList = cateList;
    }

    public Integer getDateCount() {
        return dateCount;
    }

    public void setDateCount(Integer dateCount) {
        this.dateCount = dateCount;
    }

    public Integer getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Integer timeCount) {
        this.timeCount = timeCount;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCatCds() {
        return catCds;
    }

    public void setCatCds(String catCds) {
        this.catCds = catCds;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public String[] getDelArr() {
        return delArr;
    }

    public void setDelArr(String[] delArr) {
        this.delArr = delArr;
    }
}
