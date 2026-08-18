package com.portfolio.pedidos.pattern.strategy;

import com.portfolio.pedidos.domain.Order;

import java.math.BigDecimal;

public interface ShippingStrategy {

    BigDecimal calculateCost(Order order);

    int estimatedDeliveryDays();

    String description();
}
