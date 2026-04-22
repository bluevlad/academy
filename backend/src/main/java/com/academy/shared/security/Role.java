package com.academy.shared.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 최상위 역할 구분 (ADR-002). 세부 권한(auth code) 은 추후 role 아래 별도 claim.
 */
public enum Role {
    ADMIN,
    USER;

    public GrantedAuthority asAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + name());
    }
}
