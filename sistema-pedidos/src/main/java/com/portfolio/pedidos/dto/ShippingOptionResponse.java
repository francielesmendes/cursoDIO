package com.portfolio.pedidos.dto;

import com.portfolio.pedidos.domain.ShippingType;

import java.math.BigDecimal;

public record ShippingOptionResponse(
        ShippingType type,
        String description,
        BigDecimal cost,
        int estimatedDays
) {
}
