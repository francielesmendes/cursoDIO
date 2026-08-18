package com.portfolio.pedidos.pattern.chain;

import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.exception.OrderValidationException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PaymentValidationHandler extends OrderValidationHandler {

    private static final Set<String> ACCEPTED_METHODS = Set.of(
            "CREDITO", "DEBITO", "PIX", "BOLETO"
    );

    @Override
    protected void doValidate(Order order) {
        if (!ACCEPTED_METHODS.contains(order.getPaymentMethod().toUpperCase())) {
            throw new OrderValidationException(
                    "Forma de pagamento inválida: %s. Aceitas: %s"
                            .formatted(order.getPaymentMethod(), ACCEPTED_METHODS)
            );
        }
    }
}
