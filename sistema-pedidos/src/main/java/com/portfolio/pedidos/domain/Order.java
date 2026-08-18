package com.portfolio.pedidos.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id;
    private final List<OrderItem> items;
    private final Address address;
    private final ShippingType shippingType;
    private final String paymentMethod;
    private final NotificationType notificationType;
    private final String customerEmail;
    private OrderStatus status;
    private BigDecimal shippingCost;
    private BigDecimal total;
    private String rejectionReason;
    private final LocalDateTime createdAt;

    public Order(
            List<OrderItem> items,
            Address address,
            ShippingType shippingType,
            String paymentMethod,
            NotificationType notificationType,
            String customerEmail
    ) {
        this.id = UUID.randomUUID().toString();
        this.items = List.copyOf(items);
        this.address = address;
        this.shippingType = shippingType;
        this.paymentMethod = paymentMethod;
        this.notificationType = notificationType;
        this.customerEmail = customerEmail;
        this.status = OrderStatus.PENDENTE;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Address getAddress() {
        return address;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal itemsSubtotal() {
        return items.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
