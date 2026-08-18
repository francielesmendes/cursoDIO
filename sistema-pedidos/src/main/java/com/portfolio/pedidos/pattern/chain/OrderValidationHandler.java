package com.portfolio.pedidos.pattern.chain;

import com.portfolio.pedidos.domain.Order;

public abstract class OrderValidationHandler {

    private OrderValidationHandler next;

    public OrderValidationHandler setNext(OrderValidationHandler next) {
        this.next = next;
        return next;
    }

    public final void validate(Order order) {
        doValidate(order);
        if (next != null) {
            next.validate(order);
        }
    }

    protected abstract void doValidate(Order order);
}
