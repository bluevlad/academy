package com.academy.user.coupon;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CouponMapper {
    List<CouponUser> findUsableByUserId(@Param("userId") String userId);

    Optional<CouponUser> findOwnedByUserAndCoupon(@Param("userId") String userId,
                                                  @Param("couponId") String couponId);

    int issueToUser(@Param("couponUserId") String couponUserId,
                    @Param("userId") String userId,
                    @Param("couponId") String couponId);

    int markUsed(@Param("couponUserId") String couponUserId,
                 @Param("orderId") String orderId,
                 @Param("usedAt") LocalDateTime usedAt);
}
