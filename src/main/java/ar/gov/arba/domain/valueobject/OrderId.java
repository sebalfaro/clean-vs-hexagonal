package ar.gov.arba.domain.valueobject;

import java.util.UUID;

public class OrderId {
    private final String value;

    public OrderId(String value) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("OrderId no puede ser nulo");
        this.value = value;
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID().toString());
    }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }
}
