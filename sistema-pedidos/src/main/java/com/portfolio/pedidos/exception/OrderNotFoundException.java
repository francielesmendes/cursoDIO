package com.portfolio.pedidos.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String id) {
        super("Pedido não encontrado: " + id);
    }
}
