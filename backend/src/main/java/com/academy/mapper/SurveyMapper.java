package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.survey.service.SurveyVO;

@Mapper
public interface SurveyMapper {
    // 설문 문항 (Bank)
    List<SurveyVO> selectBankList(SurveyVO vo);
    int selectBankListCount(SurveyVO vo);
    SurveyVO selectBankDetail(SurveyVO vo);
    int insertBank(SurveyVO vo);
    int updateBank(SurveyVO vo);
    int deleteBank(SurveyVO vo);

    // 설문 세트 (Set)
    List<SurveyVO> selectSetList(SurveyVO vo);
    int selectSetListCount(SurveyVO vo);
    SurveyVO selectSetDetail(SurveyVO vo);
    int insertSet(SurveyVO vo);
    int updateSet(SurveyVO vo);
    int deleteSet(SurveyVO vo);

    // 설문 세트 항목
    List<SurveyVO> selectSetItemList(SurveyVO vo);
    int insertSetItem(SurveyVO vo);
    int updateSetItem(SurveyVO vo);
    int deleteSetItem(SurveyVO vo);

    // 설문 (Survey)
    List<SurveyVO> selectSurveyList(SurveyVO vo);
    int selectSurveyListCount(SurveyVO vo);
    SurveyVO selectSurveyDetail(SurveyVO vo);
    int insertSurvey(SurveyVO vo);
    int updateSurvey(SurveyVO vo);
    int deleteSurvey(SurveyVO vo);

    // 설문 결과
    List<SurveyVO> selectSurveyResultList(SurveyVO vo);
    List<SurveyVO> selectAnswerList(SurveyVO vo);
}
