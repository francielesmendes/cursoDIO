package com.portfolio.pedidos.pattern.observer;

import com.portfolio.pedidos.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsObserver implements OrderObserver {

    private static final Logger log = LoggerFactory.getLogger(AnalyticsObserver.class);

    @Override
    public void onOrderConfirmed(Order order) {
        log.info(
                "[ANALYTICS] Pedido {} | frete={} | pagamento={} | total=R$ {}",
                order.getId(),
                order.getShippingType(),
                order.getPaymentMethod(),
                order.getTotal()
        );
    }
}
