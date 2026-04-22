package com.academy.user.mypage.privateinfo;

import com.academy.shared.auth.AuthService;
import com.academy.shared.security.Audience;
import com.academy.user.login.UserAccountMapper;
import com.academy.user.login.UserAccountVO;
import com.academy.user.mylecture.MyLectureMapper;
import com.academy.user.mylecture.MyLectureView;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 수강생 마이페이지 — P0 4건 (Sprint 1-4 · Sprint 5 에서 certificate 를 en_enrollment 실데이터와 연동).
 */
@Service
public class PrivateInfoService {

    private static final Logger log = LoggerFactory.getLogger(PrivateInfoService.class);
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final MyLectureMapper myLectureMapper;

    public PrivateInfoService(
        UserAccountMapper userAccountMapper,
        PasswordEncoder passwordEncoder,
        AuthService authService,
        MyLectureMapper myLectureMapper
    ) {
        this.userAccountMapper = userAccountMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.myLectureMapper = myLectureMapper;
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
     * 수강확인증 — en_enrollment + TB_TOP_MST JOIN 결과를 그대로 발급 리스트로 매핑 (Sprint 5).
     */
    public CertificateResponse issueCertificate(String userId) {
        UserAccountVO vo = userAccountMapper.findByUserId(userId)
            .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다."));

        List<MyLectureView> mine = myLectureMapper.findActiveByUserId(userId);
        List<CertificateResponse.EnrollmentSummary> enrollments = mine.stream()
            .map(v -> new CertificateResponse.EnrollmentSummary(
                v.mstCode(),
                v.subjectTitle() == null ? v.mstCode() : v.subjectTitle(),
                formatPeriod(v.periodStart(), v.periodEnd()),
                v.status()
            ))
            .toList();

        return new CertificateResponse(
            vo.getUserId(),
            vo.getUserNm(),
            vo.getEmail(),
            LocalDate.now(),
            enrollments,
            enrollments.isEmpty() ? "현재 활성 수강권이 없습니다." : null
        );
    }

    private String formatPeriod(LocalDate start, LocalDate end) {
        return (start == null ? "" : start.format(DATE_FMT))
            + " ~ "
            + (end == null ? "" : end.format(DATE_FMT));
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
