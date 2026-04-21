package com.academy.event.service;


import com.academy.common.CommonVO;

/**
 * 이벤트 파일 VO 클래스 (TB_EVENT_FILE 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            이벤트 파일 관리 신규 생성
 * </pre>
 */
public class EventFileVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 이벤트 번호 */
    private String eventNo;

    /** 파일 번호 */
    private String fileNo;

    /** 파일명 */
    private String fileName;

    /** 파일 경로 */
    private String filePath;

    /** 실제 파일명 */
    private String realFileName;

    /** 체크 여부 */
    private String isChk;

    /** 등록자 ID */
    private String regId;

    /** 등록일시 */
    private String regDt;

    /** 수정자 ID */
    private String updId;

    /** 수정일시 */
    private String updDt;

    // Getters and Setters
    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getIsChk() {
        return isChk;
    }

    public void setIsChk(String isChk) {
        this.isChk = isChk;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUpdDt() {
        return updDt;
    }

    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }
}
