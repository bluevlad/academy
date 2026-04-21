package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.mocktest.offExamReg.registration.service.OffExamRegVO;

@Mapper
public interface OffExamRegMapper {
    List<OffExamRegVO> selectOffExamRegList(OffExamRegVO vo);
    int selectOffExamRegListCount(OffExamRegVO vo);
    OffExamRegVO selectOffExamRegDetail(OffExamRegVO vo);
    int insertOffExamReg(OffExamRegVO vo);
    int updateOffExamReg(OffExamRegVO vo);
    int deleteOffExamReg(OffExamRegVO vo);
    int updateOffExamRegStatus(OffExamRegVO vo);
    int updateOffExamRegResult(OffExamRegVO vo);
}
