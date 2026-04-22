package com.academy.shared.admin;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security UserDetailsService — {@code id_admin} 기반 (ADR-002 1-1b).
 *
 * <p>AuthenticationManager 가 이 빈을 사용해 사용자명+비밀번호 검증. 비밀번호는 BCrypt hash.
 */
@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminMapper adminMapper;

    public AdminUserDetailsService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        AdminVO admin = adminMapper.findByUsernameOrEmail(usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("관리자를 찾을 수 없습니다: " + usernameOrEmail));

        return User.withUsername(admin.getUsername())
            .password(admin.getPasswordHash())
            .authorities(List.of(new SimpleGrantedAuthority(admin.getRole())))
            .disabled(!admin.isEnabled())
            .build();
    }
}
