package com.academy.lecture.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.TeacherMapper;

/**
 * Teacher Service
 * 강사 관리 서비스 (직접 구현)
 */
@Service(value="teacherservice")
public class TeacherService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * Kind 리스트 조회
     */
    public List<HashMap<String, String>> getKindList(TeacherVO teacherVO){
        return teacherMapper.getKindList(teacherVO);
    }

    /**
     * Subject 리스트 조회
     */
    public List<HashMap<String, String>> getSubjectList(TeacherVO teacherVO){
        return teacherMapper.getSubjectList(teacherVO);
    }

    /**
     * Teacher 목록 조회
     */
    public List<HashMap<String, String>> teacherList(TeacherVO teacherVO){
        return teacherMapper.teacherList(teacherVO);
    }

    /**
     * Teacher 목록 개수 조회
     */
    public int teacherListCount(TeacherVO teacherVO){
        return teacherMapper.teacherListCount(teacherVO);
    }

    /**
     * Teacher 전체 목록 조회
     */
    public List<HashMap<String, String>> teacherAllList(TeacherVO teacherVO){
        return teacherMapper.teacherAllList(teacherVO);
    }

    /**
     * Teacher 전체 목록 개수 조회
     */
    public int teacherAllListCount(TeacherVO teacherVO) {
        return teacherMapper.teacherAllListCount(teacherVO);
    }

    /**
     * Teacher ID 중복 체크
     */
    public int teacherIdCheck(TeacherVO teacherVO){
        return teacherMapper.teacherIdCheck(teacherVO);
    }

    /**
     * Teacher 등록 (배열 처리 포함)
     */
    public void teacherInsert(TeacherVO teacherVO){

        teacherMapper.teacherInsert(teacherVO);

        String[] ORI_CATEGORY_CODE = teacherVO.getOriCategoryCode();
        String[] CETCARR = teacherVO.getCetcarr();

        if(ORI_CATEGORY_CODE == null) { // 등록
            for(int i=0; i<CETCARR.length; i++){
                teacherVO.setCategoryCode(CETCARR[i]);
                teacherMapper.teacherCategoryInsert(teacherVO);
            }
        } else {    // 수정
            for(int i=0; i<ORI_CATEGORY_CODE.length;i++){
                boolean isDel = true;
                for(int j=0; j<CETCARR.length; j++){
                    if(ORI_CATEGORY_CODE[i].equals(CETCARR[j])) {
                        isDel = false;
                    }
                }
                if(isDel) {
                    teacherVO.setDelCategoryCode(ORI_CATEGORY_CODE[i]);
                    teacherMapper.teacherCategoryDelete(teacherVO);
                }
            }

            for(int i=0; i<CETCARR.length; i++){
                boolean isIns = true;
                for(int j=0; j<ORI_CATEGORY_CODE.length;j++){
                    if(CETCARR[i].equals(ORI_CATEGORY_CODE[j])) {
                        isIns = false;
                    }
                }
                if(isIns) {
                    teacherVO.setCategoryCode(CETCARR[i]);
                    teacherMapper.teacherCategoryInsert(teacherVO);
                }
            }
        }

        teacherMapper.teacherSubjectDelete(teacherVO);

        String[] SETCARR = teacherVO.getSetcarr();
        if(SETCARR != null){
            for(int i=0; i<SETCARR.length; i++){
                teacherVO.setSubjectCdSingle(SETCARR[i]);
                int teacherMain_Category_Subject = teacherMapper.teacherMain_Category_Subject(teacherVO);
                int teacherIntro_Category_Subject = teacherMapper.teacherIntro_Category_Subject(teacherVO);
                teacherMapper.teacherSubjectInsert(teacherVO);

                if(ORI_CATEGORY_CODE == null) {
                    teacherMapper.teacherMain_Category_Insert(teacherVO);
                    teacherMapper.teacherIntro_Category_Insert(teacherVO);
                    teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
                }else{

                    if(teacherMain_Category_Subject == 0){

                        teacherMapper.teacherMain_Category_Insert(teacherVO);
                    }else{

                    }
                    if(teacherIntro_Category_Subject == 0){

                        teacherMapper.teacherIntro_Category_Insert(teacherVO);
                        teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
                    }else{

                    }
                }
            }
        }
    }

    /**
     * Teacher 상세 조회
     */
    public List<HashMap<String, String>> teacherView(TeacherVO teacherVO){
        return teacherMapper.teacherView(teacherVO);
    }

    /**
     * Teacher 수정 (배열 처리 포함)
     */
    public void teacherUpdate(TeacherVO teacherVO){
        teacherMapper.teacherUpdate(teacherVO);

        String[] ORI_CATEGORY_CODE = teacherVO.getOriCategoryCode();
        String[] CETCARR = teacherVO.getCetcarr();

        if(ORI_CATEGORY_CODE == null) { // 등록
            for(int i=0; i<CETCARR.length; i++){
                teacherVO.setCategoryCode(CETCARR[i]);
                teacherMapper.teacherCategoryInsert(teacherVO);
            }
        } else {    // 수정
            for(int i=0; i<ORI_CATEGORY_CODE.length;i++){
                boolean isDel = true;
                for(int j=0; j<CETCARR.length; j++){
                    if(ORI_CATEGORY_CODE[i].equals(CETCARR[j])) {
                        isDel = false;
                    }
                }
                if(isDel) {
                    teacherVO.setDelCategoryCode(ORI_CATEGORY_CODE[i]);
                    teacherMapper.teacherCategoryDelete(teacherVO);
                }
            }

            for(int i=0; i<CETCARR.length; i++){
                boolean isIns = true;
                for(int j=0; j<ORI_CATEGORY_CODE.length;j++){
                    if(CETCARR[i].equals(ORI_CATEGORY_CODE[j])) {
                        isIns = false;
                    }
                }
                if(isIns) {
                    teacherVO.setCategoryCode(CETCARR[i]);
                    teacherMapper.teacherCategoryInsert(teacherVO);
                }
            }
        }

        teacherMapper.teacherSubjectDelete(teacherVO);
        teacherMapper.teacherMain_Category_Delete(teacherVO);
        teacherMapper.teacherIntro_Category_Delete(teacherVO);
        teacherMapper.teacherIntro_F_Category_Delete(teacherVO);

        String[] SETCARR = teacherVO.getSetcarr();
        String[] OFF_SETCARR = teacherVO.getOffSetcarr();
        String ON_SBJ = "";
        if(SETCARR != null && SETCARR.length > 0){
            for(int i=0; i<SETCARR.length; i++){
                teacherVO.setSubjectCdSingle(SETCARR[i]);
                teacherVO.setUseOn("Y");
                teacherVO.setUseOff("N");
                teacherMapper.teacherSubjectInsert(teacherVO);

                if(ORI_CATEGORY_CODE == null) {
                    teacherMapper.teacherMain_Category_Insert(teacherVO);
                    teacherMapper.teacherIntro_Category_Insert(teacherVO);
                    teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
                }else{
                    int teacherMain_Category_Subject = teacherMapper.teacherMain_Category_Subject(teacherVO);
                    int teacherIntro_Category_Subject = teacherMapper.teacherIntro_Category_Subject(teacherVO);
                    int teacherIntro_F_Category_Subject = teacherMapper.teacherIntro_F_Category_Subject(teacherVO);
                    if(teacherMain_Category_Subject == 0){

                        teacherMapper.teacherMain_Category_Insert(teacherVO);

                    }
                    if(teacherIntro_Category_Subject == 0){
                        teacherMapper.teacherIntro_Category_Insert(teacherVO);
                    }
                    if(teacherIntro_F_Category_Subject == 0){
                        teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
                    }

                }
                ON_SBJ += SETCARR[i]+",";
          }
       }
        teacherVO.setUseOn("");
        teacherVO.setUseOff("");

        if(OFF_SETCARR != null && OFF_SETCARR.length > 0){
            for(int j=0; j<OFF_SETCARR.length; j++){
                if(ON_SBJ.contains(OFF_SETCARR[j])){
                    teacherVO.setUseOff("Y");
                    teacherMapper.teacherSubjectUpdate(teacherVO);
                }else{
                    teacherVO.setSubjectCdSingle(OFF_SETCARR[j]);
                    teacherVO.setUseOn("N");
                    teacherVO.setUseOff("Y");
                    int sbjCnt = teacherMapper.teacherSubjectCount(teacherVO);
                    if(sbjCnt==0){
                        teacherMapper.teacherSubjectInsert(teacherVO);

                        if(ORI_CATEGORY_CODE == null && (SETCARR == null || SETCARR.length < 1)) {
                            teacherMapper.teacherMain_Category_Insert(teacherVO);
                            teacherMapper.teacherIntro_Category_Insert(teacherVO);
                            teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
                        }else{
                            int teacherMain_Category_Subject = teacherMapper.teacherMain_Category_Subject(teacherVO);
                            int teacherIntro_Category_Subject = teacherMapper.teacherIntro_Category_Subject(teacherVO);
                            int teacherIntro_F_Category_Subject = teacherMapper.teacherIntro_F_Category_Subject(teacherVO);
                            if(teacherMain_Category_Subject == 0){

                                teacherMapper.teacherMain_Category_Insert(teacherVO);

                            }
                            if(teacherIntro_Category_Subject == 0){
                                teacherMapper.teacherIntro_Category_Insert(teacherVO);
                            }
                            if(teacherIntro_F_Category_Subject == 0){
                                teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
                            }
                        }
                    }
                }
                teacherVO.setUseOn("");
                teacherVO.setUseOff("");
            }
        }
    }

    /**
     * Teacher Category 등록
     */
    public void teacherCategoryInsert(TeacherVO teacherVO){
        teacherMapper.teacherCategoryInsert(teacherVO);
    }

    /**
     * Teacher Subject 등록
     */
    public void teacherSubjectInsert(TeacherVO teacherVO){
        teacherMapper.teacherSubjectInsert(teacherVO);
    }

    /**
     * Teacher 사용 여부 수정 (배열 처리 포함)
     */
    public void teacherIsUseUpdate(TeacherVO teacherVO){
        String[] DEL_ARR = teacherVO.getDelArr();
        if(DEL_ARR != null && DEL_ARR.length > 0){
            for(int i=0; i<DEL_ARR.length; i++){
                TeacherVO paramVO = new TeacherVO();
                paramVO.setUserId(DEL_ARR[i]);
                teacherMapper.teacherIsUseUpdate(paramVO);
            }
        }
    }

    /**
     * Teacher 삭제
     */
    public void teacherDelete(TeacherVO teacherVO){
        teacherMapper.teacherDelete(teacherVO);
    }

    /**
     * Teacher Category 삭제
     */
    public void teacherCategoryDelete(TeacherVO teacherVO){
        teacherMapper.teacherCategoryDelete(teacherVO);
    }

    /**
     * Teacher Subject 삭제
     */
    public void teacherSubjectDelete(TeacherVO teacherVO){
        teacherMapper.teacherSubjectDelete(teacherVO);
    }

    /**
     * Teacher 순서 수정 (배열 처리 포함)
     */
    public void teacherSeqUpdate(TeacherVO teacherVO){
        String[] NUM = teacherVO.getNum();
        String[] SEQARR = teacherVO.getSeq();
        String[] PROFESSOR_USER_IDARR = teacherVO.getProfessorUserId();
        String[] SUBJECT_CD = teacherVO.getSubjectCd();

        if(SEQARR != null && SEQARR.length > 0){
            for(int i=0; i<SEQARR.length; i++){
                TeacherVO paramVO = new TeacherVO();
                paramVO.setNumSingle(NUM[i]);
                paramVO.setSeqSingle(SEQARR[i]);
                paramVO.setUserId(PROFESSOR_USER_IDARR[i]);
                paramVO.setSubjectCdSingle(SUBJECT_CD[i]);
                paramVO.setOnoffdiv(teacherVO.getOnoffdiv());
                paramVO.setSearchCategory(teacherVO.getSearchCategory());

                teacherMapper.teacherSeqUpdate(paramVO);
            }
        }
    }

    /**
     * Teacher Book Log 조회
     */
    public List<HashMap<String, String>> teacherBookLog(TeacherVO teacherVO){
        return teacherMapper.teacherBookLog(teacherVO);
    }

    /**
     * Teacher Main Category 등록
     */
    public void teacherMain_Category_Insert(TeacherVO teacherVO){
        teacherMapper.teacherMain_Category_Insert(teacherVO);
    }

    /**
     * Teacher Main Category 삭제
     */
    public void teacherMain_Category_Delete(TeacherVO teacherVO){
        teacherMapper.teacherMain_Category_Delete(teacherVO);
    }

    /**
     * Teacher Intro Category 등록
     */
    public void teacherIntro_Category_Insert(TeacherVO teacherVO){
        teacherMapper.teacherIntro_Category_Insert(teacherVO);
    }

    /**
     * Teacher Intro F Category 등록
     */
    public void teacherIntro_F_Category_Insert(TeacherVO teacherVO){
        teacherMapper.teacherIntro_F_Category_Insert(teacherVO);
    }

    /**
     * Teacher Intro Category 삭제
     */
    public void teacherIntro_Category_Delete(TeacherVO teacherVO){
        teacherMapper.teacherIntro_Category_Delete(teacherVO);
    }

    /**
     * Teacher Intro F Category 삭제
     */
    public void teacherIntro_F_Category_Delete(TeacherVO teacherVO){
        teacherMapper.teacherIntro_F_Category_Delete(teacherVO);
    }

    /**
     * Teacher Main 리스트 조회
     */
    public List<HashMap<String, String>> teacherMainList(TeacherVO teacherVO){
        return teacherMapper.teacherMainList(teacherVO);
    }

    /**
     * Teacher Main 리스트 개수 조회
     */
    public int teacherMainListCount(TeacherVO teacherVO){
        return teacherMapper.teacherMainListCount(teacherVO);
    }

    /**
     * Teacher 검색 리스트 조회
     */
    public List<HashMap<String, String>> findTeacherList(TeacherVO teacherVO){
        return teacherMapper.findTeacherList(teacherVO);
    }

    /**
     * Teacher Main Category Subject 조회
     */
    public int teacherMain_Category_Subject(TeacherVO teacherVO){
        return teacherMapper.teacherMain_Category_Subject(teacherVO);
    }

    /**
     * Teacher Intro Category Subject 조회
     */
    public int teacherIntro_Category_Subject(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_Category_Subject(teacherVO);
    }

    /**
     * Teacher Intro F Category Subject 조회
     */
    public int teacherIntro_F_Category_Subject(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_F_Category_Subject(teacherVO);
    }

    /**
     * Teacher Intro 리스트 조회
     */
    public List<HashMap<String, String>> teacherIntroList(TeacherVO teacherVO){
        return teacherMapper.teacherIntroList(teacherVO);
    }

    /**
     * Teacher Intro 리스트 개수 조회
     */
    public int teacherIntroListCount(TeacherVO teacherVO){
        return teacherMapper.teacherIntroListCount(teacherVO);
    }

    /**
     * Teacher Intro Off 리스트 조회
     */
    public List<HashMap<String, String>> teacherIntro_offList(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_offList(teacherVO);
    }

    /**
     * Teacher Intro Off 리스트 개수 조회
     */
    public int teacherIntro_offListCount(TeacherVO teacherVO){
        return teacherMapper.teacherIntro_offListCount(teacherVO);
    }
}