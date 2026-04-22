package com.academy.user.signup;

import com.academy.user.login.UserAccountMapper;
import com.academy.user.login.UserAccountVO;
import com.academy.user.signup.dto.SignupRequest;
import com.academy.user.signup.dto.SignupResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserSignupServiceTest {

    private UserAccountMapper mapper;
    private UserSignupService service;

    @BeforeEach
    void setUp() {
        mapper = mock(UserAccountMapper.class);
        service = new UserSignupService(mapper, new BCryptPasswordEncoder());
    }

    @Test
    void signup_inserts_bcrypt_hashed_account() {
        when(mapper.existsByUserId(any())).thenReturn(0);
        when(mapper.existsByEmail(any())).thenReturn(0);

        SignupResponse resp = service.signup(new SignupRequest(
            "stu-new", "p@ssword1", "신규학생", "new@example.com"
        ));

        ArgumentCaptor<UserAccountVO> captor = ArgumentCaptor.forClass(UserAccountVO.class);
        verify(mapper).insert(captor.capture());
        UserAccountVO saved = captor.getValue();

        assertThat(saved.getUserId()).isEqualTo("stu-new");
        assertThat(saved.getUserPwd()).startsWith("$2"); // BCrypt prefix
        assertThat(saved.getUserPwd()).isNotEqualTo("p@ssword1");
        assertThat(saved.getUserRole()).isEqualTo("USER");
        assertThat(saved.getIsUse()).isEqualTo("Y");

        assertThat(resp.userId()).isEqualTo("stu-new");
        assertThat(resp.userNm()).isEqualTo("신규학생");
    }

    @Test
    void signup_rejects_duplicate_userId() {
        when(mapper.existsByUserId("stu-dup")).thenReturn(1);

        assertThatThrownBy(() ->
            service.signup(new SignupRequest("stu-dup", "p@ssword1", "X", "a@b.com"))
        ).isInstanceOf(UserSignupService.DuplicateAccountException.class)
            .hasMessageContaining("아이디");
    }

    @Test
    void signup_rejects_duplicate_email_when_provided() {
        when(mapper.existsByUserId(any())).thenReturn(0);
        when(mapper.existsByEmail("taken@example.com")).thenReturn(1);

        assertThatThrownBy(() ->
            service.signup(new SignupRequest("stu-x", "p@ssword1", "X", "taken@example.com"))
        ).isInstanceOf(UserSignupService.DuplicateAccountException.class)
            .hasMessageContaining("이메일");
    }

    @Test
    void signup_allows_null_email() {
        when(mapper.existsByUserId(any())).thenReturn(0);

        SignupResponse resp = service.signup(new SignupRequest(
            "stu-ne", "p@ssword1", "이메일없음", null
        ));

        assertThat(resp.email()).isNull();
    }
}
