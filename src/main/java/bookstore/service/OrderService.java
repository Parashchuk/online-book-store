package bookstore.service;

import bookstore.dto.order.CreateOrderRequestDto;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.dto.order.UpdateOrderRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto save(CreateOrderRequestDto createDto, String username);

    List<OrderResponseDto> getAll(String username, Pageable pageable);

    OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto updateDto);

    OrderItemResponseDto getOrderItem(Long orderId, Long orderItemId, String username);

    List<OrderItemResponseDto> getAllOrderItems(Long id, String username);
}
