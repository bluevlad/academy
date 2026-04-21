package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.popup.service.PopupVO;

@Mapper
public interface PopupMapper {
    List<PopupVO> selectPopupList(PopupVO vo);
    int selectPopupListCount(PopupVO vo);
    PopupVO selectPopupDetail(PopupVO vo);
    int insertPopup(PopupVO vo);
    int updatePopup(PopupVO vo);
    int deletePopup(PopupVO vo);
    int updatePopupOpenYn(PopupVO vo);
    int updatePopupHit(PopupVO vo);
}
