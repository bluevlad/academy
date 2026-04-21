package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.TeacherVO;

/**
 * Teacher Mapper Interface
 * lectureTeacherSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface TeacherMapper {

    /**
     * Kind 목록 조회
     */
    List<HashMap<String, String>> getKindList(TeacherVO teacherVO);

    /**
     * 과목 목록 조회
     */
    List<HashMap<String, String>> getSubjectList(TeacherVO teacherVO);

    /**
     * 강사 목록 조회
     */
    List<HashMap<String, String>> teacherList(TeacherVO teacherVO);

    /**
     * 강사 목록 카운트
     */
    int teacherListCount(TeacherVO teacherVO);

    /**
     * 강사 전체 목록 조회
     */
    List<HashMap<String, String>> teacherAllList(TeacherVO teacherVO);

    /**
     * 강사 ID 중복 체크
     */
    int teacherIdCheck(TeacherVO teacherVO);

    /**
     * 강사 등록
     */
    void teacherInsert(TeacherVO teacherVO);

    /**
     * 강사 상세 조회
     */
    List<HashMap<String, String>> teacherView(TeacherVO teacherVO);

    /**
     * 강사 수정
     */
    void teacherUpdate(TeacherVO teacherVO);

    /**
     * 강사 카테고리 등록
     */
    void teacherCategoryInsert(TeacherVO teacherVO);

    /**
     * 강사 과목 등록
     */
    void teacherSubjectInsert(TeacherVO teacherVO);

    /**
     * 강사 과목 수정
     */
    void teacherSubjectUpdate(TeacherVO teacherVO);

    /**
     * 강사 과목 개수
     */
    int teacherSubjectCount(TeacherVO teacherVO);

    /**
     * 강사 사용여부 수정
     */
    void teacherIsUseUpdate(TeacherVO teacherVO);

    /**
     * 강사 삭제
     */
    void teacherDelete(TeacherVO teacherVO);

    /**
     * 강사 카테고리 삭제
     */
    void teacherCategoryDelete(TeacherVO teacherVO);

    /**
     * 강사 과목 삭제
     */
    void teacherSubjectDelete(TeacherVO teacherVO);

    /**
     * 강사 순서 업데이트
     */
    void teacherSeqUpdate(TeacherVO teacherVO);

    /**
     * 강사 교재 로그
     */
    List<HashMap<String, String>> teacherBookLog(TeacherVO teacherVO);

    /**
     * 강사 메인 카테고리 등록
     */
    void teacherMain_Category_Insert(TeacherVO teacherVO);

    /**
     * 강사 메인 카테고리 삭제
     */
    void teacherMain_Category_Delete(TeacherVO teacherVO);

    /**
     * 강사 소개 카테고리 등록
     */
    void teacherIntro_Category_Insert(TeacherVO teacherVO);

    /**
     * 강사 소개 F 카테고리 등록
     */
    void teacherIntro_F_Category_Insert(TeacherVO teacherVO);

    /**
     * 강사 소개 카테고리 삭제
     */
    void teacherIntro_Category_Delete(TeacherVO teacherVO);

    /**
     * 강사 소개 F 카테고리 삭제
     */
    void teacherIntro_F_Category_Delete(TeacherVO teacherVO);

    /**
     * 강사 메인 목록 조회
     */
    List<HashMap<String, String>> teacherMainList(TeacherVO teacherVO);

    /**
     * 강사 메인 목록 카운트
     */
    int teacherMainListCount(TeacherVO teacherVO);

    /**
     * 강사 목록 검색
     */
    List<HashMap<String, String>> findTeacherList(TeacherVO teacherVO);

    /**
     * 강사 메인 카테고리 과목
     */
    int teacherMain_Category_Subject(TeacherVO teacherVO);

    /**
     * 강사 소개 카테고리 과목
     */
    int teacherIntro_Category_Subject(TeacherVO teacherVO);

    /**
     * 강사 소개 F 카테고리 과목
     */
    int teacherIntro_F_Category_Subject(TeacherVO teacherVO);

    /**
     * 강사 소개 목록 조회
     */
    List<HashMap<String, String>> teacherIntroList(TeacherVO teacherVO);

    /**
     * 강사 소개 목록 카운트
     */
    int teacherIntroListCount(TeacherVO teacherVO);

    /**
     * 강사 소개 OFF 목록 조회
     */
    List<HashMap<String, String>> teacherIntro_offList(TeacherVO teacherVO);

    /**
     * 강사 소개 OFF 목록 카운트
     */
    int teacherIntro_offListCount(TeacherVO teacherVO);

    /**
     * 강사 전체 목록 카운트
     */
    int teacherAllListCount(TeacherVO teacherVO);
}