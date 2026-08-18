package com.portfolio.pedidos.pattern.observer;

import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.pattern.factory.NotificationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationObserver implements OrderObserver {

    private static final Logger log = LoggerFactory.getLogger(NotificationObserver.class);

    private final NotificationFactory notificationFactory;

    public NotificationObserver(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    @Override
    public void onOrderConfirmed(Order order) {
        var notification = notificationFactory.create(order.getNotificationType());
        notification.send(order);
        log.info("Notificação {} disparada para o pedido {}", notification.channel(), order.getId());
    }
}
