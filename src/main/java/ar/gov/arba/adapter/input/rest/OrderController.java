package ar.gov.arba.adapter.input.rest;

import ar.gov.arba.application.port.input.CreateOrderCommand;
import ar.gov.arba.application.port.input.CreateOrderResult;
import ar.gov.arba.application.port.input.CreateOrderUseCase;
import ar.gov.arba.application.port.input.OrderItemCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> create(@RequestBody CreateOrderRequest request) {
        List<OrderItemCommand> itemCommands = request.getItems().stream()
            .map(i -> new OrderItemCommand(i.getProductId(), i.getQuantity(), i.getUnitPrice()))
            .toList();

        CreateOrderCommand command = new CreateOrderCommand(request.getCustomerId(), itemCommands);
        CreateOrderResult result = createOrderUseCase.execute(command);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new CreateOrderResponse(result.getOrderId(), result.getTotal()));
    }
}
