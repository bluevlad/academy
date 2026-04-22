package com.academy.user.login;

import java.time.LocalDateTime;

/**
 * {@code acm_member} 테이블의 로그인 검증용 VO (Sprint 1-2).
 *
 * <p>{@link com.academy.login.service.MemberVO} 와 컬럼은 공유하지만,
 * 이 VO 는 로그인/인증 경로에 필요한 최소 필드만 담는다. 장기적으로는 기존
 * MemberVO 가 이관되면서 자연 통합 예정.
 */
public class UserAccountVO {

    private String userId;
    private String userNm;
    private String userPwd;
    private String userRole;
    private String adminRole;
    private String email;
    private String isUse;
    private LocalDateTime regDt;
    private LocalDateTime updDt;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserNm() { return userNm; }
    public void setUserNm(String userNm) { this.userNm = userNm; }

    public String getUserPwd() { return userPwd; }
    public void setUserPwd(String userPwd) { this.userPwd = userPwd; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public String getAdminRole() { return adminRole; }
    public void setAdminRole(String adminRole) { this.adminRole = adminRole; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getIsUse() { return isUse; }
    public void setIsUse(String isUse) { this.isUse = isUse; }

    public LocalDateTime getRegDt() { return regDt; }
    public void setRegDt(LocalDateTime regDt) { this.regDt = regDt; }

    public LocalDateTime getUpdDt() { return updDt; }
    public void setUpdDt(LocalDateTime updDt) { this.updDt = updDt; }

    public boolean isActive() {
        return "Y".equalsIgnoreCase(isUse);
    }
}
