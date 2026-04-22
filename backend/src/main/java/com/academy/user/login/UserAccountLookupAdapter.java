package com.academy.user.login;

import com.academy.shared.auth.UserCredentialsLookup;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * {@link UserCredentialsLookup} 구현 — {@link UserAccountMapper} 어댑터.
 * shared.auth 의 port 를 user 모듈이 구현해서 ArchUnit 경계(shared→user 금지)를 지킨다.
 */
@Component
public class UserAccountLookupAdapter implements UserCredentialsLookup {

    private final UserAccountMapper mapper;

    public UserAccountLookupAdapter(UserAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<UserCredentials> findByUserId(String userId) {
        return mapper.findByUserId(userId).map(vo -> new UserCredentials(
            vo.getUserId(),
            vo.getUserPwd(),
            vo.getUserRole(),
            vo.isActive()
        ));
    }
}
