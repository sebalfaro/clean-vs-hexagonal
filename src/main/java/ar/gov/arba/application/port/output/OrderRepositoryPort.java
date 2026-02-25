package ar.gov.arba.application.port.output;

import ar.gov.arba.domain.entity.Order;
import ar.gov.arba.domain.valueobject.OrderId;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(OrderId id);
}
