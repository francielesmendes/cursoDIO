package com.portfolio.pedidos.service;

import com.portfolio.pedidos.domain.Address;
import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.domain.OrderItem;
import com.portfolio.pedidos.domain.OrderStatus;
import com.portfolio.pedidos.domain.ShippingType;
import com.portfolio.pedidos.dto.CreateOrderRequest;
import com.portfolio.pedidos.dto.ShippingOptionResponse;
import com.portfolio.pedidos.exception.OrderNotFoundException;
import com.portfolio.pedidos.exception.OrderValidationException;
import com.portfolio.pedidos.pattern.chain.OrderValidationChain;
import com.portfolio.pedidos.pattern.observer.OrderEventPublisher;
import com.portfolio.pedidos.pattern.singleton.ApplicationSettings;
import com.portfolio.pedidos.pattern.strategy.ShippingStrategyFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private final OrderValidationChain validationChain;
    private final ShippingStrategyFactory shippingStrategyFactory;
    private final OrderEventPublisher eventPublisher;

    public OrderService(
            OrderValidationChain validationChain,
            ShippingStrategyFactory shippingStrategyFactory,
            OrderEventPublisher eventPublisher
    ) {
        this.validationChain = validationChain;
        this.shippingStrategyFactory = shippingStrategyFactory;
        this.eventPublisher = eventPublisher;
    }

    public Order createOrder(CreateOrderRequest request) {
        Address address = mapAddress(request);

        List<OrderItem> items = request.items().stream()
                .map(item -> new OrderItem(
                        item.productId(),
                        item.productName(),
                        item.quantity(),
                        item.unitPrice()
                ))
                .toList();

        Order order = new Order(
                items,
                address,
                request.shippingType(),
                request.paymentMethod().toUpperCase(),
                request.notificationType(),
                request.customerEmail()
        );

        try {
            validationChain.getFirstHandler().validate(order);

            var strategy = shippingStrategyFactory.getStrategy(order.getShippingType());
            BigDecimal shippingCost = strategy.calculateCost(order);
            BigDecimal total = order.itemsSubtotal().add(shippingCost);

            order.setShippingCost(shippingCost);
            order.setTotal(total);
            order.setStatus(OrderStatus.CONFIRMADO);

            orders.put(order.getId(), order);
            eventPublisher.publishOrderConfirmed(order);

            return order;
        } catch (OrderValidationException ex) {
            order.setStatus(OrderStatus.REJEITADO);
            order.setRejectionReason(ex.getMessage());
            orders.put(order.getId(), order);
            throw ex;
        }
    }

    public Order findById(String id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    public List<ShippingOptionResponse> listShippingOptions() {
        var sampleOrder = buildSampleOrder();
        return Arrays.stream(ShippingType.values())
                .map(type -> {
                    var strategy = shippingStrategyFactory.getStrategy(type);
                    return new ShippingOptionResponse(
                            type,
                            strategy.description(),
                            strategy.calculateCost(sampleOrder),
                            strategy.estimatedDeliveryDays()
                    );
                })
                .toList();
    }

    public Map<String, String> appSettings() {
        var settings = ApplicationSettings.INSTANCE;
        return Map.of(
                "currency", settings.getCurrency(),
                "defaultShipping", settings.getDefaultShipping()
        );
    }

    private Address mapAddress(CreateOrderRequest request) {
        if (request.address() == null) {
            return null;
        }
        return new Address(
                request.address().street(),
                request.address().city(),
                request.address().state(),
                request.address().zipCode()
        );
    }

    private Order buildSampleOrder() {
        var item = new OrderItem("PROD-001", "Produto exemplo", 1, new BigDecimal("99.90"));
        return new Order(
                List.of(item),
                new Address("Rua Exemplo", "São Paulo", "SP", "01000-000"),
                ShippingType.PAC,
                "PIX",
                com.portfolio.pedidos.domain.NotificationType.EMAIL,
                "cliente@email.com"
        );
    }
}
