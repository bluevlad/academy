package com.academy.banner.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.BannerMapper;

/**
 * 배너 관리 Service 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            배너 관리 신규 생성
 * </pre>
 */
@Service
public class BannerService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BannerMapper bannerMapper;

    public BannerService(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    // =====================================================
    // 배너 마스터 (TB_BANNER) 관련
    // =====================================================

    /**
     * 배너 목록 조회
     * @param bannerVO 검색조건
     * @return List 배너 목록
     */
    public List<JSONObject> selectBannerList(BannerVO bannerVO) {
        return bannerMapper.selectBannerList(bannerVO);
    }

    /**
     * 배너 목록 건수 조회
     * @param bannerVO 검색조건
     * @return int 배너 목록 건수
     */
    public int selectBannerListCount(BannerVO bannerVO) {
        return bannerMapper.selectBannerListCount(bannerVO);
    }

    /**
     * 배너 상세 조회
     * @param bannerVO 검색조건
     * @return JSONObject 배너 상세정보
     */
    public JSONObject selectBannerDetail(BannerVO bannerVO) {
        return bannerMapper.selectBannerDetail(bannerVO);
    }

    /**
     * 배너 아이템 사용중 수 조회
     * @param bannerVO 검색조건
     * @return int 사용중 아이템 수
     */
    public int selectBannerItemYCount(BannerVO bannerVO) {
        return bannerMapper.selectBannerItemYCount(bannerVO);
    }

    /**
     * 배너 등록
     * @param bannerVO 배너정보
     */
    public void insertBanner(BannerVO bannerVO) {
        bannerMapper.insertBanner(bannerVO);
    }

    /**
     * 배너 수정
     * @param bannerVO 배너정보
     */
    public void updateBanner(BannerVO bannerVO) {
        bannerMapper.updateBanner(bannerVO);
    }

    /**
     * 배너 수정 (아이템 수, 시작/종료번호 제외)
     * @param bannerVO 배너정보
     */
    public void updateBanner2(BannerVO bannerVO) {
        bannerMapper.updateBanner2(bannerVO);
    }

    /**
     * 배너 삭제
     * @param bannerVO 배너정보
     */
    public void deleteBanner(BannerVO bannerVO) {
        bannerMapper.deleteBanner(bannerVO);
    }

    /**
     * 배너 관련 전체 삭제 (배너 + 아이템)
     * @param bannerVO 배너정보
     */
    public void deleteBannerAll(BannerVO bannerVO) {
        // 아이템 먼저 삭제
        bannerMapper.deleteBannerItemByBannerCd(bannerVO);
        // 배너 삭제
        bannerMapper.deleteBanner(bannerVO);
    }

    // =====================================================
    // 배너 아이템 (TB_BANNER_ITEM) 관련
    // =====================================================

    /**
     * 배너 아이템 목록 조회
     * @param bannerItemVO 검색조건
     * @return List 배너 아이템 목록
     */
    public List<JSONObject> selectBannerItemList(BannerItemVO bannerItemVO) {
        return bannerMapper.selectBannerItemList(bannerItemVO);
    }

    /**
     * 배너 아이템 목록 건수 조회
     * @param bannerItemVO 검색조건
     * @return int 배너 아이템 목록 건수
     */
    public int selectBannerItemListCount(BannerItemVO bannerItemVO) {
        return bannerMapper.selectBannerItemListCount(bannerItemVO);
    }

    /**
     * 배너 아이템 상세 조회
     * @param bannerItemVO 검색조건
     * @return JSONObject 배너 아이템 상세정보
     */
    public JSONObject selectBannerItemDetail(BannerItemVO bannerItemVO) {
        return bannerMapper.selectBannerItemDetail(bannerItemVO);
    }

    /**
     * 배너 아이템 등록
     * @param bannerItemVO 배너 아이템 정보
     */
    public void insertBannerItem(BannerItemVO bannerItemVO) {
        bannerMapper.insertBannerItem(bannerItemVO);
    }

    /**
     * 배너 아이템 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    public void updateBannerItem(BannerItemVO bannerItemVO) {
        bannerMapper.updateBannerItem(bannerItemVO);
    }

    /**
     * 배너 아이템 플래그 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    public void updateBannerItemFlag(BannerItemVO bannerItemVO) {
        bannerMapper.updateBannerItemFlag(bannerItemVO);
    }

    /**
     * 배너 아이템 초기화
     * @param bannerItemVO 배너 아이템 정보
     */
    public void updateBannerItemReset(BannerItemVO bannerItemVO) {
        bannerMapper.updateBannerItemReset(bannerItemVO);
    }

    /**
     * 배너 아이템 삭제
     * @param bannerItemVO 배너 아이템 정보
     */
    public void deleteBannerItem(BannerItemVO bannerItemVO) {
        bannerMapper.deleteBannerItem(bannerItemVO);
    }

    /**
     * 배너 아이템 일괄 삭제 (부모 배너 기준)
     * @param bannerVO 배너 정보
     */
    public void deleteBannerItemByBannerCd(BannerVO bannerVO) {
        bannerMapper.deleteBannerItemByBannerCd(bannerVO);
    }
}
