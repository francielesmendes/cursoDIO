package com.portfolio.pedidos.pattern.factory;

import com.portfolio.pedidos.domain.Order;

public interface Notification {

    void send(Order order);

    String channel();
}
