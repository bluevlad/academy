package com.academy.shared.security;

/**
 * JWT 의 {@code aud} claim 값. URL prefix 필터 분기에 사용.
 */
public enum Audience {
    ADMIN("admin"),
    USER("user");

    private final String value;

    Audience(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Audience of(String value) {
        for (Audience a : values()) {
            if (a.value.equalsIgnoreCase(value)) {
                return a;
            }
        }
        throw new IllegalArgumentException("알 수 없는 audience: " + value);
    }
}
