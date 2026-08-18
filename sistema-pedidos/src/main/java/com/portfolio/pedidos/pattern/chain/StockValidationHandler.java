package com.portfolio.pedidos.pattern.chain;

import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.exception.OrderValidationException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StockValidationHandler extends OrderValidationHandler {

    private static final Map<String, Integer> STOCK = Map.of(
            "PROD-001", 50,
            "PROD-002", 30,
            "PROD-003", 10,
            "PROD-004", 5
    );

    @Override
    protected void doValidate(Order order) {
        for (var item : order.getItems()) {
            int available = STOCK.getOrDefault(item.productId(), 0);
            if (item.quantity() > available) {
                throw new OrderValidationException(
                        "Estoque insuficiente para o produto %s. Disponível: %d, solicitado: %d"
                                .formatted(item.productId(), available, item.quantity())
                );
            }
        }
    }
}
