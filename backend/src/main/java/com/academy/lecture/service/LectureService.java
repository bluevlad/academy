package com.academy.lecture.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.LectureMapper;

/**
 * Lecture Service
 * 강의 관리 서비스 (직접 구현)
 */
@Service(value="lectureservice")
public class LectureService {

    @Autowired
    private LectureMapper lectureMapper;

    /**
     * 강의 목록 조회
     */
    public List<HashMap<String, String>> lectureList(LectureVO lectureVO){
        return lectureMapper.lectureList(lectureVO);
    }

    /**
     * 구 무료강의를 신 무료강의로 조회
     */
    public List<HashMap<String, String>> oldFreeToNewFree(LectureVO lectureVO){
        return lectureMapper.oldFreeToNewFree(lectureVO);
    }

    /**
     * 강의 목록 개수 조회
     */
    public int lectureListCount(LectureVO lectureVO){
        return lectureMapper.lectureListCount(lectureVO);
    }

    /**
     * 교재 목록 조회
     */
    public List<HashMap<String, String>> bookList(LectureVO lectureVO){
        return lectureMapper.bookList(lectureVO);
    }

    /**
     * 교재 목록 개수 조회
     */
    public int bookListCount(LectureVO lectureVO){
        return lectureMapper.bookListCount(lectureVO);
    }

    /**
     * Bridge Leccode 시퀀스 조회
     */
    public List<HashMap<String, String>> getBridgeLeccodeSeq(LectureVO lectureVO){
        return lectureMapper.getBridgeLeccodeSeq(lectureVO);
    }

    /**
     * Jong Leccode 시퀀스 조회
     */
    public List<HashMap<String, String>> getJongLeccodeSeq(LectureVO lectureVO){
        return lectureMapper.getJongLeccodeSeq(lectureVO);
    }

    /**
     * Leccode 조회
     */
    public List<HashMap<String, String>> getLeccode(LectureVO lectureVO){
        return lectureMapper.getLeccode(lectureVO);
    }

    /**
     * Bridge Leccode 조회
     */
    public List<HashMap<String, String>> getBridgeLeccode(LectureVO lectureVO){
        return lectureMapper.getBridgeLeccode(lectureVO);
    }

    /**
     * Bridge Leccode 조회
     */
    public List<HashMap<String, String>> BridgeLeccode(LectureVO lectureVO){
        return lectureMapper.BridgeLeccode(lectureVO);
    }

    /**
     * 강의 등록
     */
    public void lectureInsert(LectureVO lectureVO){
        lectureMapper.lectureInsert(lectureVO);
    }

    /**
     * 강의 Bridge 등록
     */
    public void lectureBridgeInsert(LectureVO lectureVO){
        lectureMapper.lectureBridgeInsert(lectureVO);
    }

    /**
     * 강의 교재 등록
     */
    public void lectureBookInsert(LectureVO lectureVO){
        lectureMapper.lectureBookInsert(lectureVO);
    }

    /**
     * 강의 교재 등록2
     */
    public void lectureBookInsert2(LectureVO lectureVO){
        lectureMapper.lectureBookInsert2(lectureVO);
    }

    /**
     * 강의 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureViewList(LectureVO lectureVO){
        return lectureMapper.lectureViewList(lectureVO);
    }

    /**
     * 강의 상세 조회
     */
    public List<HashMap<String, String>> lectureView(LectureVO lectureVO){
        return lectureMapper.lectureView(lectureVO);
    }

    /**
     * 강의 교재 목록 조회
     */
    public List<HashMap<String, String>> lectureViewBookList(LectureVO lectureVO){
        return lectureMapper.lectureViewBookList(lectureVO);
    }

    /**
     * 강의 교재 삭제
     */
    public void lectureBookDelete(LectureVO lectureVO){
        lectureMapper.lectureBookDelete(lectureVO);
    }

    /**
     * 강의 수정
     */
    public void lectureUpdate(LectureVO lectureVO){
        lectureMapper.lectureUpdate(lectureVO);
    }

    /**
     * 강의 온/오프 수정
     */
    public void Modify_Lecture_On_Off(LectureVO lectureVO){
        lectureMapper.Modify_Lecture_On_Off(lectureVO);
    }

    /**
     * 강의 사용여부 수정
     */
    public void lectureIsUseUpdate(LectureVO lectureVO){
        lectureMapper.lectureIsUseUpdate(lectureVO);
    }

    /**
     * 강의 삭제
     */
    public void lectureDelete(LectureVO lectureVO){
        lectureMapper.lectureDelete(lectureVO);
    }

    /**
     * 강의 Bridge 삭제
     */
    public void lectureBridgeDelete(LectureVO lectureVO){
        lectureMapper.lectureBridgeDelete(lectureVO);
    }

