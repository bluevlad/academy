package com.academy.mocktest.stats.total.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academy.mapper.TotalStatsMapper;

@Service
public class TotalStatsService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final TotalStatsMapper totalStatsMapper;

    @Autowired
    public TotalStatsService(TotalStatsMapper totalStatsMapper) {
        this.totalStatsMapper = totalStatsMapper;
    }

    public List<TotalStatsVO> getTotalStatsList(TotalStatsVO vo) { return totalStatsMapper.selectTotalStatsList(vo); }
    public int getTotalStatsListCount(TotalStatsVO vo) { return totalStatsMapper.selectTotalStatsListCount(vo); }
    public TotalStatsVO getTotalStatsDetail(TotalStatsVO vo) { return totalStatsMapper.selectTotalStatsDetail(vo); }
    public List<TotalStatsVO> getTotalSubjectStats(TotalStatsVO vo) { return totalStatsMapper.selectTotalSubjectStats(vo); }
    public TotalStatsVO getOffererCnt(TotalStatsVO vo) { return totalStatsMapper.selectOffererCnt(vo); }
}
