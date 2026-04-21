import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// 일반 메뉴 트리 조회
export const fetchMenuTree = async () => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/getMenuTree?onoffDiv=O`);

    // API 응답 처리 (menuTree 배열로 반환)
    let menuData = [];

    if (Array.isArray(response.body)) {
      menuData = response.body;
    } else if (response.body && Array.isArray(response.body.menuTree)) {
      menuData = response.body.menuTree;
    } else if (response.body && response.body.data) {
      menuData = Array.isArray(response.body.data) ? response.body.data : [];
    }

    // 데이터 매핑 (member API 패턴 참고)
    return {
      menuTree: menuData.map((menu) => ({
        menuSeq: menu.MENU_SEQ,
        isUse: menu.ISUSE,
        menuId: menu.MENU_ID,
        pMenuId: menu.P_MENUID,
        menuNm: menu.MENU_NM,
        menuUrl: menu.MENU_URL || "",
        menuInfo: menu.MENU_INFO || "",
        target: menu.TARGET || "_self",
      })),
    };
  } catch (error) {
    console.error("Error fetching menu tree data:", error);
    return { menuTree: [] };
  }
};

// Pass 메뉴 트리 조회
export const fetchPassMenuTree = async () => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/getpassMenuTree`);

    // API 응답 처리 (menuTree 배열로 반환)
    let menuData = [];

    if (Array.isArray(response.body)) {
      menuData = response.body;
    } else if (response.body && Array.isArray(response.body.menuTree)) {
      menuData = response.body.menuTree;
    } else if (response.body && response.body.data) {
      menuData = Array.isArray(response.body.data) ? response.body.data : [];
    }

    // 데이터 매핑 (member API 패턴 참고)
    return {
      menuTree: menuData.map((menu) => ({
        menuSeq: menu.MENU_SEQ,
        isUse: menu.ISUSE,
        menuId: menu.MENU_ID,
        pMenuId: menu.P_MENUID,
        menuNm: menu.MENU_NM,
        menuUrl: menu.MENU_URL || "",
        menuInfo: menu.MENU_INFO || "",
        target: menu.TARGET || "_self",
      })),
    };
  } catch (error) {
    console.error("Error fetching pass menu tree data:", error);
    return { menuTree: [] };
  }
};

// 일반 메뉴 상세 조회
export const fetchDetailMenu = async (menuId) => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/getDetailMenu`).query({ menuId });

    // 데이터 매핑 (member API 패턴 참고)
    const menuDetail = response.body;
    return {
      menuSeq: menuDetail.MENU_SEQ,
      isUse: menuDetail.ISUSE,
      menuId: menuDetail.MENU_ID,
      pMenuId: menuDetail.P_MENUID,
      menuNm: menuDetail.MENU_NM,
      menuUrl: menuDetail.MENU_URL || "",
      menuInfo: menuDetail.MENU_INFO || "",
      target: menuDetail.TARGET || "_self",
    };
  } catch (error) {
    console.error("Error fetching detail menu:", error);
    return {};
  }
};

// Pass 메뉴 상세 조회
export const fetchPassDetailMenu = async (menuId) => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/getpassDetailMenu`).query({ menuId });

    // 데이터 매핑 (member API 패턴 참고)
    const menuDetail = response.body;
    return {
      menuSeq: menuDetail.MENU_SEQ,
      isUse: menuDetail.ISUSE,
      menuId: menuDetail.MENU_ID,
      pMenuId: menuDetail.P_MENUID,
      menuNm: menuDetail.MENU_NM,
      menuUrl: menuDetail.MENU_URL || "",
      menuInfo: menuDetail.MENU_INFO || "",
      target: menuDetail.TARGET || "_self",
    };
  } catch (error) {
    console.error("Error fetching pass detail menu:", error);
    return {};
  }
};

// 일반 메뉴 수정
export const updateMenu = async (menuData) => {
  try {
    const response = await superagent.post(`${BASE_API}/menu/menuUpdateProcess`).send(menuData);

    return response.body;
  } catch (error) {
    console.error("Error updating menu:", error);
    throw error;
  }
};

// Pass 메뉴 수정
export const updatePassMenu = async (menuData) => {
  try {
    const response = await superagent.post(`${BASE_API}/menu/passmenuUpdateProcess`).send(menuData);

    return response.body;
  } catch (error) {
    console.error("Error updating pass menu:", error);
    throw error;
  }
};

// 일반 메뉴 삭제
export const deleteMenu = async (menuId) => {
  try {
    const response = await superagent.post(`${BASE_API}/menu/menuDeleteProcess`).send({ menuId });

    return response.body;
  } catch (error) {
    console.error("Error deleting menu:", error);
    throw error;
  }
};

// Pass 메뉴 삭제
export const deletePassMenu = async (menuId) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/menu/passmenuDeleteProcess`)
      .send({ menuId });

    return response.body;
  } catch (error) {
    console.error("Error deleting pass menu:", error);
    throw error;
  }
};

// 메뉴 ID 중복 체크
export const checkMenuId = async (menuId) => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/menuIdCheck`).query({ menuId });

    return response.body;
  } catch (error) {
    console.error("Error checking menu ID:", error);
    throw error;
  }
};

// 일반 메뉴 최대 ID 조회
export const getMaxMenuId = async () => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/getMaxMenuId`);

    return response.body;
  } catch (error) {
    console.error("Error getting max menu ID:", error);
    throw error;
  }
};

// Pass 메뉴 최대 ID 조회
export const getPassMaxMenuId = async () => {
  try {
    const response = await superagent.get(`${BASE_API}/menu/getpassMaxMenuId`);

    return response.body;
  } catch (error) {
    console.error("Error getting pass max menu ID:", error);
    throw error;
  }
};

// 일반 메뉴 등록
export const insertMenu = async (menuData) => {
  try {
    const response = await superagent.post(`${BASE_API}/menu/menuInsertProcess`).send(menuData);

    return response.body;
  } catch (error) {
    console.error("Error inserting menu:", error);
    throw error;
  }
};

// Pass 메뉴 등록
export const insertPassMenu = async (menuData) => {
  try {
    const response = await superagent.post(`${BASE_API}/menu/passmenuInsertProcess`).send(menuData);

    return response.body;
  } catch (error) {
    console.error("Error inserting pass menu:", error);
    throw error;
  }
};
