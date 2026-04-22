package com.academy.user.mypage.privateinfo;

import com.academy.shared.auth.AuthService;
import com.academy.shared.security.Audience;
import com.academy.user.login.UserAccountMapper;
import com.academy.user.login.UserAccountVO;
import com.academy.user.mypage.privateinfo.dto.CertificateResponse;
import com.academy.user.mypage.privateinfo.dto.PasswordChangeRequest;
import com.academy.user.mypage.privateinfo.dto.ProfileResponse;
import com.academy.user.mypage.privateinfo.dto.ProfileUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 수강생 마이페이지 — P0 4건 (Sprint 1-4).
 * 개인정보 조회/수정 · 비밀번호 변경 · 회원탈퇴 · 수강확인증 (skeleton).
 */
@Service
public class PrivateInfoService {

    private static final Logger log = LoggerFactory.getLogger(PrivateInfoService.class);

    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public PrivateInfoService(
        UserAccountMapper userAccountMapper,
        PasswordEncoder passwordEncoder,
        AuthService authService
    ) {
        this.userAccountMapper = userAccountMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    public ProfileResponse getProfile(String userId) {
        UserAccountVO vo = userAccountMapper.findByUserId(userId)
            .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다."));
        return new ProfileResponse(
            vo.getUserId(), vo.getUserNm(), vo.getEmail(), vo.getUserRole(), vo.getIsUse(),
            vo.getRegDt(), vo.getUpdDt()
        );
    }

    @Transactional
    public ProfileResponse updateProfile(String userId, ProfileUpdateRequest req) {
        UserAccountVO vo = userAccountMapper.findByUserId(userId)
            .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다."));
        vo.setUserNm(req.userNm());
        vo.setEmail(req.email());
        userAccountMapper.updateProfile(vo);
        log.info("개인정보 수정: userId={}", userId);
        return getProfile(userId);
    }

    @Transactional
    public void changePassword(String userId, PasswordChangeRequest req) {
        UserAccountVO vo = userAccountMapper.findByUserId(userId)
            .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다."));
        if (!passwordEncoder.matches(req.currentPassword(), vo.getUserPwd())) {
            throw new PasswordMismatchException("현재 비밀번호가 일치하지 않습니다.");
        }
        if (passwordEncoder.matches(req.newPassword(), vo.getUserPwd())) {
            throw new PasswordPolicyException("새 비밀번호가 기존과 동일합니다.");
        }
        userAccountMapper.updatePassword(userId, passwordEncoder.encode(req.newPassword()));
        authService.logoutAll(Audience.USER, userId);
        log.info("비밀번호 변경 + 모든 refresh 폐기: userId={}", userId);
    }

    @Transactional
    public void withdraw(String userId) {
        UserAccountVO vo = userAccountMapper.findByUserId(userId)
            .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다."));
        if (!vo.isActive()) {
            return;  // 이미 탈퇴 상태 — 멱등
        }
        userAccountMapper.markWithdrawn(userId);
        authService.logoutAll(Audience.USER, userId);
        log.info("회원탈퇴: userId={}", userId);
    }

    /**
     * 수강확인증 — 현재 enrollment 테이블이 없으므로 기본 필드 + 빈 리스트 반환.
     * Sprint 3 (enrollment 구축) 이후 실제 수강 이력 매핑.
     */
    public CertificateResponse issueCertificate(String userId) {
        UserAccountVO vo = userAccountMapper.findByUserId(userId)
            .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다."));
        return new CertificateResponse(
            vo.getUserId(),
            vo.getUserNm(),
            vo.getEmail(),
            LocalDate.now(),
            List.of(),
            "수강 이력 연동은 Sprint 3(enrollment) 이후 제공됩니다."
        );
    }

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String m) { super(m); }
    }

    public static class PasswordMismatchException extends RuntimeException {
        public PasswordMismatchException(String m) { super(m); }
    }

    public static class PasswordPolicyException extends RuntimeException {
        public PasswordPolicyException(String m) { super(m); }
    }
}
