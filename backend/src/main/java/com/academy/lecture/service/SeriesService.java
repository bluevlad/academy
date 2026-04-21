package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.SeriesMapper;
import com.academy.mapper.CategorySeriesMapper;

/**
 * Series Service
 * 시리즈 관리 서비스 (직접 구현)
 */
@Service(value="seriesService")
public class SeriesService {

    @Autowired
    private SeriesMapper seriesMapper;

    @Autowired
    private CategorySeriesMapper categorySeriesMapper;

    /**
     * Series 목록 조회
     */
    public List<HashMap<String, String>> seriesList(SeriesVO seriesVO){
        return seriesMapper.seriesList(seriesVO);
    }

    /**
     * Series 목록 개수 조회
     */
    public int seriesListCount(SeriesVO seriesVO){
        return seriesMapper.seriesListCount(seriesVO);
    }

    /**
     * Series 등록
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void seriesInsert(SeriesVO seriesVO){
        seriesMapper.seriesInsert(seriesVO);

        String[] catCds = seriesVO.getCatCds();
        if(null != catCds && catCds.length > 0) {
            for(int i=0; i<catCds.length; i++) {
                seriesVO.setCatCd(catCds[i]);
                seriesVO.setOrdr(i+1);
                categorySeriesMapper.catSeriesInsert(seriesVO);
            }
        }
    }

    /**
     * Series 상세 조회
     */
    public List<HashMap<String, String>> seriesView(SeriesVO seriesVO){
        return seriesMapper.seriesView(seriesVO);
    }

    /**
     * Series 수정
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void seriesUpdate(SeriesVO seriesVO){
        seriesMapper.seriesUpdate(seriesVO);

        categorySeriesMapper.catSeriesDeleteWthSrsCd(seriesVO);

        String[] catCds = seriesVO.getCatCds();
        if(null != catCds && catCds.length > 0) {
            for(int i=0; i<catCds.length; i++) {
                seriesVO.setCatCd(catCds[i]);
                seriesVO.setOrdr(i+1);
                categorySeriesMapper.catSeriesInsert(seriesVO);
            }
        }
    }

    /**
     * Series 삭제
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void seriesDelete(SeriesVO seriesVO){
        String[] srsCds = seriesVO.getSrsCds();
        if(srsCds != null) {
            for(int i=0; i<srsCds.length; i++) {
                seriesVO.setSrsCd(srsCds[i]);
                seriesMapper.seriesDelete(seriesVO);
                categorySeriesMapper.catSeriesDeleteWthSrsCd(seriesVO);
            }
        }
    }

    /**
     * Series 코드 중복 체크
     */
    public int seriesCheck(SeriesVO seriesVO){
        return seriesMapper.seriesCheck(seriesVO);
    }
}