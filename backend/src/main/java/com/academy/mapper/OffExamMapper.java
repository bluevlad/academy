package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.mocktest.offExamReg.exam.service.OffExamVO;

@Mapper
public interface OffExamMapper {
    List<OffExamVO> selectOffExamList(OffExamVO vo);
    int selectOffExamListCount(OffExamVO vo);
    OffExamVO selectOffExamDetail(OffExamVO vo);
    int insertOffExam(OffExamVO vo);
    int updateOffExam(OffExamVO vo);
    int deleteOffExam(OffExamVO vo);
    int updateOffExamStatus(OffExamVO vo);
}
