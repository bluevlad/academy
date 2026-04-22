package com.academy.user.mypage.privateinfo;

import com.academy.shared.auth.AuthService;
import com.academy.shared.security.Audience;
import com.academy.user.login.UserAccountMapper;
import com.academy.user.login.UserAccountVO;
import com.academy.user.mylecture.MyLectureMapper;
import com.academy.user.mypage.privateinfo.dto.CertificateResponse;
import com.academy.user.mypage.privateinfo.dto.PasswordChangeRequest;
import com.academy.user.mypage.privateinfo.dto.ProfileUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PrivateInfoServiceTest {

    private UserAccountMapper mapper;
    private PasswordEncoder encoder;
    private AuthService authService;
    private MyLectureMapper myLectureMapper;
    private PrivateInfoService service;

    @BeforeEach
    void setUp() {
        mapper = mock(UserAccountMapper.class);
        encoder = new BCryptPasswordEncoder();
        authService = mock(AuthService.class);
        myLectureMapper = mock(MyLectureMapper.class);
        when(myLectureMapper.findActiveByUserId(any())).thenReturn(java.util.List.of());
        service = new PrivateInfoService(mapper, encoder, authService, myLectureMapper);
    }

    private UserAccountVO fixture(String userId, String rawPwd, String isUse) {
        UserAccountVO vo = new UserAccountVO();
        vo.setUserId(userId);
        vo.setUserNm(userId);
        vo.setEmail(userId + "@example.com");
        vo.setUserPwd(encoder.encode(rawPwd));
        vo.setUserRole("USER");
        vo.setIsUse(isUse);
        return vo;
    }

    @Test
    void change_password_success_revokes_all_refresh() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "old-pwd1", "Y")));

        service.changePassword("stu-1", new PasswordChangeRequest("old-pwd1", "new-pwd99"));

        ArgumentCaptor<String> hashCaptor = ArgumentCaptor.forClass(String.class);
        verify(mapper).updatePassword(eq("stu-1"), hashCaptor.capture());
        assertThat(encoder.matches("new-pwd99", hashCaptor.getValue())).isTrue();
        verify(authService).logoutAll(Audience.USER, "stu-1");
    }

    @Test
    void change_password_wrong_current_rejected() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "correct", "Y")));

        assertThatThrownBy(() ->
            service.changePassword("stu-1", new PasswordChangeRequest("WRONG", "new-pwd99"))
        ).isInstanceOf(PrivateInfoService.PasswordMismatchException.class);
        verify(mapper, never()).updatePassword(any(), any());
        verify(authService, never()).logoutAll(any(), any());
    }

    @Test
    void change_password_same_as_current_rejected() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "same-pwd1", "Y")));

        assertThatThrownBy(() ->
            service.changePassword("stu-1", new PasswordChangeRequest("same-pwd1", "same-pwd1"))
        ).isInstanceOf(PrivateInfoService.PasswordPolicyException.class);
        verify(mapper, never()).updatePassword(any(), any());
    }

    @Test
    void update_profile_changes_userNm_and_email() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "pw", "Y")));

        service.updateProfile("stu-1", new ProfileUpdateRequest("변경된이름", "new@example.com"));

        ArgumentCaptor<UserAccountVO> captor = ArgumentCaptor.forClass(UserAccountVO.class);
        verify(mapper).updateProfile(captor.capture());
        assertThat(captor.getValue().getUserNm()).isEqualTo("변경된이름");
        assertThat(captor.getValue().getEmail()).isEqualTo("new@example.com");
    }

    @Test
    void withdraw_marks_account_inactive_and_logs_out() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "pw", "Y")));

        service.withdraw("stu-1");

        verify(mapper).markWithdrawn("stu-1");
        verify(authService).logoutAll(Audience.USER, "stu-1");
    }

    @Test
    void withdraw_is_idempotent_for_already_inactive() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "pw", "N")));

        service.withdraw("stu-1");

        verify(mapper, never()).markWithdrawn(any());
        verify(authService, never()).logoutAll(any(), any());
    }

    @Test
    void certificate_returns_skeleton_with_empty_enrollments() {
        when(mapper.findByUserId("stu-1")).thenReturn(Optional.of(fixture("stu-1", "pw", "Y")));

        CertificateResponse cert = service.issueCertificate("stu-1");

        assertThat(cert.userId()).isEqualTo("stu-1");
        assertThat(cert.enrollments()).isEmpty();
        assertThat(cert.issuedDate()).isNotNull();
    }

    @Test
    void missing_account_throws_not_found() {
        when(mapper.findByUserId("ghost")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getProfile("ghost"))
            .isInstanceOf(PrivateInfoService.AccountNotFoundException.class);
    }
}
