package com.portfolio.pedidos.pattern.chain;

import org.springframework.stereotype.Component;

@Component
public class OrderValidationChain {

    private final OrderValidationHandler firstHandler;

    public OrderValidationChain(
            StockValidationHandler stock,
            PaymentValidationHandler payment,
            AddressValidationHandler address
    ) {
        stock.setNext(payment).setNext(address);
        this.firstHandler = stock;
    }

    public OrderValidationHandler getFirstHandler() {
        return firstHandler;
    }
}
