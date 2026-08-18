package com.portfolio.pedidos.pattern.factory;

import com.portfolio.pedidos.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotification implements Notification {

    private static final Logger log = LoggerFactory.getLogger(EmailNotification.class);

    @Override
    public void send(Order order) {
        log.info("[EMAIL] Pedido {} confirmado. Enviando para {}", order.getId(), order.getCustomerEmail());
    }

    @Override
    public String channel() {
        return "EMAIL";
    }
}
