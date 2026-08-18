package com.portfolio.pedidos.pattern.chain;

import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.domain.ShippingType;
import com.portfolio.pedidos.exception.OrderValidationException;
import org.springframework.stereotype.Component;

@Component
public class AddressValidationHandler extends OrderValidationHandler {

    @Override
    protected void doValidate(Order order) {
        if (order.getShippingType() == ShippingType.RETIRADA) {
            return;
        }

        var address = order.getAddress();
        if (address == null) {
            throw new OrderValidationException("Endereço é obrigatório para entrega");
        }

        if (isBlank(address.street()) || isBlank(address.city())
                || isBlank(address.state()) || isBlank(address.zipCode())) {
            throw new OrderValidationException("Endereço incompleto para entrega");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
