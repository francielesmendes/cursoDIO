package com.portfolio.pedidos.pattern.strategy;

import com.portfolio.pedidos.domain.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SedexShippingStrategy implements ShippingStrategy {

    private static final BigDecimal BASE_COST = new BigDecimal("29.90");
    private static final BigDecimal PER_ITEM = new BigDecimal("4.00");

    @Override
    public BigDecimal calculateCost(Order order) {
        int itemCount = order.getItems().stream().mapToInt(item -> item.quantity()).sum();
        return BASE_COST.add(PER_ITEM.multiply(BigDecimal.valueOf(itemCount)));
    }

    @Override
    public int estimatedDeliveryDays() {
        return 3;
    }

    @Override
    public String description() {
        return "SEDEX - Entrega expressa";
    }
}
