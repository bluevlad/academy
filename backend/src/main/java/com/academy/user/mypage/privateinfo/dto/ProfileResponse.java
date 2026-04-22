package com.academy.user.mypage.privateinfo.dto;

import java.time.LocalDateTime;

public record ProfileResponse(
    String userId,
    String userNm,
    String email,
    String userRole,
    String isUse,
    LocalDateTime regDt,
    LocalDateTime updDt
) {}
