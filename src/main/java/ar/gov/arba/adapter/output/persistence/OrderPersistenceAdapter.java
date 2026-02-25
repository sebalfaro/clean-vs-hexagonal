package ar.gov.arba.adapter.output.persistence;

import ar.gov.arba.application.port.output.OrderRepositoryPort;
import ar.gov.arba.domain.entity.Order;
import ar.gov.arba.domain.entity.OrderItem;
import ar.gov.arba.domain.valueobject.Money;
import ar.gov.arba.domain.valueobject.OrderId;
import ar.gov.arba.domain.valueobject.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository jpaRepository;

    public OrderPersistenceAdapter(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = toEntity(order);
        OrderJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return jpaRepository.findById(id.getValue()).map(this::toDomain);
    }

    private OrderJpaEntity toEntity(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.getId().getValue());
        entity.setCustomerId(order.getCustomerId());
        entity.setStatus(order.getStatus().name());
        entity.setItems(order.getItems().stream().map(item -> {
            OrderJpaEntity.OrderItemEmbedded e = new OrderJpaEntity.OrderItemEmbedded();
            e.setProductId(item.getProductId());
            e.setQuantity(item.getQuantity());
            e.setUnitPrice(item.getUnitPrice().getAmount());
            return e;
        }).toList());
        return entity;
    }

    private Order toDomain(OrderJpaEntity entity) {
        List<OrderItem> items = entity.getItems().stream()
            .map(e -> new OrderItem(e.getProductId(), e.getQuantity(), e.getUnitPrice()))
            .toList();
        return new Order(new OrderId(entity.getId()), entity.getCustomerId(), items);
    }
}
