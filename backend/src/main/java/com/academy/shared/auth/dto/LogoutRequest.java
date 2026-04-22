package com.academy.shared.auth.dto;

/**
 * 로그아웃 — refresh 제공 시 해당 jti 만, 비워두면 해당 유저 모든 refresh 삭제.
 */
public record LogoutRequest(String refreshToken) {}
