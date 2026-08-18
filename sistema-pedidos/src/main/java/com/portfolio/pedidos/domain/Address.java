package com.portfolio.pedidos.domain;

import java.math.BigDecimal;

public record Address(
        String street,
        String city,
        String state,
        String zipCode
) {
}
