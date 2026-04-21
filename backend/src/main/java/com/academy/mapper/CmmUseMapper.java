package com.academy.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.academy.common.ComDefaultCodeVO;
import com.academy.common.service.CmmnDetailCode;

/**
 * 공통코드 매퍼 인터페이스
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 */
@Mapper
public interface CmmUseMapper {

    /**
     * 공통코드를 조회한다.
     *
     * @param vo ComDefaultCodeVO
     * @return List(코드)
     * @throws Exception
     */
    List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception;

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * @param searchMap Map<String, String>
     * @return List(코드)
     * @throws Exception
     */
    List<HashMap<String, String>> selectCmmCodeMap(Map<String, String> searchMap) throws Exception;

    /**
     * 공통코드를 조회한다. (숫자 정렬)
     * Map을 이용하여 정보를 조회한다
     * @param searchMap Map<String, String>
     * @return List(코드)
     * @throws Exception
     */
    List<HashMap<String, String>> selectCmmCodeMapSortNum(Map<String, String> searchMap) throws Exception;

    /**
     * 공통코드를 조회한다. (문자열 정렬)
     * Map을 이용하여 정보를 조회한다
     * @param searchMap Map<String, String>
     * @return List(코드)
     * @throws Exception
     */
    List<HashMap<String, String>> selectCmmCodeMapSortStr(Map<String, String> searchMap) throws Exception;

    /**
     * 공통코드를 조회한다. (다중 조건 - List)
     * Map을 이용하여 정보를 조회한다
     * @param searchList Map<String, List<String>>
     * @return List(코드)
     * @throws Exception
     */
    List<HashMap<String, String>> selectCmmCdMultiCondWthList(Map<String, List<String>> searchList) throws Exception;

    /**
     * 공통코드를 조회한다. (다중 조건 - Array)
     * Map을 이용하여 정보를 조회한다
     * @param searchArray Map<String, String[]>
     * @return List(코드)
     * @throws Exception
     */
    List<HashMap<String, String>> selectCmmCdMultiCondWthArray(Map<String, String[]> searchArray) throws Exception;

    /**
     * 대학정보를 코드형태로 리턴한다.
     *
     * @param vo ComDefaultCodeVO
     * @return 조직정보 List
     * @throws Exception
     */
    List<CmmnDetailCode> selectUnivList(ComDefaultCodeVO vo) throws Exception;

    /**
     * 대학정보를 코드형태로 리턴한다.
     *
     * @param searchMap Map<String, String>
     * @return 조직정보 List
     * @throws Exception
     */
    List<HashMap<String, String>> selectUnivListMap(Map<String, String> searchMap) throws Exception;

    /**
     * 학과정보를 코드형태로 리턴한다.
     *
     * @param vo ComDefaultCodeVO
     * @return 조직정보 List
     * @throws Exception
     */
    List<CmmnDetailCode> selectSubjctList(ComDefaultCodeVO vo) throws Exception;

    /**
     * 학과정보를 코드형태로 리턴한다.
     *
     * @param searchMap Map<String, String>
     * @return 조직정보 List
     * @throws Exception
     */
    List<HashMap<String, String>> selectSubjctListMap(Map<String, String> searchMap) throws Exception;

    /**
     * 코드번호로 공통코드를 조회한다.
     *
     * @param searchMap Map<String, String>
     * @return HashMap
     * @throws Exception
     */
    HashMap<String, String> selectCmmCodeByNo(Map<String, String> searchMap) throws Exception;

}