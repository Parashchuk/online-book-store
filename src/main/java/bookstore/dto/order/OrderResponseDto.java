package bookstore.dto.order;

import bookstore.entity.order.OrderItem;
import bookstore.entity.order.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderResponseDto(
        Long id,
        Long userId,
        Set<OrderItem> orderItems,
        LocalDateTime orderDate,
        BigDecimal total,
        OrderStatus status) {
}