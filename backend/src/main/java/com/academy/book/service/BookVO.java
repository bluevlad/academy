package com.academy.book.service;


import com.academy.common.CommonVO;

/**
 * Book Value Object
 * TB_CA_BOOK 테이블 매핑 VO
 */
public class BookVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Book specific fields
    private Integer seq;
    private String rscId;
    private String subjectSjtCd;
    private String categoryCd;
    private String learningCd;
    private String bookNm;
    private String bookInfo;
    private String bookMemo;
    private String bookKeyword;
    private String issueDate;
    private String coverType;
    private String bookContents;
    private Integer price;
    private Integer discount;
    private Integer discountPrice;
    private Integer point;
    private String bookPublishers;
    private String bookAuthor;
    private String bookSupplementdata;
    private String bookPrintingdate;
    private String bookMain;
    private String bookSub;
    private String bookStudentbook;
    private String attachFile;
    private String attachImgL;
    private String attachImgM;
    private String attachImgS;
    private String attachDetailInfo;
    private Integer bookStock;
    private String freePost;
    private String bookDate;
    private String newBook;
    private String mainView;
    private String useYn;
    private String bookPage;
    private String bookFormat;

    // Additional fields for display
    private String categoryNm;
    private String learningNm;
    private Integer relcnt;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getBookNm() {
        return bookNm;
    }

    public void setBookNm(String bookNm) {
        this.bookNm = bookNm;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getBookMemo() {
        return bookMemo;
    }

    public void setBookMemo(String bookMemo) {
        this.bookMemo = bookMemo;
    }

    public String getBookKeyword() {
        return bookKeyword;
    }

    public void setBookKeyword(String bookKeyword) {
        this.bookKeyword = bookKeyword;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getBookContents() {
        return bookContents;
    }

    public void setBookContents(String bookContents) {
        this.bookContents = bookContents;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(String bookPublishers) {
        this.bookPublishers = bookPublishers;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookSupplementdata() {
        return bookSupplementdata;
    }

    public void setBookSupplementdata(String bookSupplementdata) {
        this.bookSupplementdata = bookSupplementdata;
    }

    public String getBookPrintingdate() {
        return bookPrintingdate;
    }

    public void setBookPrintingdate(String bookPrintingdate) {
        this.bookPrintingdate = bookPrintingdate;
    }

    public String getBookMain() {
        return bookMain;
    }

    public void setBookMain(String bookMain) {
        this.bookMain = bookMain;
    }

    public String getBookSub() {
        return bookSub;
    }

    public void setBookSub(String bookSub) {
        this.bookSub = bookSub;
    }

    public String getBookStudentbook() {
        return bookStudentbook;
    }

    public void setBookStudentbook(String bookStudentbook) {
        this.bookStudentbook = bookStudentbook;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachImgL() {
        return attachImgL;
    }

    public void setAttachImgL(String attachImgL) {
        this.attachImgL = attachImgL;
    }

    public String getAttachImgM() {
        return attachImgM;
    }

    public void setAttachImgM(String attachImgM) {
        this.attachImgM = attachImgM;
    }

    public String getAttachImgS() {
        return attachImgS;
    }

    public void setAttachImgS(String attachImgS) {
        this.attachImgS = attachImgS;
    }

    public String getAttachDetailInfo() {
        return attachDetailInfo;
    }

    public void setAttachDetailInfo(String attachDetailInfo) {
        this.attachDetailInfo = attachDetailInfo;
    }

    public Integer getBookStock() {
        return bookStock;
    }

    public void setBookStock(Integer bookStock) {
        this.bookStock = bookStock;
    }

    public String getFreePost() {
        return freePost;
    }

    public void setFreePost(String freePost) {
        this.freePost = freePost;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getNewBook() {
        return newBook;
    }

    public void setNewBook(String newBook) {
        this.newBook = newBook;
    }

    public String getMainView() {
        return mainView;
    }

    public void setMainView(String mainView) {
        this.mainView = mainView;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getBookPage() {
        return bookPage;
    }

    public void setBookPage(String bookPage) {
        this.bookPage = bookPage;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getLearningNm() {
        return learningNm;
    }

    public void setLearningNm(String learningNm) {
        this.learningNm = learningNm;
    }

    public Integer getRelcnt() {
        return relcnt;
    }

    public void setRelcnt(Integer relcnt) {
        this.relcnt = relcnt;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}
