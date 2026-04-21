package com.academy.survey.service;

import com.academy.common.CommonVO;

/**
 * 설문조사 관리 VO
 */
public class SurveyVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 설문 문항 (Bank)
    private int queId;
    private String queOwner;
    private String queTitle;
    private String queDesc;
    private String queViw1;
    private String queViw2;
    private String queViw3;
    private String queViw4;
    private String queViw5;
    private String queViw6;
    private String queViw7;
    private String queViw8;
    private String queViw9;
    private String queViw10;
    private String hint1;
    private String hint2;
    private String hint3;
    private String hint4;
    private String hint5;
    private String hint6;
    private String hint7;
    private String hint8;
    private String hint9;
    private String hint10;
    private int queCount;
    private String queType;
    private String queNm;
    private String isuse;

    // 설문 세트 (Set)
    private int setId;
    private String setTitle;
    private String setDesc;
    private int cnt;
    private int qseq;

    // 설문 (Survey)
    private int surveyId;
    private String surveyTitle;
    private String surveyDesc;
    private String surveySdat;
    private String surveyEdat;
    private String surveyTarget;

    // 설문 결과
    private String userId;
    private String userNm;
    private String userAnsw;
    private int a1;
    private int a2;
    private int a3;
    private int a4;
    private int a5;
    private int a6;
    private int a7;
    private int a8;
    private int a9;
    private int a10;
    private int asum;

    // 검색 조건
    private String searchKind;
    private String searchText;
    private String chk;

    public int getQueId() { return queId; }
    public void setQueId(int queId) { this.queId = queId; }
    public String getQueOwner() { return queOwner; }
    public void setQueOwner(String queOwner) { this.queOwner = queOwner; }
    public String getQueTitle() { return queTitle; }
    public void setQueTitle(String queTitle) { this.queTitle = queTitle; }
    public String getQueDesc() { return queDesc; }
    public void setQueDesc(String queDesc) { this.queDesc = queDesc; }
    public String getQueViw1() { return queViw1; }
    public void setQueViw1(String queViw1) { this.queViw1 = queViw1; }
    public String getQueViw2() { return queViw2; }
    public void setQueViw2(String queViw2) { this.queViw2 = queViw2; }
    public String getQueViw3() { return queViw3; }
    public void setQueViw3(String queViw3) { this.queViw3 = queViw3; }
    public String getQueViw4() { return queViw4; }
    public void setQueViw4(String queViw4) { this.queViw4 = queViw4; }
    public String getQueViw5() { return queViw5; }
    public void setQueViw5(String queViw5) { this.queViw5 = queViw5; }
    public String getQueViw6() { return queViw6; }
    public void setQueViw6(String queViw6) { this.queViw6 = queViw6; }
    public String getQueViw7() { return queViw7; }
    public void setQueViw7(String queViw7) { this.queViw7 = queViw7; }
    public String getQueViw8() { return queViw8; }
    public void setQueViw8(String queViw8) { this.queViw8 = queViw8; }
    public String getQueViw9() { return queViw9; }
    public void setQueViw9(String queViw9) { this.queViw9 = queViw9; }
    public String getQueViw10() { return queViw10; }
    public void setQueViw10(String queViw10) { this.queViw10 = queViw10; }
    public String getHint1() { return hint1; }
    public void setHint1(String hint1) { this.hint1 = hint1; }
    public String getHint2() { return hint2; }
    public void setHint2(String hint2) { this.hint2 = hint2; }
    public String getHint3() { return hint3; }
    public void setHint3(String hint3) { this.hint3 = hint3; }
    public String getHint4() { return hint4; }
    public void setHint4(String hint4) { this.hint4 = hint4; }
    public String getHint5() { return hint5; }
    public void setHint5(String hint5) { this.hint5 = hint5; }
    public String getHint6() { return hint6; }
    public void setHint6(String hint6) { this.hint6 = hint6; }
    public String getHint7() { return hint7; }
    public void setHint7(String hint7) { this.hint7 = hint7; }
    public String getHint8() { return hint8; }
    public void setHint8(String hint8) { this.hint8 = hint8; }
    public String getHint9() { return hint9; }
    public void setHint9(String hint9) { this.hint9 = hint9; }
    public String getHint10() { return hint10; }
    public void setHint10(String hint10) { this.hint10 = hint10; }
    public int getQueCount() { return queCount; }
    public void setQueCount(int queCount) { this.queCount = queCount; }
    public String getQueType() { return queType; }
    public void setQueType(String queType) { this.queType = queType; }
    public String getQueNm() { return queNm; }
    public void setQueNm(String queNm) { this.queNm = queNm; }
    public String getIsuse() { return isuse; }
    public void setIsuse(String isuse) { this.isuse = isuse; }
    public int getSetId() { return setId; }
    public void setSetId(int setId) { this.setId = setId; }
    public String getSetTitle() { return setTitle; }
    public void setSetTitle(String setTitle) { this.setTitle = setTitle; }
    public String getSetDesc() { return setDesc; }
    public void setSetDesc(String setDesc) { this.setDesc = setDesc; }
    public int getCnt() { return cnt; }
    public void setCnt(int cnt) { this.cnt = cnt; }
    public int getQseq() { return qseq; }
    public void setQseq(int qseq) { this.qseq = qseq; }
    public int getSurveyId() { return surveyId; }
    public void setSurveyId(int surveyId) { this.surveyId = surveyId; }
    public String getSurveyTitle() { return surveyTitle; }
    public void setSurveyTitle(String surveyTitle) { this.surveyTitle = surveyTitle; }
    public String getSurveyDesc() { return surveyDesc; }
    public void setSurveyDesc(String surveyDesc) { this.surveyDesc = surveyDesc; }
    public String getSurveySdat() { return surveySdat; }
    public void setSurveySdat(String surveySdat) { this.surveySdat = surveySdat; }
    public String getSurveyEdat() { return surveyEdat; }
    public void setSurveyEdat(String surveyEdat) { this.surveyEdat = surveyEdat; }
    public String getSurveyTarget() { return surveyTarget; }
    public void setSurveyTarget(String surveyTarget) { this.surveyTarget = surveyTarget; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserNm() { return userNm; }
    public void setUserNm(String userNm) { this.userNm = userNm; }
    public String getUserAnsw() { return userAnsw; }
    public void setUserAnsw(String userAnsw) { this.userAnsw = userAnsw; }
    public int getA1() { return a1; }
    public void setA1(int a1) { this.a1 = a1; }
    public int getA2() { return a2; }
    public void setA2(int a2) { this.a2 = a2; }
    public int getA3() { return a3; }
    public void setA3(int a3) { this.a3 = a3; }
    public int getA4() { return a4; }
    public void setA4(int a4) { this.a4 = a4; }
    public int getA5() { return a5; }
    public void setA5(int a5) { this.a5 = a5; }
    public int getA6() { return a6; }
    public void setA6(int a6) { this.a6 = a6; }
    public int getA7() { return a7; }
    public void setA7(int a7) { this.a7 = a7; }
    public int getA8() { return a8; }
    public void setA8(int a8) { this.a8 = a8; }
    public int getA9() { return a9; }
    public void setA9(int a9) { this.a9 = a9; }
    public int getA10() { return a10; }
    public void setA10(int a10) { this.a10 = a10; }
    public int getAsum() { return asum; }
    public void setAsum(int asum) { this.asum = asum; }
    public String getSearchKind() { return searchKind; }
    public void setSearchKind(String searchKind) { this.searchKind = searchKind; }
    public String getSearchText() { return searchText; }
    public void setSearchText(String searchText) { this.searchText = searchText; }
    public String getChk() { return chk; }
    public void setChk(String chk) { this.chk = chk; }
}
