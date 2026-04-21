package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.admin.service.AdminCodeVO;

/**
 * 공통코드 관리 데이터 접근 클래스
 * @author system
 * @since 2025.12.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.10         system          최초 생성
 * </pre>
 */
@Mapper
public interface AdminCodeMapper {

    /** 공통코드 리스트 조회 */
    ArrayList<JSONObject> selectCodeList(AdminCodeVO vo);

    /** 공통코드 리스트 총 건수 */
    int selectCodeListCount(AdminCodeVO vo);

    /** 공통코드 등록 */
    void insertCode(AdminCodeVO vo);

    /** 공통코드 상세 조회 */
    JSONObject selectCodeDetail(AdminCodeVO vo);

    /** 공통코드 수정 */
    void updateCode(AdminCodeVO vo);

    /** 공통코드 개별 삭제 */
    void deleteCode(AdminCodeVO vo);

    /** 공통코드 일괄 삭제 */
    void deleteCodeBatch(AdminCodeVO vo);

    /** 설정코드 트리 조회 */
    ArrayList<JSONObject> selectPassCodeTree();

    /** 설정코드 상세 조회 */
    JSONObject selectPassCodeDetail(AdminCodeVO vo);

    /** 설정코드 등록 */
    int insertPassCode(AdminCodeVO vo);

    /** 설정코드 수정 */
    void updatePassCode(AdminCodeVO vo);

    /** 설정코드 삭제 */
    int deletePassCode(AdminCodeVO vo);

    /** 설정코드 MAX ID 조회 */
    JSONObject selectPassMaxCodeId(AdminCodeVO vo);

    /** BA CONFIG 코드 리스트 조회 */
    ArrayList<JSONObject> selectBaConfigCodeList(AdminCodeVO vo);
}
