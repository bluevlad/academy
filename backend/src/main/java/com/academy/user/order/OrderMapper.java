package com.academy.user.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    int insertOrder(@Param("orderId") String orderId,
                    @Param("userId") String userId,
                    @Param("totalAmount") BigDecimal totalAmount);

    int insertItem(@Param("orderItemId") String orderItemId,
                   @Param("orderId") String orderId,
                   @Param("mstCode") String mstCode,
                   @Param("quantity") int quantity,
                   @Param("unitPrice") BigDecimal unitPrice);

    int markPaid(@Param("orderId") String orderId, @Param("paidAt") LocalDateTime paidAt);

    int markCanceled(@Param("orderId") String orderId, @Param("canceledAt") LocalDateTime canceledAt);

    Optional<Order> findById(@Param("orderId") String orderId);

    List<Order.OrderItem> findItemsByOrderId(@Param("orderId") String orderId);

    List<Order> findByUserId(@Param("userId") String userId);
}
