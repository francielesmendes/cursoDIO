package com.portfolio.pedidos.pattern.strategy;

import com.portfolio.pedidos.domain.ShippingType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ShippingStrategyFactory {

    private final Map<ShippingType, ShippingStrategy> strategies;

    public ShippingStrategyFactory(
            PacShippingStrategy pac,
            SedexShippingStrategy sedex,
            PickupShippingStrategy pickup
    ) {
        strategies = new EnumMap<>(ShippingType.class);
        strategies.put(ShippingType.PAC, pac);
        strategies.put(ShippingType.SEDEX, sedex);
        strategies.put(ShippingType.RETIRADA, pickup);
    }

    public ShippingStrategy getStrategy(ShippingType type) {
        ShippingStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de frete não suportado: " + type);
        }
        return strategy;
    }
}
