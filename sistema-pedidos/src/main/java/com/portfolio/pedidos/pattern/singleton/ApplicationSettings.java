package com.portfolio.pedidos.pattern.singleton;

/**
 * Singleton via enum (Effective Java). Garante instância única e thread-safe.
 */
public enum ApplicationSettings {

    INSTANCE;

    private String currency = "BRL";
    private String defaultShipping = "PAC";

    public String getCurrency() {
        return currency;
    }

    public String getDefaultShipping() {
        return defaultShipping;
    }

    public void configure(String currency, String defaultShipping) {
        this.currency = currency;
        this.defaultShipping = defaultShipping;
    }
}
