package com.academy.user.cart;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartMapper {
    List<CartItem> findByUserId(@Param("userId") String userId);

    int upsert(@Param("cartItemId") String cartItemId,
               @Param("userId") String userId,
               @Param("mstCode") String mstCode,
               @Param("quantity") int quantity,
               @Param("priceSnapshot") BigDecimal priceSnapshot);

    int delete(@Param("userId") String userId, @Param("mstCode") String mstCode);

    int clear(@Param("userId") String userId);
}
