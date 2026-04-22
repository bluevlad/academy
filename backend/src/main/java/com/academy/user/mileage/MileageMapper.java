package com.academy.user.mileage;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface MileageMapper {
    BigDecimal sumBalance(@Param("userId") String userId);

    List<MileageLedgerEntry> findHistory(@Param("userId") String userId,
                                         @Param("limit") int limit);

    int insertEntry(@Param("userId") String userId,
                    @Param("delta") BigDecimal delta,
                    @Param("reason") String reason,
                    @Param("orderId") String orderId,
                    @Param("balanceAfter") BigDecimal balanceAfter);
}
