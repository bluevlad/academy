package com.academy.user.delivery;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryMapper {
    List<DeliveryAddress> findAddressesByUserId(@Param("userId") String userId);

    int insertAddress(@Param("addressId") String addressId,
                      @Param("userId") String userId,
                      @Param("recipient") String recipient,
                      @Param("phone") String phone,
                      @Param("zipCode") String zipCode,
                      @Param("address1") String address1,
                      @Param("address2") String address2,
                      @Param("isDefault") boolean isDefault);

    int deleteAddress(@Param("addressId") String addressId, @Param("userId") String userId);
}
