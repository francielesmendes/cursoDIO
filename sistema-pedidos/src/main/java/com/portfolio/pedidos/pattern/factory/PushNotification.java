package com.portfolio.pedidos.pattern.factory;

import com.portfolio.pedidos.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushNotification implements Notification {

    private static final Logger log = LoggerFactory.getLogger(PushNotification.class);

    @Override
    public void send(Order order) {
        log.info("[PUSH] Pedido {} confirmado. Status: {}", order.getId(), order.getStatus());
    }

    @Override
    public String channel() {
        return "PUSH";
    }
}
