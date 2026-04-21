package com.academy.banner;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.banner.service.BannerItemVO;
import com.academy.banner.service.BannerService;
import com.academy.banner.service.BannerVO;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 배너 관리 API Controller
 * 
 * @author system
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            배너 관리 신규 생성
 *      </pre>
 */
@Tag(name = "Banner", description = "배너 관리 API")
@RestController
@RequestMapping("/api/banner")
public class BannerApi extends CORSFilter {

    private final BannerService bannerService;

    public BannerApi(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    // =====================================================
    // 배너 마스터 (TB_BANNER) API
    // =====================================================

    /**
     * 배너 목록 조회
     */
    @Operation(summary = "배너 목록 조회", description = "페이징 처리된 배너 목록을 조회합니다.")
    @GetMapping(value = "/getBannerList")
    public JSONObject getBannerList(@ModelAttribute("BannerVO") BannerVO bannerVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(bannerVO.getPageUnit());
        paginationInfo.setPageSize(bannerVO.getPageSize());

        bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
        bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("bannerList", bannerService.selectBannerList(bannerVO));

        int totCnt = bannerService.selectBannerListCount(bannerVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 상세 조회
     */
    @Operation(summary = "배너 상세 조회", description = "배너 상세 정보를 조회합니다.")
    @GetMapping(value = "/getBannerDetail")
    public JSONObject getBannerDetail(@ModelAttribute("BannerVO") BannerVO bannerVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("bannerDetail", bannerService.selectBannerDetail(bannerVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 등록
     */
    @Operation(summary = "배너 등록", description = "새로운 배너를 등록합니다.")
    @PostMapping(value = "/insertBanner")
    public JSONObject insertBanner(@ModelAttribute("BannerVO") BannerVO bannerVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            bannerService.insertBanner(bannerVO);

            // 배너 아이템을 개수만큼 생성
            int bannerCount = bannerVO.getBannerCount();
            int startNum = bannerVO.getStartNum();
            int endNum = bannerVO.getEndNum();

            if (bannerCount > 0) {
                for (int i = startNum; i <= endNum; i++) {
                    BannerItemVO itemVO = new BannerItemVO();
                    itemVO.setBannerCd(bannerVO.getBannerCd());
                    itemVO.setBannerNum(i);
                    itemVO.setUserId("");
                    itemVO.setBannerFlag("N");
                    itemVO.setRegId(bannerVO.getRegId());
                    bannerService.insertBannerItem(itemVO);
                }
            }

            jsonObject.put("retMsg", "OK");
            jsonObject.put("bannerCd", bannerVO.getBannerCd());
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 수정
     */
    @Operation(summary = "배너 수정", description = "배너 정보를 수정합니다.")
    @PostMapping(value = "/updateBanner")
    public JSONObject updateBanner(@ModelAttribute("BannerVO") BannerVO bannerVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        String msgStr = "";

        try {
            int yCnt = bannerService.selectBannerItemYCount(bannerVO);
            if (yCnt > 0) {
                // 사용중인 아이템이 있으므로 기본 정보만 수정
                bannerService.updateBanner2(bannerVO);
                msgStr = "사용중인 아이템이 있어 기본 정보만 변경되었습니다.";
            } else {
                // 배너 정보 수정 후 아이템 삭제 후 새로 생성
                bannerService.updateBanner(bannerVO);
                bannerService.deleteBannerItemByBannerCd(bannerVO);

                // 배너 아이템을 개수만큼 생성
                int bannerCount = bannerVO.getBannerCount();
                int startNum = bannerVO.getStartNum();
                int endNum = bannerVO.getEndNum();

                if (bannerCount > 0) {
                    for (int i = startNum; i <= endNum; i++) {
                        BannerItemVO itemVO = new BannerItemVO();
                        itemVO.setBannerCd(bannerVO.getBannerCd());
                        itemVO.setBannerNum(i);
                        itemVO.setUserId("");
                        itemVO.setBannerFlag("N");
                        itemVO.setRegId(bannerVO.getUpdId());
                        bannerService.insertBannerItem(itemVO);
                    }
                }
                msgStr = "배너 정보가 변경되었습니다.";
            }

            jsonObject.put("retMsg", "OK");
            jsonObject.put("msg", msgStr);
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 삭제
     */
    @Operation(summary = "배너 삭제", description = "배너를 삭제합니다. (연결된 아이템도 함께 삭제)")
    @PostMapping(value = "/deleteBanner")
    public JSONObject deleteBanner(@ModelAttribute("BannerVO") BannerVO bannerVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        String msgStr = "";

        try {
            if (bannerVO.getBannerCd() == null || "".equals(bannerVO.getBannerCd())) {
                msgStr = "삭제할 배너 코드가 잘못되어 삭제할 수 없습니다.";
                jsonObject.put("retMsg", "FAIL");
            } else {
                int yCnt = bannerService.selectBannerItemYCount(bannerVO);
                if (yCnt > 0) {
                    msgStr = "삭제할 배너에 사용중인 아이템이 있어 삭제할 수 없습니다.";
                    jsonObject.put("retMsg", "FAIL");
                } else {
                    bannerService.deleteBannerAll(bannerVO);
                    msgStr = "배너 정보가 정상적으로 삭제되었습니다.";
                    jsonObject.put("retMsg", "OK");
                }
            }
            jsonObject.put("msg", msgStr);
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 배너 아이템 (TB_BANNER_ITEM) API
    // =====================================================

    /**
     * 배너 아이템 목록 조회
     */
    @Operation(summary = "배너 아이템 목록 조회", description = "페이징 처리된 배너 아이템 목록을 조회합니다.")
    @GetMapping(value = "/getBannerItemList")
    public JSONObject getBannerItemList(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(bannerItemVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(bannerItemVO.getPageUnit());
        paginationInfo.setPageSize(bannerItemVO.getPageSize());

        bannerItemVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        bannerItemVO.setLastIndex(paginationInfo.getLastRecordIndex());
        bannerItemVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 부모 배너 정보도 함께 조회
        BannerVO parentBannerVO = new BannerVO();
        parentBannerVO.setBannerCd(bannerItemVO.getBannerCd());
        jsonObject.put("parentBanner", bannerService.selectBannerDetail(parentBannerVO));

        jsonObject.put("bannerItemList", bannerService.selectBannerItemList(bannerItemVO));

        int totCnt = bannerService.selectBannerItemListCount(bannerItemVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 아이템 상세 조회
     */
    @Operation(summary = "배너 아이템 상세 조회", description = "배너 아이템 상세 정보를 조회합니다.")
    @GetMapping(value = "/getBannerItemDetail")
    public JSONObject getBannerItemDetail(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("bannerItemDetail", bannerService.selectBannerItemDetail(bannerItemVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 아이템 등록
     */
    @Operation(summary = "배너 아이템 등록", description = "새로운 배너 아이템을 등록합니다.")
    @PostMapping(value = "/insertBannerItem")
    public JSONObject insertBannerItem(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            bannerService.insertBannerItem(bannerItemVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 아이템 수정
     */
    @Operation(summary = "배너 아이템 수정", description = "배너 아이템 정보를 수정합니다.")
    @PostMapping(value = "/updateBannerItem")
    public JSONObject updateBannerItem(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            bannerService.updateBannerItem(bannerItemVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 아이템 플래그 수정
     */
    @Operation(summary = "배너 아이템 플래그 수정", description = "배너 아이템 사용여부를 수정합니다.")
    @PostMapping(value = "/updateBannerItemFlag")
    public JSONObject updateBannerItemFlag(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            bannerService.updateBannerItemFlag(bannerItemVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 아이템 초기화
     */
    @Operation(summary = "배너 아이템 초기화", description = "배너 아이템 정보를 초기화합니다.")
    @PostMapping(value = "/updateBannerItemReset")
    public JSONObject updateBannerItemReset(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO)
            throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            bannerService.updateBannerItemReset(bannerItemVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 배너 아이템 삭제
     */
    @Operation(summary = "배너 아이템 삭제", description = "배너 아이템을 삭제합니다.")
    @PostMapping(value = "/deleteBannerItem")
    public JSONObject deleteBannerItem(@ModelAttribute("BannerItemVO") BannerItemVO bannerItemVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            bannerService.deleteBannerItem(bannerItemVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }
}
