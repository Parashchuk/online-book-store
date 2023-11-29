package bookstore.service.impl;

import bookstore.dto.order.OrderCreateDto;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.dto.order.OrderUpdateDto;
import bookstore.entity.order.Order;
import bookstore.entity.user.User;
import bookstore.mapper.OrderItemMapper;
import bookstore.mapper.OrderMapper;
import bookstore.repository.OrderItemRepository;
import bookstore.repository.OrderRepository;
import bookstore.repository.UserRepository;
import bookstore.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderCreateDto createDto, String username) {
        return orderMapper.toDto(
                orderRepository.save(
                        orderMapper.toModel(
                            userRepository.getUserByEmail(username), createDto
                        )
                )
        );
    }

    @Override
    public List<OrderResponseDto> getAllOrders(String username, Pageable pageable) {
        User user = userRepository.getUserByEmail(username);
        return orderRepository.findAllByUser(user, pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderStatus(Long id, OrderUpdateDto updateDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order doesn't exists with id: " + id)
        );
        order.setStatus(updateDto.status());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderId, Long orderItemId, String username) {
        User user = userRepository.getUserByEmail(username);
        Order order = orderRepository.findByIdAndUser(orderId, user);
        return orderItemMapper.toDto(orderItemRepository.findByIdAndOrder(orderItemId, order)
                .orElseThrow(
                        () -> new EntityNotFoundException("Invalid order")
                ));
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItems(Long id, String username) {
        User user = userRepository.getUserByEmail(username);
        Order order = orderRepository.findByIdAndUser(id, user);
        return orderItemRepository.findAllByOrderId(order.getId()).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }
}
