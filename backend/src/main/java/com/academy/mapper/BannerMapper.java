package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.banner.service.BannerItemVO;
import com.academy.banner.service.BannerVO;

/**
 * 배너정보에 관한 데이터 접근 클래스를 정의한다.
 * @author system
 * @since 2025.12.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.11         system          신규 생성
 * </pre>
 */
@Mapper
public interface BannerMapper {

    // =====================================================
    // 배너 마스터 (TB_BANNER) 관련
    // =====================================================

    /**
     * 배너 목록 조회
     * @param bannerVO 검색조건
     * @return List 배너 목록정보
     */
    List<JSONObject> selectBannerList(BannerVO bannerVO);

    /**
     * 배너 목록 건수 조회
     * @param bannerVO 검색조건
     * @return int 배너 목록 건수
     */
    int selectBannerListCount(BannerVO bannerVO);

    /**
     * 배너 상세 조회
     * @param bannerVO 검색조건
     * @return JSONObject 배너 상세정보
     */
    JSONObject selectBannerDetail(BannerVO bannerVO);

    /**
     * 배너 아이템 사용중 수 조회
     * @param bannerVO 검색조건
     * @return int 사용중 아이템 수
     */
    int selectBannerItemYCount(BannerVO bannerVO);

    /**
     * 배너 등록
     * @param bannerVO 배너정보
     */
    void insertBanner(BannerVO bannerVO);

    /**
     * 배너 수정
     * @param bannerVO 배너정보
     */
    void updateBanner(BannerVO bannerVO);

    /**
     * 배너 수정 (아이템 수, 시작/종료번호 제외)
     * @param bannerVO 배너정보
     */
    void updateBanner2(BannerVO bannerVO);

    /**
     * 배너 삭제
     * @param bannerVO 배너정보
     */
    void deleteBanner(BannerVO bannerVO);

    /**
     * 배너 관련 전체 삭제 (배너 + 아이템)
     * @param bannerVO 배너정보
     */
    void deleteBannerAll(BannerVO bannerVO);

    // =====================================================
    // 배너 아이템 (TB_BANNER_ITEM) 관련
    // =====================================================

    /**
     * 배너 아이템 목록 조회
     * @param bannerItemVO 검색조건
     * @return List 배너 아이템 목록정보
     */
    List<JSONObject> selectBannerItemList(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 목록 건수 조회
     * @param bannerItemVO 검색조건
     * @return int 배너 아이템 목록 건수
     */
    int selectBannerItemListCount(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 상세 조회
     * @param bannerItemVO 검색조건
     * @return JSONObject 배너 아이템 상세정보
     */
    JSONObject selectBannerItemDetail(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 등록
     * @param bannerItemVO 배너 아이템 정보
     */
    void insertBannerItem(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    void updateBannerItem(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 플래그 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    void updateBannerItemFlag(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 초기화
     * @param bannerItemVO 배너 아이템 정보
     */
    void updateBannerItemReset(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 삭제
     * @param bannerItemVO 배너 아이템 정보
     */
    void deleteBannerItem(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 일괄 삭제 (부모 배너 기준)
     * @param bannerVO 배너 정보
     */
    void deleteBannerItemByBannerCd(BannerVO bannerVO);
}
