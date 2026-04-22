package com.academy.user.payment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper
public interface PaymentMapper {
    int insert(@Param("paymentId") String paymentId,
               @Param("orderId") String orderId,
               @Param("method") String method,
               @Param("amount") BigDecimal amount);

    int markApproved(@Param("paymentId") String paymentId,
                     @Param("pgTxnId") String pgTxnId,
                     @Param("approvedAt") LocalDateTime approvedAt,
                     @Param("rawResponse") String rawResponse);
}
