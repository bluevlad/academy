import apiClient from "shared/api/client";

// 관리자 메뉴 트리 조회
export const getMenuTree = async () => {
  try {
    const response = await apiClient.get("/admin/menu/getMenuTree");
    return response.data;
  } catch (error) {
    console.error("Error fetching menu tree:", error);
    return { menuList: [] };
  }
};

// 메뉴 등록
export const insertMenu = async (data) => {
  try {
    const response = await apiClient.post("/admin/menu/insertMenu", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting menu:", error);
    throw error;
  }
};

// 메뉴 수정
export const updateMenu = async (data) => {
  try {
    const response = await apiClient.post("/admin/menu/updateMenu", data);
    return response.data;
  } catch (error) {
    console.error("Error updating menu:", error);
    throw error;
  }
};

// 메뉴 삭제
export const deleteMenu = async (menuId) => {
  try {
    const response = await apiClient.post("/admin/menu/deleteMenu", { menuId });
    return response.data;
  } catch (error) {
    console.error("Error deleting menu:", error);
    throw error;
  }
};

// 메뉴 ID 중복 체크
export const checkMenuId = async (menuId) => {
  try {
    const response = await apiClient.get("/admin/menu/checkMenuId", { params: { menuId } });
    return response.data;
  } catch (error) {
    console.error("Error checking menu ID:", error);
    throw error;
  }
};
