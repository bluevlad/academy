package com.academy.admin.service;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.AdminCodeMapper;

/**
 * 공통코드 관리 Service 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            최초 생성
 * </pre>
 */
@Service
public class AdminCodeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final AdminCodeMapper adminCodeMapper;

    public AdminCodeService(AdminCodeMapper adminCodeMapper) {
        this.adminCodeMapper = adminCodeMapper;
    }

    /** 공통코드 리스트 조회 */
    public ArrayList<JSONObject> selectCodeList(AdminCodeVO vo) {
        return adminCodeMapper.selectCodeList(vo);
    }

    /** 공통코드 리스트 총 건수 */
    public int selectCodeListCount(AdminCodeVO vo) {
        return adminCodeMapper.selectCodeListCount(vo);
    }

    /** 공통코드 등록 */
    public void insertCode(AdminCodeVO vo) {
        adminCodeMapper.insertCode(vo);
    }

    /** 공통코드 상세 조회 */
    public JSONObject selectCodeDetail(AdminCodeVO vo) {
        return adminCodeMapper.selectCodeDetail(vo);
    }

    /** 공통코드 수정 */
    public void updateCode(AdminCodeVO vo) {
        adminCodeMapper.updateCode(vo);
    }

    /** 공통코드 개별 삭제 */
    public void deleteCode(AdminCodeVO vo) {
        adminCodeMapper.deleteCode(vo);
    }

    /** 공통코드 일괄 삭제 */
    public void deleteCodeBatch(AdminCodeVO vo) {
        adminCodeMapper.deleteCodeBatch(vo);
    }

    /** 설정코드 트리 조회 */
    public ArrayList<JSONObject> selectPassCodeTree() {
        return adminCodeMapper.selectPassCodeTree();
    }

    /** 설정코드 상세 조회 */
    public JSONObject selectPassCodeDetail(AdminCodeVO vo) {
        return adminCodeMapper.selectPassCodeDetail(vo);
    }

    /** 설정코드 등록 */
    public int insertPassCode(AdminCodeVO vo) {
        return adminCodeMapper.insertPassCode(vo);
    }

    /** 설정코드 수정 */
    public void updatePassCode(AdminCodeVO vo) {
        adminCodeMapper.updatePassCode(vo);
    }

    /** 설정코드 삭제 */
    public int deletePassCode(AdminCodeVO vo) {
        return adminCodeMapper.deletePassCode(vo);
    }

    /** 설정코드 MAX ID 조회 */
    public JSONObject selectPassMaxCodeId(AdminCodeVO vo) {
        return adminCodeMapper.selectPassMaxCodeId(vo);
    }

    /** BA CONFIG 코드 리스트 조회 */
    public ArrayList<JSONObject> selectBaConfigCodeList(AdminCodeVO vo) {
        return adminCodeMapper.selectBaConfigCodeList(vo);
    }
}
