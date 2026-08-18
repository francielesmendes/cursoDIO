package com.portfolio.pedidos.pattern.observer;

import com.portfolio.pedidos.domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEventPublisher {

    private final List<OrderObserver> observers;

    public OrderEventPublisher(
            NotificationObserver notificationObserver,
            InventoryObserver inventoryObserver,
            AnalyticsObserver analyticsObserver
    ) {
        this.observers = List.of(notificationObserver, inventoryObserver, analyticsObserver);
    }

    public void publishOrderConfirmed(Order order) {
        observers.forEach(observer -> observer.onOrderConfirmed(order));
    }
}
