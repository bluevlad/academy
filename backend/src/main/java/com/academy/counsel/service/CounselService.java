package com.academy.counsel.service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.CounselMapper;

/**
 * 상담 관리 Service 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            상담 관리 신규 생성
 * </pre>
 */
@Service
public class CounselService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CounselMapper counselMapper;

    public CounselService(CounselMapper counselMapper) {
        this.counselMapper = counselMapper;
    }

    // =====================================================
    // 상담 일정 (COUNSEL_SCH) 관련
    // =====================================================

    /**
     * 상담 일정 일자 목록 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 일정 일자 목록
     */
    public List<JSONObject> selectScheduleDayList(CounselScheduleVO counselScheduleVO) {
        return counselMapper.selectScheduleDayList(counselScheduleVO);
    }

    /**
     * 상담 일정 상세 목록 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 일정 상세 목록
     */
    public List<JSONObject> selectScheduleList(CounselScheduleVO counselScheduleVO) {
        return counselMapper.selectScheduleList(counselScheduleVO);
    }

    /**
     * 상담 일정 테이블 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 일정 테이블
     */
    public List<JSONObject> selectScheduleTable(CounselScheduleVO counselScheduleVO) {
        return counselMapper.selectScheduleTable(counselScheduleVO);
    }

    /**
     * 상담 시간 테이블 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 시간 테이블
     */
    public List<JSONObject> selectTimeTable(CounselScheduleVO counselScheduleVO) {
        return counselMapper.selectTimeTable(counselScheduleVO);
    }

    /**
     * 상담 일정 중복 건수 조회
     * @param counselScheduleVO 검색조건
     * @return int 중복 건수
     */
    public int selectScheduleCount(CounselScheduleVO counselScheduleVO) {
        return counselMapper.selectScheduleCount(counselScheduleVO);
    }

    /**
     * 상담 일정 등록
     * @param counselScheduleVO 상담 일정 정보
     */
    public void insertSchedule(CounselScheduleVO counselScheduleVO) {
        counselMapper.insertSchedule(counselScheduleVO);
    }

    /**
     * 상담 일정 일괄 등록
     * @param counselScheduleVO 상담 일정 정보
     */
    public void insertScheduleBatch(CounselScheduleVO counselScheduleVO) throws Exception {
        String catCds = counselScheduleVO.getCatCds();
        if (catCds != null && catCds.length() > 1) {
            String[] cats = catCds.split(",");
            for (String cat : cats) {
                if (cat != null && cat.length() > 0) {
                    counselScheduleVO.setCatCd(cat);

                    for (int i = 0; i < counselScheduleVO.getDateCount(); i++) {
                        String sDate = counselScheduleVO.getsDate();
                        String year = sDate.substring(0, 4);
                        String month = sDate.substring(4, 6);
                        String day = sDate.substring(6, 8);

                        String dateStr = year + "-" + month + "-" + day;
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = df.parse(dateStr);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.DATE, i);

                        counselScheduleVO.setSchDay(df.format(cal.getTime()));
                        Integer temp = counselScheduleVO.getReqCnt();

                        for (int x = 1; x <= counselScheduleVO.getTimeCount(); x++) {
                            if (x == 3) {
                                counselScheduleVO.setReqCnt(0);
                            } else {
                                counselScheduleVO.setReqCnt(temp);
                            }
                            counselScheduleVO.setTsIdx(x);
                            counselMapper.insertSchedule(counselScheduleVO);
                        }
                    }
                }
            }
        }
    }

    /**
     * 상담 일정 수정
     * @param counselScheduleVO 상담 일정 정보
     */
    public void updateSchedule(CounselScheduleVO counselScheduleVO) {
        counselMapper.updateSchedule(counselScheduleVO);
    }

    /**
     * 상담 일정 삭제
     * @param counselScheduleVO 상담 일정 정보
     */
    public void deleteSchedule(CounselScheduleVO counselScheduleVO) {
        counselMapper.deleteSchedule(counselScheduleVO);
    }

    /**
     * 상담 일정 일괄 삭제
     * @param counselScheduleVO 상담 일정 정보
     */
    public void deleteScheduleBatch(CounselScheduleVO counselScheduleVO) {
        String[] delArr = counselScheduleVO.getDelArr();
        if (delArr != null) {
            for (String del : delArr) {
                String[] dels = del.split("#");
                counselScheduleVO.setDelDate(dels[0]);
                counselScheduleVO.setCatCd(dels[1]);
                counselMapper.deleteSchedule(counselScheduleVO);
            }
        }
    }

    // =====================================================
    // 상담 신청 (COUNSEL_RST) 관련
    // =====================================================

    /**
     * 상담 신청 목록 조회
     * @param counselRequestVO 검색조건
     * @return List 상담 신청 목록
     */
    public List<JSONObject> selectCounselRequestList(CounselRequestVO counselRequestVO) {
        return counselMapper.selectCounselRequestList(counselRequestVO);
    }

    /**
     * 상담 신청 목록 건수 조회
     * @param counselRequestVO 검색조건
     * @return int 상담 신청 목록 건수
     */
    public int selectCounselRequestListCount(CounselRequestVO counselRequestVO) {
        return counselMapper.selectCounselRequestListCount(counselRequestVO);
    }

    /**
     * 상담 신청 명단 조회 (예약 현황)
     * @param counselRequestVO 검색조건
     * @return List 상담 신청 명단
     */
    public List<JSONObject> selectCounselReqList(CounselRequestVO counselRequestVO) {
        return counselMapper.selectCounselReqList(counselRequestVO);
    }

    /**
     * 개인 상담 신청 내역 조회
     * @param counselRequestVO 검색조건
     * @return List 개인 상담 신청 내역
     */
    public List<JSONObject> selectCounselUserReq(CounselRequestVO counselRequestVO) {
        return counselMapper.selectCounselUserReq(counselRequestVO);
    }

    // =====================================================
    // 설명회 신청 (TB_PRESENT) 관련
    // =====================================================

    /**
     * 설명회 신청 목록 조회
     * @param presentRequestVO 검색조건
     * @return List 설명회 신청 목록
     */
    public List<JSONObject> selectPresentReqList(PresentRequestVO presentRequestVO) {
        return counselMapper.selectPresentReqList(presentRequestVO);
    }

    /**
     * 설명회 신청 목록 건수 조회
     * @param presentRequestVO 검색조건
     * @return int 설명회 신청 목록 건수
     */
    public int selectPresentReqListCount(PresentRequestVO presentRequestVO) {
        return counselMapper.selectPresentReqListCount(presentRequestVO);
    }

    /**
     * 설명회 코드 목록 조회
     * @param presentRequestVO 검색조건
     * @return List 설명회 코드 목록
     */
    public List<JSONObject> selectPresentCodeList(PresentRequestVO presentRequestVO) {
        return counselMapper.selectPresentCodeList(presentRequestVO);
    }
}
