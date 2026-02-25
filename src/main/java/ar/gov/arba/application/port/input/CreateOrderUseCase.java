package ar.gov.arba.application.port.input;

public interface CreateOrderUseCase {
    CreateOrderResult execute(CreateOrderCommand command);
}
