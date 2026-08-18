package com.portfolio.pedidos.pattern.factory;

import com.portfolio.pedidos.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsNotification implements Notification {

    private static final Logger log = LoggerFactory.getLogger(SmsNotification.class);

    @Override
    public void send(Order order) {
        log.info("[SMS] Pedido {} confirmado. Total: R$ {}", order.getId(), order.getTotal());
    }

    @Override
    public String channel() {
        return "SMS";
    }
}