    /**
     * 강의 동영상 수정
     */
    public void lecMovUpdate(LectureVO lectureVO){
        lectureMapper.lecMovUpdate(lectureVO);
    }

    /**
     * 강의 순서 목록 조회
     */
    public List<HashMap<String, String>> lectureSeqList(LectureVO lectureVO){
        return lectureMapper.lectureSeqList(lectureVO);
    }

    /**
     * 강의 순서 수정
     */
    public void lectureSeqUpdate(LectureVO lectureVO){
        lectureMapper.lectureSeqUpdate(lectureVO);
    }

    /**
     * 강의 Jong 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureViewJongList(LectureVO lectureVO){
        return lectureMapper.lectureViewJongList(lectureVO);
    }

    /**
     * 강의 Jong 목록 조회
     */
    public List<HashMap<String, String>> lectureJongList(LectureVO lectureVO){
        return lectureMapper.lectureJongList(lectureVO);
    }

    /**
     * 강의 Jong 목록 개수 조회
     */
    public int lectureJongListCount(LectureVO lectureVO){
        return lectureMapper.lectureJongListCount(lectureVO);
    }

    /**
     * 강의 연간 목록 조회
     */
    public List<HashMap<String, String>> lectureYearList(LectureVO lectureVO){
        return lectureMapper.lectureYearList(lectureVO);
    }

    /**
     * 강의 연간 목록 개수 조회
     */
    public int lectureYearListCount(LectureVO lectureVO){
        return lectureMapper.lectureYearListCount(lectureVO);
    }

    /**
     * 강의 Jong 과목 목록 조회
     */
    public List<HashMap<String, String>> lectureJongSubjectList(LectureVO lectureVO){
        return lectureMapper.lectureJongSubjectList(lectureVO);
    }

    /**
     * 강의 Jong 상세 조회
     */
    public List<HashMap<String, String>> lectureJongView(LectureVO lectureVO){
        return lectureMapper.lectureJongView(lectureVO);
    }

    /**
     * 강의 Jong 과목 목록 개수 조회
     */
    public int lectureJongSubjectListCount(LectureVO lectureVO){
        return lectureMapper.lectureJongSubjectListCount(lectureVO);
    }

    /**
     * 강의 LecJong 등록
     */
    public void lectureLecJongInsert(LectureVO lectureVO){
        lectureMapper.lectureLecJongInsert(lectureVO);
    }

    /**
     * 강의 ChoiceJong 번호 등록
     */
    public void lectureChoiceJongNoInsert(LectureVO lectureVO){
        lectureMapper.lectureChoiceJongNoInsert(lectureVO);
    }

    /**
     * 강의 Leccode 목록 조회
     */
    public List<HashMap<String, String>> lectureViewLeccodeList(LectureVO lectureVO){
        return lectureMapper.lectureViewLeccodeList(lectureVO);
    }

    /**
     * 강의 LecJong 삭제
     */
    public void lectureLecJongDelete(LectureVO lectureVO){
        lectureMapper.lectureLecJongDelete(lectureVO);
    }

    /**
     * 강의 ChoiceJong 번호 삭제
     */
    public void lectureChoiceJongNoDelete(LectureVO lectureVO){
        lectureMapper.lectureChoiceJongNoDelete(lectureVO);
    }

    /**
     * 강의 결제 목록 조회
     */
    public List<HashMap<String, String>> lecturePayList(LectureVO lectureVO){
        return lectureMapper.lecturePayList(lectureVO);
    }

    /**
     * 강의 Jong 결제 목록 조회
     */
    public List<HashMap<String, String>> lectureJongPayList(LectureVO lectureVO){
        return lectureMapper.lectureJongPayList(lectureVO);
    }

    /**
     * 강의 데이터 메모 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureDataMemoViewList(LectureVO lectureVO){
        return lectureMapper.lectureDataMemoViewList(lectureVO);
    }

    /**
     * 강의 데이터 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureDataViewList(LectureVO lectureVO){
        return lectureMapper.lectureDataViewList(lectureVO);
    }

    /**
     * 강의 모바일 목록 조회
     */
    public List<HashMap<String, String>> lectureMobileList(LectureVO lectureVO){
        return lectureMapper.lectureMobileList(lectureVO);
    }

    /**
     * 강의 데이터 동영상 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureDataMovieViewList(LectureVO lectureVO){
        return lectureMapper.lectureDataMovieViewList(lectureVO);
    }

    /**
     * 강의 데이터 동영상 목록 조회
     */
    public List<HashMap<String, String>> lectureDataMovieList(LectureVO lectureVO){
        return lectureMapper.lectureDataMovieList(lectureVO);
    }

