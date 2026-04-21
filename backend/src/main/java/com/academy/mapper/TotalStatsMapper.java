package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.mocktest.stats.total.service.TotalStatsVO;

@Mapper
public interface TotalStatsMapper {
    List<TotalStatsVO> selectTotalStatsList(TotalStatsVO vo);
    int selectTotalStatsListCount(TotalStatsVO vo);
    TotalStatsVO selectTotalStatsDetail(TotalStatsVO vo);
    List<TotalStatsVO> selectTotalSubjectStats(TotalStatsVO vo);
    TotalStatsVO selectOffererCnt(TotalStatsVO vo);
}
