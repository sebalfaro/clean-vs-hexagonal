package ar.gov.arba.adapter.output.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    private String id;
    private String customerId;
    private String status;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItemEmbedded> items;

    public OrderJpaEntity() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItemEmbedded> getItems() { return items; }
    public void setItems(List<OrderItemEmbedded> items) { this.items = items; }

    @Embeddable
    public static class OrderItemEmbedded {
        private String productId;
        private int quantity;
        private BigDecimal unitPrice;

        public OrderItemEmbedded() {}

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public BigDecimal getUnitPrice() { return unitPrice; }
        public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    }
}
