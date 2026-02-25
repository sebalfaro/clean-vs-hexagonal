package ar.gov.arba.adapter.input.rest;

import java.math.BigDecimal;

public class CreateOrderResponse {
    private final String orderId;
    private final BigDecimal total;

    public CreateOrderResponse(String orderId, BigDecimal total) {
        this.orderId = orderId;
        this.total = total;
    }

    public String getOrderId() { return orderId; }
    public BigDecimal getTotal() { return total; }
}
