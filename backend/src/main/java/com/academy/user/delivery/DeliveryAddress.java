package com.academy.user.delivery;

public record DeliveryAddress(
    String addressId,
    String userId,
    String recipient,
    String phone,
    String zipCode,
    String address1,
    String address2,
    boolean isDefault
) {}
