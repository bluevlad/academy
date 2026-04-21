package com.academy.popup.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.PopupMapper;

@Service
public class PopupService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final PopupMapper popupMapper;

    @Autowired
    public PopupService(PopupMapper popupMapper) {
        this.popupMapper = popupMapper;
    }

    public List<PopupVO> getPopupList(PopupVO vo) { return popupMapper.selectPopupList(vo); }
    public int getPopupListCount(PopupVO vo) { return popupMapper.selectPopupListCount(vo); }
    public PopupVO getPopupDetail(PopupVO vo) { return popupMapper.selectPopupDetail(vo); }
    @Transactional
    public int insertPopup(PopupVO vo) { return popupMapper.insertPopup(vo); }
    @Transactional
    public int updatePopup(PopupVO vo) { return popupMapper.updatePopup(vo); }
    @Transactional
    public int deletePopup(PopupVO vo) { return popupMapper.deletePopup(vo); }
    @Transactional
    public int updatePopupOpenYn(PopupVO vo) { return popupMapper.updatePopupOpenYn(vo); }
    @Transactional
    public int updatePopupHit(PopupVO vo) { return popupMapper.updatePopupHit(vo); }
}
