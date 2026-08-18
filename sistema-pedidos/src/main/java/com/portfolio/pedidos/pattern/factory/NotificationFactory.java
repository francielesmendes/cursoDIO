package com.portfolio.pedidos.pattern.factory;

import com.portfolio.pedidos.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    public Notification create(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SmsNotification();
            case PUSH -> new PushNotification();
        };
    }
}
