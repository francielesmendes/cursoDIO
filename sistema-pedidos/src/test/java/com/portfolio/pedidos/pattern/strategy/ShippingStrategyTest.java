package com.portfolio.pedidos.pattern.strategy;

import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.domain.OrderItem;
import com.portfolio.pedidos.domain.ShippingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingStrategyTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(
                List.of(new OrderItem("PROD-001", "Item", 2, new BigDecimal("10.00"))),
                null,
                ShippingType.PAC,
                "PIX",
                com.portfolio.pedidos.domain.NotificationType.EMAIL,
                "test@email.com"
        );
    }

    @Test
    void pacCalculatesCostBasedOnItems() {
        var strategy = new PacShippingStrategy();
        assertEquals(new BigDecimal("20.90"), strategy.calculateCost(order));
    }

    @Test
    void pickupHasZeroCost() {
        var strategy = new PickupShippingStrategy();
        assertEquals(BigDecimal.ZERO, strategy.calculateCost(order));
    }
}
