package bookstore.controller;

import bookstore.dto.order.OrderCreateDto;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.dto.order.OrderUpdateDto;
import bookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Create empty order")
    OrderResponseDto createOrder(
            @RequestBody @Valid OrderCreateDto createDto,
            Authentication authentication
    ) {
        return orderService.createOrder(createDto, authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Retrieve all orders with its items")
    List<OrderResponseDto> getAllOrders(Authentication authentication, Pageable pageable) {
        return orderService.getAllOrders(authentication.getName(), pageable);
    }

    @GetMapping("/{orderId}/items/{orderItemId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Retrieve one order item by id")
    OrderItemResponseDto getOrderItem(
            @PathVariable @Positive Long orderId,
            @PathVariable @Positive Long orderItemId,
            Authentication authentication
    ) {
        return orderService.getOrderItem(orderId, orderItemId, authentication.getName());
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Retrieve all order items from certain order")
    List<OrderItemResponseDto> getAllOrderItems(
            @PathVariable @Positive Long id,
            Authentication authentication
    ) {
        return orderService.getAllOrderItems(id, authentication.getName());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Retrieve all order items from certain order")
    OrderResponseDto updateOrderStatus(
            @PathVariable @Positive Long id,
            @RequestBody @Valid OrderUpdateDto updateDto
    ) {
        return orderService.updateOrderStatus(id, updateDto);
    }
}