    /**
     * 강의 동영상 등록
     */
    public void lectureMovieInsert(LectureVO lectureVO){
        lectureMapper.lectureMovieInsert(lectureVO);
    }

    /**
     * 강의 동영상 삭제
     */
    public void lectureMovieDelete(LectureVO lectureVO){
        lectureMapper.lectureMovieDelete(lectureVO);
    }

    /**
     * 강의 동영상 수정
     */
    public void lectureMovieUpdate(LectureVO lectureVO){
        lectureMapper.lectureMovieUpdate(lectureVO);
    }

    /**
     * 강의 동영상 파일 삭제
     */
    public void lectureMovieFileDelete(LectureVO lectureVO){
        lectureMapper.lectureMovieFileDelete(lectureVO);
    }

    /**
     * 강의 동영상 메모 등록
     */
    public void lectureMovieMemoInsert(LectureVO lectureVO){
        lectureMapper.lectureMovieMemoInsert(lectureVO);
    }

    /**
     * 강의 동영상 메모 수정
     */
    public void lectureMovieMemoUpdate(LectureVO lectureVO){
        lectureMapper.lectureMovieMemoUpdate(lectureVO);
    }

    /**
     * 강의 동영상 메모 삭제
     */
    public void lectureMovieMemoDelete(LectureVO lectureVO){
        lectureMapper.lectureMovieMemoDelete(lectureVO);
    }

    /**
     * 강의 삭제 체크
     */
    public int lectureDeleteCheck(LectureVO lectureVO){
        return lectureMapper.lectureDeleteCheck(lectureVO);
    }

    /**
     * 플레이 정보 조회
     */
    public List<HashMap<String, String>> playinfo(LectureVO lectureVO){
        return lectureMapper.playinfo(lectureVO);
    }

    /**
     * 무료 강의 관리자 조회
     */
    public List<HashMap<String, String>> getCbMovie4_free_admin(LectureVO lectureVO){
        return lectureMapper.getCbMovie4_free_admin(lectureVO);
    }

    /**
     * 무료 강의 관리자 개수 조회
     */
    public int getCbMovie4_free_admin_count(LectureVO lectureVO){
        return lectureMapper.getCbMovie4_free_admin_count(lectureVO);
    }

    /**
     * 강의 온라인 상세 조회
     */
    public HashMap<String, String> lectureOnDetailS(LectureVO lectureVO) {
        return lectureMapper.lectureOnDetailS(lectureVO);
    }

    /**
     * PMP 다운로드 로그 등록
     */
    public void insertPmpDownLog(LectureVO lectureVO){
        lectureMapper.insertPmpDownLog(lectureVO);
    }

    /**
     * WMV 강의 조회
     */
    public List<HashMap<String, String>> lectureWMV(LectureVO lectureVO){
        return lectureMapper.lectureWMV(lectureVO);
    }

    /**
     * 강의 다운로드 횟수 조회
     */
    public List<HashMap<String, String>> lectureDown_Count(LectureVO lectureVO){
        return lectureMapper.lectureDown_Count(lectureVO);
    }

    /**
     * 구 무료강의를 신 무료강의로 등록 (복잡한 로직이므로 Object 유지)
     */
    @SuppressWarnings("unchecked")
    public void oldFreeToNewFreeInsert(Object params){
        HashMap<String, String> paramMap = (HashMap<String, String>) params;
        Calendar cal = Calendar.getInstance();

        List<HashMap<String, String>> oldfreetonewfree_list = lectureMapper.oldFreeToNewFree(params);

        if(null != oldfreetonewfree_list && oldfreetonewfree_list.size() > 0) {
            for(int i=0; i<oldfreetonewfree_list.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)oldfreetonewfree_list.get(i);
                String LECCODE = map.get("LECCODE");

                paramMap.put("SHEARH_LECCODE", LECCODE);

                String SEQ = "";
                String BRIDGE_LECCODE = "";

                paramMap.put("PREFIX", "R" + cal.get(Calendar.YEAR));

                List<HashMap<String, String>> getBridgeLeccodeSeqList = lectureMapper.getBridgeLeccodeSeq(params);
                List<HashMap<String, String>> getBridgeLeccodeList = lectureMapper.getBridgeLeccode(params);
                if(getBridgeLeccodeList.size() > 0){
                    BRIDGE_LECCODE = paramMap.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
                }else{
                    BRIDGE_LECCODE = paramMap.get("PREFIX") + "00001";
                }

                paramMap.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));

                paramMap.put("PREFIX", "F"+ cal.get(Calendar.YEAR));

                if(getBridgeLeccodeSeqList.size() > 0){
                    SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
                    paramMap.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
                }else{
                    SEQ = "1";
                    paramMap.put("SEQ", "1");
                }

