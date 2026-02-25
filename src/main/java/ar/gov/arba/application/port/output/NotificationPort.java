package ar.gov.arba.application.port.output;

import ar.gov.arba.domain.entity.Order;

public interface NotificationPort {
    void orderCreated(Order order);
}
