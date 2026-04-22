package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

/**
 * {@code acm_member.user_pwd} 를 평문 → BCrypt 로 일괄 전환 (Sprint 1-2 · ADR-002).
 *
 * <p>기존 평문 비밀번호는 그대로 사용자가 계속 로그인 가능하도록 BCrypt 로 감싸기만 한다.
 * 이미 BCrypt 포맷 ({@code $2a$}, {@code $2b$}, {@code $2y$}) 인 row 는 skip.
 *
 * <p>Flyway schema_history 가 실행 이력을 기록하므로 한 번만 실행된다.
 * 운영 DB 적용 전 개발 환경에서 반드시 검증할 것.
 */
public class V4__acm_member_pwd_bcrypt_rehash extends BaseJavaMigration {

    private static final Logger log = Logger.getLogger(
        V4__acm_member_pwd_bcrypt_rehash.class.getName());

    @Override
    public void migrate(Context context) throws Exception {
        Connection conn = context.getConnection();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        int rehashed = 0;
        int skipped = 0;
        int empty = 0;

        try (PreparedStatement select = conn.prepareStatement(
                "SELECT user_id, user_pwd FROM acm_member");
             PreparedStatement update = conn.prepareStatement(
                "UPDATE acm_member SET user_pwd = ? WHERE user_id = ?");
             ResultSet rs = select.executeQuery()) {

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String pwd = rs.getString("user_pwd");

                if (pwd == null || pwd.isBlank()) {
                    empty++;
                    continue;
                }
                if (pwd.startsWith("$2a$") || pwd.startsWith("$2b$") || pwd.startsWith("$2y$")) {
                    skipped++;
                    continue;
                }

                update.setString(1, encoder.encode(pwd));
                update.setString(2, userId);
                update.executeUpdate();
                rehashed++;
            }
        }

        log.info(String.format(
            "acm_member pwd BCrypt rehash 결과: %d rehashed / %d already hashed / %d empty",
            rehashed, skipped, empty));
    }
}
