package ar.gov.arba.application.port.input;

import java.math.BigDecimal;

public class OrderItemCommand {
    private final String productId;
    private final int quantity;
    private final BigDecimal unitPrice;

    public OrderItemCommand(String productId, int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
}
