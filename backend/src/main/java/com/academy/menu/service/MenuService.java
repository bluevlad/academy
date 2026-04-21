package com.academy.menu.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.MenuMapper;

@Service
public class MenuService {

    private MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public ArrayList<JSONObject> getMenuTree(MenuVO menuVO) {
        return menuMapper.getMenuTree(menuVO);
    }

    public ArrayList<JSONObject> getpassMenuTree(MenuVO menuVO) {
        return menuMapper.getpassMenuTree(menuVO);
    }

    public HashMap<String, Object> getDetailMenu(MenuVO menuVO) {
        return menuMapper.getDetailMenu(menuVO);
    }

    public HashMap<String, Object> getpassDetailMenu(MenuVO menuVO) {
        return menuMapper.getpassDetailMenu(menuVO);
    }

    public int menuUpdateProcess(MenuVO menuVO) {
        return menuMapper.menuUpdateProcess(menuVO);
    }

    public int sgMenuUpdateProcess(MenuVO menuVO) {
        return menuMapper.sgMenuUpdateProcess(menuVO);
    }

    public int passmenuUpdateProcess(MenuVO menuVO) {
        return menuMapper.passmenuUpdateProcess(menuVO);
    }

    public int menuDeleteProcess(MenuVO menuVO) {
        return menuMapper.menuDeleteProcess(menuVO);
    }

    public int passmenuDeleteProcess(MenuVO menuVO) {
        return menuMapper.passmenuDeleteProcess(menuVO);
    }

    public int menuIdCheck(MenuVO menuVO) {
        return menuMapper.menuIdCheck(menuVO);
    }

    public HashMap<String, Object> getMaxMenuId(MenuVO menuVO) {
        return menuMapper.getMaxMenuId(menuVO);
    }

    public HashMap<String, Object> getpassMaxMenuId(MenuVO menuVO) {
        return menuMapper.getpassMaxMenuId(menuVO);
    }

    public int menuInsertProcess(MenuVO menuVO) {
        return menuMapper.menuInsertProcess(menuVO);
    }

    public int passmenuInsertProcess(MenuVO menuVO) {
        return menuMapper.passmenuInsertProcess(menuVO);
    }

}
