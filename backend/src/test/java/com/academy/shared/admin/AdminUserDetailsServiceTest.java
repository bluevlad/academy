package com.academy.shared.admin;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * AdminUserDetailsService — AdminMapper 가 반환하는 VO 를 UserDetails 로 올바로 변환하는지 검증.
 */
class AdminUserDetailsServiceTest {

    private final AdminMapper mapper = mock(AdminMapper.class);
    private final AdminUserDetailsService svc = new AdminUserDetailsService(mapper);

    @Test
    void returns_user_details_for_active_admin() {
        AdminVO vo = new AdminVO();
        vo.setAdminId("a-1");
        vo.setUsername("admin");
        vo.setPasswordHash("{bcrypt}$2a$10$x");
        vo.setRole("ROLE_ADMIN");
        vo.setEnabled(true);
        when(mapper.findByUsernameOrEmail("admin")).thenReturn(Optional.of(vo));

        UserDetails user = svc.loadUserByUsername("admin");

        assertThat(user.getUsername()).isEqualTo("admin");
        assertThat(user.getPassword()).isEqualTo("{bcrypt}$2a$10$x");
        assertThat(user.isEnabled()).isTrue();
        assertThat(user.getAuthorities()).anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
    }

    @Test
    void marks_disabled_when_admin_enabled_is_false() {
        AdminVO vo = new AdminVO();
        vo.setAdminId("a-2");
        vo.setUsername("ex-admin");
        vo.setPasswordHash("{bcrypt}$2a$10$y");
        vo.setRole("ROLE_ADMIN");
        vo.setEnabled(false);
        when(mapper.findByUsernameOrEmail(any())).thenReturn(Optional.of(vo));

        UserDetails user = svc.loadUserByUsername("ex-admin");

        assertThat(user.isEnabled()).isFalse();
    }

    @Test
    void throws_username_not_found_when_missing() {
        when(mapper.findByUsernameOrEmail(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> svc.loadUserByUsername("nobody"))
            .isInstanceOf(UsernameNotFoundException.class);
    }
}
