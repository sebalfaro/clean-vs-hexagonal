package ar.gov.arba.domain.entity;

import ar.gov.arba.domain.valueobject.Money;
import ar.gov.arba.domain.valueobject.OrderId;
import ar.gov.arba.domain.valueobject.OrderStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final OrderId id;
    private final String customerId;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(OrderId id, String customerId, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Una orden debe tener al menos un item");
        }
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>(items);
        this.status = OrderStatus.PENDING;
    }

    public Money calculateTotal() {
        return items.stream()
            .map(OrderItem::subtotal)
            .reduce(Money.ZERO, Money::add);
    }

    public OrderId getId() { return id; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public OrderStatus getStatus() { return status; }
}
