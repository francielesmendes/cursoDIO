package com.portfolio.pedidos.pattern.strategy;

import com.portfolio.pedidos.domain.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PickupShippingStrategy implements ShippingStrategy {

    @Override
    public BigDecimal calculateCost(Order order) {
        return BigDecimal.ZERO;
    }

    @Override
    public int estimatedDeliveryDays() {
        return 0;
    }

    @Override
    public String description() {
        return "Retirada na loja - Sem custo de frete";
    }
}
