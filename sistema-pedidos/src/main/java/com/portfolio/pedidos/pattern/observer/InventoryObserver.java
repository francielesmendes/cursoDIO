package com.portfolio.pedidos.pattern.observer;

import com.portfolio.pedidos.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InventoryObserver implements OrderObserver {

    private static final Logger log = LoggerFactory.getLogger(InventoryObserver.class);

    @Override
    public void onOrderConfirmed(Order order) {
        order.getItems().forEach(item ->
                log.info("[ESTOQUE] Reservando {} unidade(s) do produto {}", item.quantity(), item.productId())
        );
    }
}
