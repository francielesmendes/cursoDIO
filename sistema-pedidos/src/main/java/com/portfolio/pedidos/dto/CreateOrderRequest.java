package com.portfolio.pedidos.dto;

import com.portfolio.pedidos.domain.NotificationType;
import com.portfolio.pedidos.domain.ShippingType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(
        @NotEmpty(message = "O pedido deve conter ao menos um item")
        @Valid
        List<OrderItemRequest> items,

        @Valid
        AddressRequest address,

        @NotNull(message = "Tipo de frete é obrigatório")
        ShippingType shippingType,

        @NotBlank(message = "Forma de pagamento é obrigatória")
        String paymentMethod,

        @NotNull(message = "Tipo de notificação é obrigatório")
        NotificationType notificationType,

        @NotBlank(message = "E-mail do cliente é obrigatório")
        @Email(message = "E-mail inválido")
        String customerEmail
) {
    public record OrderItemRequest(
            @NotBlank String productId,
            @NotBlank String productName,
            @Positive int quantity,
            @NotNull @Positive BigDecimal unitPrice
    ) {
    }

    public record AddressRequest(
            String street,
            String city,
            String state,
            String zipCode
    ) {
    }
}
