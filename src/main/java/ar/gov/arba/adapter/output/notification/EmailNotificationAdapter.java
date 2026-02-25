package ar.gov.arba.adapter.output.notification;

import ar.gov.arba.application.port.output.NotificationPort;
import ar.gov.arba.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationAdapter implements NotificationPort {

    @Override
    public void orderCreated(Order order) {
        // En un caso real aquí iría JavaMailSender o similar
        System.out.printf("📧 Notificación: orden %s creada para cliente %s. Total: %s%n",
            order.getId().getValue(),
            order.getCustomerId(),
            order.calculateTotal().getAmount()
        );
    }
}
