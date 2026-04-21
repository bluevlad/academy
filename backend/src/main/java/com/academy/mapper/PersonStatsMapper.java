package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.mocktest.stats.person.service.PersonStatsVO;

@Mapper
public interface PersonStatsMapper {
    List<PersonStatsVO> selectPersonStatsList(PersonStatsVO vo);
    int selectPersonStatsListCount(PersonStatsVO vo);
    PersonStatsVO selectPersonStatsDetail(PersonStatsVO vo);
    List<PersonStatsVO> selectPersonSubjectStats(PersonStatsVO vo);
    PersonStatsVO selectPersonTotalStats(PersonStatsVO vo);
}
