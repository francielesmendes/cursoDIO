package com.portfolio.pedidos.config;

import com.portfolio.pedidos.pattern.singleton.ApplicationSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSettingsBootstrap {

    public ApplicationSettingsBootstrap(
            @Value("${app.currency}") String currency,
            @Value("${app.default-shipping}") String defaultShipping
    ) {
        ApplicationSettings.INSTANCE.configure(currency, defaultShipping);
    }
}
