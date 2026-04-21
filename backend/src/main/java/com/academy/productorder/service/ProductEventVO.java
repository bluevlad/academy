package com.academy.productorder.service;

import java.util.Date;

/**
 * ProductEvent Value Object
 * PRD_EVENT 테이블 매핑 VO
 */
public class ProductEventVO {

    private Integer eventId;
    private String eventNm;
    private String eventType;
    private Integer eventAmount;
    private Date stDate;
    private Date edDate;
    private String eventYn;

    // Additional fields for display
    private Integer cnt;
    private String leccode;
    private String subjectTitle;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventNm() {
        return eventNm;
    }

    public void setEventNm(String eventNm) {
        this.eventNm = eventNm;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getEventAmount() {
        return eventAmount;
    }

    public void setEventAmount(Integer eventAmount) {
        this.eventAmount = eventAmount;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public Date getEdDate() {
        return edDate;
    }

    public void setEdDate(Date edDate) {
        this.edDate = edDate;
    }

    public String getEventYn() {
        return eventYn;
    }

    public void setEventYn(String eventYn) {
        this.eventYn = eventYn;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}