package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.stat.service.SalesStatVO;

@Mapper
public interface SalesStatMapper {
    List<SalesStatVO> selectTeacherList(SalesStatVO vo);
    List<SalesStatVO> selectTeacherSubjectList(SalesStatVO vo);
    SalesStatVO selectTeacherOne(SalesStatVO vo);
    List<SalesStatVO> selectTeacherSalesStatList(SalesStatVO vo);
    SalesStatVO selectUserBuyStat(SalesStatVO vo);
    List<SalesStatVO> selectSearchKeywordList(SalesStatVO vo);
    int selectSearchKeywordCount(SalesStatVO vo);
    int selectSearchKeywordSum(SalesStatVO vo);
}
