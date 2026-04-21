package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.admin.service.AdminMenuVO;

/**
 * 관리자 메뉴 관리 데이터 접근 클래스
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
public interface AdminMenuMapper {

    /** 관리자 메뉴 트리 조회 */
    ArrayList<JSONObject> selectMenuTree();

    /** 사용자 메뉴 트리 조회 */
    ArrayList<JSONObject> selectPassMenuTree();

    /** 관리자 메뉴 상세 조회 */
    JSONObject selectMenuDetail(AdminMenuVO vo);

    /** 사용자 메뉴 상세 조회 */
    JSONObject selectPassMenuDetail(AdminMenuVO vo);

    /** 관리자 메뉴 등록 */
    int insertMenu(AdminMenuVO vo);

    /** 사용자 메뉴 등록 */
    int insertPassMenu(AdminMenuVO vo);

    /** 관리자 메뉴 수정 */
    void updateMenu(AdminMenuVO vo);

    /** 사용자 메뉴 수정 */
    void updatePassMenu(AdminMenuVO vo);

    /** 관리자 메뉴 삭제 */
    int deleteMenu(AdminMenuVO vo);

    /** 사용자 메뉴 삭제 */
    int deletePassMenu(AdminMenuVO vo);

    /** 메뉴 ID 중복 체크 */
    int checkMenuIdDuplicate(AdminMenuVO vo);

    /** 관리자 메뉴 MAX ID 조회 */
    JSONObject selectMaxMenuId(AdminMenuVO vo);

    /** 사용자 메뉴 MAX ID 조회 */
    JSONObject selectPassMaxMenuId(AdminMenuVO vo);
}
