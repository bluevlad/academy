package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.mocktest.mouigosa.exam.service.MouiExamVO;

@Mapper
public interface MouiExamMapper {
    List<MouiExamVO> selectMouiExamList(MouiExamVO vo);
    int selectMouiExamListCount(MouiExamVO vo);
    MouiExamVO selectMouiExamDetail(MouiExamVO vo);
    int insertMouiExam(MouiExamVO vo);
    int updateMouiExam(MouiExamVO vo);
    int deleteMouiExam(MouiExamVO vo);
    int updateExamStatus(MouiExamVO vo);
    List<MouiExamVO> selectExamYearList(MouiExamVO vo);
}
