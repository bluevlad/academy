package com.academy.user.signup;

import com.academy.user.login.UserAccountMapper;
import com.academy.user.login.UserAccountVO;
import com.academy.user.signup.dto.SignupRequest;
import com.academy.user.signup.dto.SignupResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 수강생 이메일 가입 (Sprint 1-3 · ADR-002/005).
 *
 * <p>OAuth 가입은 별도 ({@link com.academy.auth.GoogleOAuthApi} 기반). 여기선 아이디 + 비밀번호
 * 조합만 담당. 저장 시 {@link PasswordEncoder} 가 BCrypt 해시로 변환.
 */
@Service
public class UserSignupService {

    private static final Logger log = LoggerFactory.getLogger(UserSignupService.class);

    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;

    public UserSignupService(UserAccountMapper userAccountMapper, PasswordEncoder passwordEncoder) {
        this.userAccountMapper = userAccountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignupResponse signup(SignupRequest req) {
        if (userAccountMapper.existsByUserId(req.userId()) > 0) {
            throw new DuplicateAccountException("이미 사용 중인 아이디입니다.");
        }
        if (req.email() != null && !req.email().isBlank()
            && userAccountMapper.existsByEmail(req.email()) > 0) {
            throw new DuplicateAccountException("이미 가입된 이메일입니다.");
        }

        UserAccountVO vo = new UserAccountVO();
        vo.setUserId(req.userId());
        vo.setUserNm(req.userNm());
        vo.setUserPwd(passwordEncoder.encode(req.password()));
        vo.setUserRole("USER");
        vo.setEmail(req.email());
        vo.setIsUse("Y");

        userAccountMapper.insert(vo);
        log.info("user 가입 완료: userId={}", req.userId());

        return new SignupResponse(req.userId(), req.userNm(), req.email());
    }

    public static class DuplicateAccountException extends RuntimeException {
        public DuplicateAccountException(String message) {
            super(message);
        }
    }
}
