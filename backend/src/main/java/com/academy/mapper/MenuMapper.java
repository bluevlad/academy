package com.academy.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.menu.service.MenuVO;

@Mapper
public interface MenuMapper {

    public ArrayList<JSONObject> getMenuTree(MenuVO menuVO);

    public ArrayList<JSONObject> getpassMenuTree(MenuVO menuVO);

    public HashMap<String, Object> getDetailMenu(MenuVO menuVO);

    public HashMap<String, Object> getpassDetailMenu(MenuVO menuVO);

    public int menuUpdateProcess(MenuVO menuVO);

    public int sgMenuUpdateProcess(MenuVO menuVO);

    public int passmenuUpdateProcess(MenuVO menuVO);

    public int menuDeleteProcess(MenuVO menuVO);

    public int passmenuDeleteProcess(MenuVO menuVO);

    public int menuIdCheck(MenuVO menuVO);

    public HashMap<String, Object> getMaxMenuId(MenuVO menuVO);

    public HashMap<String, Object> getpassMaxMenuId(MenuVO menuVO);

    public int menuInsertProcess(MenuVO menuVO);

    public int passmenuInsertProcess(MenuVO menuVO);

}
