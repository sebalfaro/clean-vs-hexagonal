package ar.gov.arba.application.port.input;

import java.math.BigDecimal;

public class CreateOrderResult {
    private final String orderId;
    private final BigDecimal total;

    public CreateOrderResult(String orderId, BigDecimal total) {
        this.orderId = orderId;
        this.total = total;
    }

    public String getOrderId() { return orderId; }
    public BigDecimal getTotal() { return total; }
}