                List<HashMap<String, String>> getLeccodeList = lectureMapper.getLeccode(params);
                if(getLeccodeList.size() > 0)
                    LECCODE = paramMap.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
                else
                    LECCODE = paramMap.get("PREFIX") + "00001";
                paramMap.put("LECCODE", LECCODE.replace(" ", ""));

                String rleccode = lectureMapper.getRleccode(params);

                lectureMapper.oldFreeToNewFreeInsert(params);

                paramMap.put("SEQ", SEQ);
                paramMap.put("REG_ID", "ADMIN");
                paramMap.put("UPD_ID", "ADMIN");
                lectureMapper.lectureBridgeInsert(params);

                paramMap.put("RLECCODE", rleccode);
                lectureMapper.oldTbmovieToNewTbmovieInsert(params);
            }
        }
    }

    /**
     * 구 보강 무료강의를 신 보강 무료강의로 등록 (복잡한 로직이므로 Object 유지)
     */
    @SuppressWarnings("unchecked")
    public void oldBogangFreeToNewBogangFree(Object params){
        HashMap<String, String> paramMap = (HashMap<String, String>) params;
        Calendar cal = Calendar.getInstance();

        List<HashMap<String, String>> oldfreetonewfree_list = lectureMapper.oldBogangFreeToNewBogangFree(params);

        if(null != oldfreetonewfree_list && oldfreetonewfree_list.size() > 0) {
            for(int i=0; i<oldfreetonewfree_list.size(); i++){
                HashMap<String, String> map = (HashMap<String, String>)oldfreetonewfree_list.get(i);
                String LECCODE = map.get("LECCODE");

                paramMap.put("SHEARH_LECCODE", LECCODE);

                String SEQ = "";
                String BRIDGE_LECCODE = "";

                paramMap.put("PREFIX", "R" + cal.get(Calendar.YEAR));

                List<HashMap<String, String>> getBridgeLeccodeSeqList = lectureMapper.getBridgeLeccodeSeq(params);
                List<HashMap<String, String>> getBridgeLeccodeList = lectureMapper.getBridgeLeccode(params);
                if(getBridgeLeccodeList.size() > 0){
                    BRIDGE_LECCODE = paramMap.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
                }else{
                    BRIDGE_LECCODE = paramMap.get("PREFIX") + "00001";
                }

                paramMap.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));

                paramMap.put("PREFIX", "F"+ cal.get(Calendar.YEAR));

                if(getBridgeLeccodeSeqList.size() > 0){
                    SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
                    paramMap.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
                }else{
                    SEQ = "1";
                    paramMap.put("SEQ", "1");
                }

                List<HashMap<String, String>> getLeccodeList = lectureMapper.getLeccode(params);
                if(getLeccodeList.size() > 0)
                    LECCODE = paramMap.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
                else
                    LECCODE = paramMap.get("PREFIX") + "00001";
                paramMap.put("LECCODE", LECCODE.replace(" ", ""));

                String rleccode = lectureMapper.getRleccode(params);

                lectureMapper.oldBogangFreeToNewFreeBogangInsert(params);

                paramMap.put("SEQ", SEQ);
                paramMap.put("REG_ID", "ADMIN");
                paramMap.put("UPD_ID", "ADMIN");
                lectureMapper.lectureBridgeInsert(params);

                System.out.println("새로운 BRIDGE_LECCODE="+paramMap.get("BRIDGE_LECCODE"));
                System.out.println("이전 BRIDGE_LECCODE="+rleccode);
                System.out.println("이전 무료단과코드="+paramMap.get("SHEARH_LECCODE"));
                System.out.println("현재 단과코드="+paramMap.get("LECCODE"));

                paramMap.put("RLECCODE", rleccode);
                lectureMapper.oldTbmovieToNewTbmovieInsert(params);
            }
        }
    }

    /**
     * 강의 무료 패스 결제 목록 조회
     */
    public List<HashMap<String, String>> lectureFreePassPayList(LectureVO lectureVO){
        return lectureMapper.lectureFreePassPayList(lectureVO);
    }

    /**
     * 진행중인 연간 목록 조회
     */
    public List<HashMap<String, String>> YearIngList(LectureVO lectureVO){
        return lectureMapper.YearIngList(lectureVO);
    }

    /**
     * 내 진행중인 연간 목록 조회
     */
    public List<HashMap<String, String>> MyYearIngList(LectureVO lectureVO){
        return lectureMapper.MyYearIngList(lectureVO);
    }

    /**
     * 교재 상세 조회
     */
    public List<HashMap<String, String>> bookView(LectureVO lectureVO){
        return lectureMapper.bookView(lectureVO);
    }
}