package ar.gov.arba.domain.entity;

import ar.gov.arba.domain.valueobject.Money;
import java.math.BigDecimal;

public class OrderItem {
    private final String productId;
    private final int quantity;
    private final Money unitPrice;

    public OrderItem(String productId, int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = new Money(unitPrice);
    }

    public Money subtotal() {
        return new Money(unitPrice.getAmount().multiply(BigDecimal.valueOf(quantity)));
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public Money getUnitPrice() { return unitPrice; }
}
