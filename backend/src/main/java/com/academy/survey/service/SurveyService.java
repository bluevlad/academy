package com.academy.survey.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.SurveyMapper;

@Service
public class SurveyService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final SurveyMapper surveyMapper;

    @Autowired
    public SurveyService(SurveyMapper surveyMapper) {
        this.surveyMapper = surveyMapper;
    }

    // 설문 문항 (Bank)
    public List<SurveyVO> getBankList(SurveyVO vo) { return surveyMapper.selectBankList(vo); }
    public int getBankListCount(SurveyVO vo) { return surveyMapper.selectBankListCount(vo); }
    public SurveyVO getBankDetail(SurveyVO vo) { return surveyMapper.selectBankDetail(vo); }
    @Transactional
    public int insertBank(SurveyVO vo) { return surveyMapper.insertBank(vo); }
    @Transactional
    public int updateBank(SurveyVO vo) { return surveyMapper.updateBank(vo); }
    @Transactional
    public int deleteBank(SurveyVO vo) { return surveyMapper.deleteBank(vo); }

    // 설문 세트 (Set)
    public List<SurveyVO> getSetList(SurveyVO vo) { return surveyMapper.selectSetList(vo); }
    public int getSetListCount(SurveyVO vo) { return surveyMapper.selectSetListCount(vo); }
    public SurveyVO getSetDetail(SurveyVO vo) { return surveyMapper.selectSetDetail(vo); }
    @Transactional
    public int insertSet(SurveyVO vo) { return surveyMapper.insertSet(vo); }
    @Transactional
    public int updateSet(SurveyVO vo) { return surveyMapper.updateSet(vo); }
    @Transactional
    public int deleteSet(SurveyVO vo) { return surveyMapper.deleteSet(vo); }

    // 설문 세트 항목
    public List<SurveyVO> getSetItemList(SurveyVO vo) { return surveyMapper.selectSetItemList(vo); }
    @Transactional
    public int insertSetItem(SurveyVO vo) { return surveyMapper.insertSetItem(vo); }
    @Transactional
    public int updateSetItem(SurveyVO vo) { return surveyMapper.updateSetItem(vo); }
    @Transactional
    public int deleteSetItem(SurveyVO vo) { return surveyMapper.deleteSetItem(vo); }

    // 설문 (Survey)
    public List<SurveyVO> getSurveyList(SurveyVO vo) { return surveyMapper.selectSurveyList(vo); }
    public int getSurveyListCount(SurveyVO vo) { return surveyMapper.selectSurveyListCount(vo); }
    public SurveyVO getSurveyDetail(SurveyVO vo) { return surveyMapper.selectSurveyDetail(vo); }
    @Transactional
    public int insertSurvey(SurveyVO vo) { return surveyMapper.insertSurvey(vo); }
    @Transactional
    public int updateSurvey(SurveyVO vo) { return surveyMapper.updateSurvey(vo); }
    @Transactional
    public int deleteSurvey(SurveyVO vo) { return surveyMapper.deleteSurvey(vo); }

    // 설문 결과
    public List<SurveyVO> getSurveyResultList(SurveyVO vo) { return surveyMapper.selectSurveyResultList(vo); }
    public List<SurveyVO> getAnswerList(SurveyVO vo) { return surveyMapper.selectAnswerList(vo); }
}
