package com.portfolio.pedidos.pattern.observer;

import com.portfolio.pedidos.domain.Order;

public interface OrderObserver {

    void onOrderConfirmed(Order order);
}
