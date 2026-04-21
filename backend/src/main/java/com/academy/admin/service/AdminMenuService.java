package com.academy.admin.service;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.AdminMenuMapper;

/**
 * 관리자 메뉴 관리 Service 클래스
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
public class AdminMenuService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final AdminMenuMapper adminMenuMapper;

    public AdminMenuService(AdminMenuMapper adminMenuMapper) {
        this.adminMenuMapper = adminMenuMapper;
    }

    /** 관리자 메뉴 트리 조회 */
    public ArrayList<JSONObject> selectMenuTree() {
        return adminMenuMapper.selectMenuTree();
    }

    /** 사용자 메뉴 트리 조회 */
    public ArrayList<JSONObject> selectPassMenuTree() {
        return adminMenuMapper.selectPassMenuTree();
    }

    /** 관리자 메뉴 상세 조회 */
    public JSONObject selectMenuDetail(AdminMenuVO vo) {
        return adminMenuMapper.selectMenuDetail(vo);
    }

    /** 사용자 메뉴 상세 조회 */
    public JSONObject selectPassMenuDetail(AdminMenuVO vo) {
        return adminMenuMapper.selectPassMenuDetail(vo);
    }

    /** 관리자 메뉴 등록 */
    public int insertMenu(AdminMenuVO vo) {
        return adminMenuMapper.insertMenu(vo);
    }

    /** 사용자 메뉴 등록 */
    public int insertPassMenu(AdminMenuVO vo) {
        return adminMenuMapper.insertPassMenu(vo);
    }

    /** 관리자 메뉴 수정 */
    public void updateMenu(AdminMenuVO vo) {
        adminMenuMapper.updateMenu(vo);
    }

    /** 사용자 메뉴 수정 */
    public void updatePassMenu(AdminMenuVO vo) {
        adminMenuMapper.updatePassMenu(vo);
    }

    /** 관리자 메뉴 삭제 */
    public int deleteMenu(AdminMenuVO vo) {
        return adminMenuMapper.deleteMenu(vo);
    }

    /** 사용자 메뉴 삭제 */
    public int deletePassMenu(AdminMenuVO vo) {
        return adminMenuMapper.deletePassMenu(vo);
    }

    /** 메뉴 ID 중복 체크 */
    public int checkMenuIdDuplicate(AdminMenuVO vo) {
        return adminMenuMapper.checkMenuIdDuplicate(vo);
    }

    /** 관리자 메뉴 MAX ID 조회 */
    public JSONObject selectMaxMenuId(AdminMenuVO vo) {
        return adminMenuMapper.selectMaxMenuId(vo);
    }

    /** 사용자 메뉴 MAX ID 조회 */
    public JSONObject selectPassMaxMenuId(AdminMenuVO vo) {
        return adminMenuMapper.selectPassMaxMenuId(vo);
    }
}
