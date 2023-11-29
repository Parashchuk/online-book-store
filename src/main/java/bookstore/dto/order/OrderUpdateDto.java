package bookstore.dto.order;

import bookstore.entity.order.Order.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateDto(
        @NotNull
        OrderStatus status
) {
}
