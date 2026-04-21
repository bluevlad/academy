package com.academy.stat.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academy.mapper.SalesStatMapper;

@Service
public class SalesStatService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final SalesStatMapper salesStatMapper;

    @Autowired
    public SalesStatService(SalesStatMapper salesStatMapper) {
        this.salesStatMapper = salesStatMapper;
    }

    public List<SalesStatVO> getTeacherList(SalesStatVO vo) { return salesStatMapper.selectTeacherList(vo); }
    public List<SalesStatVO> getTeacherSubjectList(SalesStatVO vo) { return salesStatMapper.selectTeacherSubjectList(vo); }
    public SalesStatVO getTeacherOne(SalesStatVO vo) { return salesStatMapper.selectTeacherOne(vo); }
    public List<SalesStatVO> getTeacherSalesStatList(SalesStatVO vo) { return salesStatMapper.selectTeacherSalesStatList(vo); }
    public SalesStatVO getUserBuyStat(SalesStatVO vo) { return salesStatMapper.selectUserBuyStat(vo); }
    public List<SalesStatVO> getSearchKeywordList(SalesStatVO vo) { return salesStatMapper.selectSearchKeywordList(vo); }
    public int getSearchKeywordCount(SalesStatVO vo) { return salesStatMapper.selectSearchKeywordCount(vo); }
    public int getSearchKeywordSum(SalesStatVO vo) { return salesStatMapper.selectSearchKeywordSum(vo); }
}
