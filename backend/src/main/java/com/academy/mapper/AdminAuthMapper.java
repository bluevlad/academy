package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.admin.service.AdminSiteVO;

/**
 * 권한 관리 데이터 접근 클래스
 * 
 * @author system
 * @since 2025.12.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.10         system          최초 생성
 *      </pre>
 */
@Mapper
public interface AdminAuthMapper {

    /** 권한 리스트 조회 */
    ArrayList<JSONObject> selectAuthList(AdminSiteVO vo);

    /** 권한 리스트 총 건수 */
    int selectAuthListCount(AdminSiteVO vo);

    /** 권한 등록 */
    void insertAuth(AdminSiteVO vo);

    /** 권한 상세 조회 */
    JSONObject selectAuthDetail(AdminSiteVO vo);

    /** 권한 수정 */
    void updateAuth(AdminSiteVO vo);

    /** 권한별 메뉴 사이트ID 수정 */
    void updateAuthMenuSiteId(AdminSiteVO vo);

    /** 권한 개별 삭제 */
    void deleteAuth(AdminSiteVO vo);

    /** 권한 일괄 삭제 */
    void deleteAuthBatch(AdminSiteVO vo);

    /** 온라인 메뉴 리스트 조회 */
    ArrayList<JSONObject> selectOnlineMenuList(AdminSiteVO vo);

    /** 오프라인 메뉴 리스트 조회 */
    ArrayList<JSONObject> selectOfflineMenuList(AdminSiteVO vo);

    /** 모의고사 메뉴 리스트 조회 */
    ArrayList<JSONObject> selectTestMenuList(AdminSiteVO vo);

    /** 권한별 메뉴 삭제 */
    void deleteAuthMenu(AdminSiteVO vo);

    /** 권한별 메뉴 등록 */
    void insertAuthMenu(AdminSiteVO vo);

    /** 권한별 등록된 메뉴 리스트 조회 */
    ArrayList<JSONObject> selectAuthMenuList(AdminSiteVO vo);

    /** 권한별 등록된 메뉴 개별 삭제 */
    void deleteSiteIdAuthMenu(AdminSiteVO vo);

    /** 권한별 등록된 메뉴 일괄 삭제 */
    void deleteSiteIdAuthMenuBatch(AdminSiteVO vo);
}
