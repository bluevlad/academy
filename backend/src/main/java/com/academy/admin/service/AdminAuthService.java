package com.academy.admin.service;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.AdminAuthMapper;

/**
 * 권한 관리 Service 클래스
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
public class AdminAuthService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final AdminAuthMapper adminAuthMapper;

    public AdminAuthService(AdminAuthMapper adminAuthMapper) {
        this.adminAuthMapper = adminAuthMapper;
    }

    /** 권한 리스트 조회 */
    public ArrayList<JSONObject> selectAuthList(AdminSiteVO vo) {
        return adminAuthMapper.selectAuthList(vo);
    }

    /** 권한 리스트 총 건수 */
    public int selectAuthListCount(AdminSiteVO vo) {
        return adminAuthMapper.selectAuthListCount(vo);
    }

    /** 권한 등록 */
    public void insertAuth(AdminSiteVO vo) {
        adminAuthMapper.insertAuth(vo);
    }

    /** 권한 상세 조회 */
    public JSONObject selectAuthDetail(AdminSiteVO vo) {
        return adminAuthMapper.selectAuthDetail(vo);
    }

    /** 권한 수정 */
    public void updateAuth(AdminSiteVO vo) {
        adminAuthMapper.updateAuth(vo);
    }

    /** 권한별 메뉴 사이트ID 수정 */
    public void updateAuthMenuSiteId(AdminSiteVO vo) {
        adminAuthMapper.updateAuthMenuSiteId(vo);
    }

    /** 권한 개별 삭제 */
    public void deleteAuth(AdminSiteVO vo) {
        adminAuthMapper.deleteAuth(vo);
    }

    /** 권한 일괄 삭제 */
    public void deleteAuthBatch(AdminSiteVO vo) {
        adminAuthMapper.deleteAuthBatch(vo);
    }

    /** 온라인 메뉴 리스트 조회 */
    public ArrayList<JSONObject> selectOnlineMenuList(AdminSiteVO vo) {
        return adminAuthMapper.selectOnlineMenuList(vo);
    }

    /** 오프라인 메뉴 리스트 조회 */
    public ArrayList<JSONObject> selectOfflineMenuList(AdminSiteVO vo) {
        return adminAuthMapper.selectOfflineMenuList(vo);
    }

    /** 모의고사 메뉴 리스트 조회 */
    public ArrayList<JSONObject> selectTestMenuList(AdminSiteVO vo) {
        return adminAuthMapper.selectTestMenuList(vo);
    }

    /** 권한별 메뉴 삭제 */
    public void deleteAuthMenu(AdminSiteVO vo) {
        adminAuthMapper.deleteAuthMenu(vo);
    }

    /** 권한별 메뉴 등록 */
    public void insertAuthMenu(AdminSiteVO vo) {
        adminAuthMapper.insertAuthMenu(vo);
    }

    /** 권한별 등록된 메뉴 리스트 조회 */
    public ArrayList<JSONObject> selectAuthMenuList(AdminSiteVO vo) {
        return adminAuthMapper.selectAuthMenuList(vo);
    }

    /** 권한별 등록된 메뉴 개별 삭제 */
    public void deleteSiteIdAuthMenu(AdminSiteVO vo) {
        adminAuthMapper.deleteSiteIdAuthMenu(vo);
    }

    /** 권한별 등록된 메뉴 일괄 삭제 */
    public void deleteSiteIdAuthMenuBatch(AdminSiteVO vo) {
        adminAuthMapper.deleteSiteIdAuthMenuBatch(vo);
    }
}
