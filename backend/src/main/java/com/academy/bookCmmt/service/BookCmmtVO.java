package com.academy.bookCmmt.service;


import com.academy.common.CommonVO;

/**
 * BookCmmt Value Object
 * 교재 구매 후기 관련 VO
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 */
public class BookCmmtVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // BookCmmt specific fields
    private String seq;
    private String rscId;
    private String deleteRscId;
    private String deleteSeq;

    // Getter and Setter
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getDeleteRscId() {
        return deleteRscId;
    }

    public void setDeleteRscId(String deleteRscId) {
        this.deleteRscId = deleteRscId;
    }

    public String getDeleteSeq() {
        return deleteSeq;
    }

    public void setDeleteSeq(String deleteSeq) {
        this.deleteSeq = deleteSeq;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}
