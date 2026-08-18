package com.portfolio.pedidos.dto;

import com.portfolio.pedidos.domain.NotificationType;
import com.portfolio.pedidos.domain.OrderStatus;
import com.portfolio.pedidos.domain.Order;
import com.portfolio.pedidos.domain.ShippingType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        List<ItemResponse> items,
        AddressResponse address,
        ShippingType shippingType,
        String paymentMethod,
        NotificationType notificationType,
        String customerEmail,
        OrderStatus status,
        BigDecimal shippingCost,
        BigDecimal total,
        String rejectionReason,
        LocalDateTime createdAt
) {
    public record ItemResponse(
            String productId,
            String productName,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal
    ) {
    }

    public record AddressResponse(
            String street,
            String city,
            String state,
            String zipCode
    ) {
    }

    public static OrderResponse from(Order order) {
        var items = order.getItems().stream()
                .map(item -> new ItemResponse(
                        item.productId(),
                        item.productName(),
                        item.quantity(),
                        item.unitPrice(),
                        item.subtotal()
                ))
                .toList();

        AddressResponse address = null;
        if (order.getAddress() != null) {
            address = new AddressResponse(
                    order.getAddress().street(),
                    order.getAddress().city(),
                    order.getAddress().state(),
                    order.getAddress().zipCode()
            );
        }

        return new OrderResponse(
                order.getId(),
                items,
                address,
                order.getShippingType(),
                order.getPaymentMethod(),
                order.getNotificationType(),
                order.getCustomerEmail(),
                order.getStatus(),
                order.getShippingCost(),
                order.getTotal(),
                order.getRejectionReason(),
                order.getCreatedAt()
        );
    }
}
