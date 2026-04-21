import apiClient from "shared/api/client";

// 일반 메뉴 트리 조회
export const fetchMenuTree = async () => {
  try {
    const response = await apiClient.get("/menu/getMenuTree", {
      params: { onoffDiv: "O" },
    });

    // API 응답 처리 (menuTree 배열로 반환)
    let menuData = [];

    if (Array.isArray(response.data)) {
      menuData = response.data;
    } else if (response.data && Array.isArray(response.data.menuTree)) {
      menuData = response.data.menuTree;
    } else if (response.data && response.data.data) {
      menuData = Array.isArray(response.data.data) ? response.data.data : [];
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
    const response = await apiClient.get("/menu/getpassMenuTree");

    // API 응답 처리 (menuTree 배열로 반환)
    let menuData = [];

    if (Array.isArray(response.data)) {
      menuData = response.data;
    } else if (response.data && Array.isArray(response.data.menuTree)) {
      menuData = response.data.menuTree;
    } else if (response.data && response.data.data) {
      menuData = Array.isArray(response.data.data) ? response.data.data : [];
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
    const response = await apiClient.get("/menu/getDetailMenu", {
      params: { menuId },
    });

    // 데이터 매핑 (member API 패턴 참고)
    const menuDetail = response.data;
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
    const response = await apiClient.get("/menu/getpassDetailMenu", {
      params: { menuId },
    });

    // 데이터 매핑 (member API 패턴 참고)
    const menuDetail = response.data;
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
    const response = await apiClient.post("/menu/menuUpdateProcess", menuData);

    return response.data;
  } catch (error) {
    console.error("Error updating menu:", error);
    throw error;
  }
};

// Pass 메뉴 수정
export const updatePassMenu = async (menuData) => {
  try {
    const response = await apiClient.post("/menu/passmenuUpdateProcess", menuData);

    return response.data;
  } catch (error) {
    console.error("Error updating pass menu:", error);
    throw error;
  }
};

// 일반 메뉴 삭제
export const deleteMenu = async (menuId) => {
  try {
    const response = await apiClient.post("/menu/menuDeleteProcess", { menuId });

    return response.data;
  } catch (error) {
    console.error("Error deleting menu:", error);
    throw error;
  }
};

// Pass 메뉴 삭제
export const deletePassMenu = async (menuId) => {
  try {
    const response = await apiClient.post("/menu/passmenuDeleteProcess", { menuId });

    return response.data;
  } catch (error) {
    console.error("Error deleting pass menu:", error);
    throw error;
  }
};

// 메뉴 ID 중복 체크
export const checkMenuId = async (menuId) => {
  try {
    const response = await apiClient.get("/menu/menuIdCheck", {
      params: { menuId },
    });

    return response.data;
  } catch (error) {
    console.error("Error checking menu ID:", error);
    throw error;
  }
};

// 일반 메뉴 최대 ID 조회
export const getMaxMenuId = async () => {
  try {
    const response = await apiClient.get("/menu/getMaxMenuId");

    return response.data;
  } catch (error) {
    console.error("Error getting max menu ID:", error);
    throw error;
  }
};

// Pass 메뉴 최대 ID 조회
export const getPassMaxMenuId = async () => {
  try {
    const response = await apiClient.get("/menu/getpassMaxMenuId");

    return response.data;
  } catch (error) {
    console.error("Error getting pass max menu ID:", error);
    throw error;
  }
};

// 일반 메뉴 등록
export const insertMenu = async (menuData) => {
  try {
    const response = await apiClient.post("/menu/menuInsertProcess", menuData);

    return response.data;
  } catch (error) {
    console.error("Error inserting menu:", error);
    throw error;
  }
};

// Pass 메뉴 등록
export const insertPassMenu = async (menuData) => {
  try {
    const response = await apiClient.post("/menu/passmenuInsertProcess", menuData);

    return response.data;
  } catch (error) {
    console.error("Error inserting pass menu:", error);
    throw error;
  }
};
