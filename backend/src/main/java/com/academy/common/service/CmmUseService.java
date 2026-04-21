package com.academy.common.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.common.ComDefaultCodeVO;
import com.academy.mapper.CmmUseMapper;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스 클래스
 * 
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.11  Y.K.KIM          최초 생성
 *
 *      </pre>
 */
@Service
public class CmmUseService implements Serializable {

    private final CmmUseMapper cmmUseMapper;

    @Autowired
    public CmmUseService(CmmUseMapper cmmUseMapper) {
        this.cmmUseMapper = cmmUseMapper;
    }

    /**
     * 공통코드를 조회한다.
     *
     * @param vo ComDefaultCodeVO
     * @return List(코드)
     * @throws Exception
     */
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception {
        return cmmUseMapper.selectCmmCodeDetail(vo);
    }

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * 
     * @param searchMap Map<String, String>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMap(Map<String, String> searchMap) throws Exception {
        return cmmUseMapper.selectCmmCodeMap(searchMap);
    }

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * 
     * @param searchMap Map<String, String>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortNum(Map<String, String> searchMap) throws Exception {
        return cmmUseMapper.selectCmmCodeMapSortNum(searchMap);
    }

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * 
     * @param searchMap Map<String, String>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortStr(Map<String, String> searchMap) throws Exception {
        return cmmUseMapper.selectCmmCodeMapSortStr(searchMap);
    }

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * 
     * @param searchList Map<String, List<String>>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthList(Map<String, List<String>> searchList)
            throws Exception {
        return cmmUseMapper.selectCmmCdMultiCondWthList(searchList);
    }

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * 
     * @param searchArray Map<String, String[]>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthArray(Map<String, String[]> searchArray)
            throws Exception {
        return cmmUseMapper.selectCmmCdMultiCondWthArray(searchArray);
    }

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
     *
     * @param voList
     * @return Map(코드)
     * @throws Exception
     */
    public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<?> voList) throws Exception {
        Map<String, List<CmmnDetailCode>> map = new HashMap<>();

        for (Object obj : voList) {
            if (obj instanceof ComDefaultCodeVO) {
                ComDefaultCodeVO vo = (ComDefaultCodeVO) obj;
                List<CmmnDetailCode> list = cmmUseMapper.selectCmmCodeDetail(vo);
                map.put(vo.getSYS_CD(), list);
            }
        }

        return map;
    }

    /**
     * 대학정보를 코드형태로 리턴한다.
     *
     * @param ComDefaultVO vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectUnivList(ComDefaultCodeVO vo) throws Exception {
        return cmmUseMapper.selectUnivList(vo);
    }

    /**
     * 대학정보를 코드형태로 리턴한다.
     *
     * @param ComDefaultVO vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<HashMap<String, String>> selectUnivListMap(Map<String, String> searchMap) throws Exception {
        return cmmUseMapper.selectUnivListMap(searchMap);
    }

    /**
     * 학과정보를 코드형태로 리턴한다.
     *
     * @param ComDefaultVO vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectSubjctList(ComDefaultCodeVO vo) throws Exception {
        return cmmUseMapper.selectSubjctList(vo);
    }

    /**
     * 학과정보를 코드형태로 리턴한다.
     *
     * @param ComDefaultVO vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<HashMap<String, String>> selectSubjctListMap(Map<String, String> searchMap) throws Exception {
        return cmmUseMapper.selectSubjctListMap(searchMap);
    }

    /**
     * 코드번호로 공통코드를 조회한다.
     *
     * @param searchMap Map<String, String>
     * @return HashMap
     * @throws Exception
     */
    public HashMap<String, String> selectCmmCodeByNo(Map<String, String> searchMap) throws Exception {
        return cmmUseMapper.selectCmmCodeByNo(searchMap);
    }

}