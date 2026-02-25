package ar.gov.arba.application.usecase;

import ar.gov.arba.application.port.input.CreateOrderCommand;
import ar.gov.arba.application.port.input.CreateOrderResult;
import ar.gov.arba.application.port.input.CreateOrderUseCase;
import ar.gov.arba.application.port.output.NotificationPort;
import ar.gov.arba.application.port.output.OrderRepositoryPort;
import ar.gov.arba.domain.entity.Order;
import ar.gov.arba.domain.entity.OrderItem;
import ar.gov.arba.domain.valueobject.OrderId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepository;
    private final NotificationPort notification;

    public CreateOrderUseCaseImpl(OrderRepositoryPort orderRepository, NotificationPort notification) {
        this.orderRepository = orderRepository;
        this.notification = notification;
    }

    @Override
    public CreateOrderResult execute(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
            .map(i -> new OrderItem(i.getProductId(), i.getQuantity(), i.getUnitPrice()))
            .toList();

        Order order = new Order(OrderId.generate(), command.getCustomerId(), items);
        Order savedOrder = orderRepository.save(order);
        notification.orderCreated(savedOrder);

        return new CreateOrderResult(
            savedOrder.getId().getValue(),
            savedOrder.calculateTotal().getAmount()
        );
    }
}
