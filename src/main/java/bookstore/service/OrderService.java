package bookstore.service;

import bookstore.dto.order.OrderCreateDto;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.dto.order.OrderUpdateDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateDto createDto, String username);

    List<OrderResponseDto> getAllOrders(String username, Pageable pageable);

    OrderResponseDto updateOrderStatus(Long id, OrderUpdateDto updateDto);

    OrderItemResponseDto getOrderItem(Long orderId, Long orderItemId, String username);

    List<OrderItemResponseDto> getAllOrderItems(Long id, String username);
}
