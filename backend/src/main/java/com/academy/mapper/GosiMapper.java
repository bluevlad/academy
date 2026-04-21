package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.gosi.service.GosiVO;

/**
 * 고시 관리 Mapper
 */
@Mapper
public interface GosiMapper {

    // === 고시 마스터 ===
    List<GosiVO> selectGosiList(GosiVO vo);

    // === 샘플 사용자 관리 ===
    List<GosiVO> selectSampleUserList(GosiVO vo);
    int selectSampleUserListCount(GosiVO vo);
    GosiVO selectSampleUserDetail(GosiVO vo);
    int insertSampleUser(GosiVO vo);
    int updateSampleUser(GosiVO vo);
    int deleteSampleUser(GosiVO vo);

    // === 고시 지역 마스터 ===
    List<GosiVO> selectGosiAreaMstList(GosiVO vo);
    GosiVO selectGosiAreaMstDetail(GosiVO vo);
    int updateGosiAreaMst(GosiVO vo);

    // === VOD 관리 ===
    List<GosiVO> selectVodSubjectList(GosiVO vo);
    GosiVO selectVodDetail(GosiVO vo);
    int updateGosiVod(GosiVO vo);

    // === 고시 통계 ===
    List<GosiVO> selectGosiStatMstList(GosiVO vo);
    void callMakeGosiResult(GosiVO vo);
    void callMakeGosiStandard(GosiVO vo);
    void callMakeGosiStatMst(GosiVO vo);
    void callMakeGosiAdjustMst(GosiVO vo);

    // === 고시 과목 ===
    List<GosiVO> selectGosiSubjectList(GosiVO vo);

    // === 이벤트 결과 ===
    List<GosiVO> selectEventResultList(GosiVO vo);
    int selectEventResultListCount(GosiVO vo);
}
