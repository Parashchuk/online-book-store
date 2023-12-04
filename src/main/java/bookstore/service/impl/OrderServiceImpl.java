package bookstore.service.impl;

import bookstore.dto.order.CreateOrderRequestDto;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.dto.order.UpdateOrderRequestDto;
import bookstore.entity.cart.Cart;
import bookstore.entity.order.Order;
import bookstore.entity.order.OrderItem;
import bookstore.entity.user.User;
import bookstore.exception.OrderException;
import bookstore.mapper.OrderItemMapper;
import bookstore.mapper.OrderMapper;
import bookstore.repository.CartRepository;
import bookstore.repository.OrderItemRepository;
import bookstore.repository.OrderRepository;
import bookstore.repository.UserRepository;
import bookstore.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderResponseDto save(CreateOrderRequestDto createDto, String username) {
        Cart cart = cartRepository.getCartByUserEmail(username);
        Order order = orderRepository.save(
                orderMapper.toModel(userRepository.getUserByEmail(username), createDto)
        );
        Set<OrderItem> orderItems =
                cart.getCartItems().stream()
                .map(e -> orderItemMapper.cartItemToOrderModel(e, order, e.getBook().getPrice()))
                .collect(Collectors.toSet());
        if (orderItems.isEmpty()) {
            throw new OrderException(
                    "Can't finish order because nothing was added to the shopping cart");
        } else {
            orderItemRepository.saveAll(orderItems);
        }
        cart.getCartItems().clear();
        cartRepository.save(cart);
        order.setTotal(countTotal(orderItems));
        order.setOrderItems(orderItems);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDto> getAll(String username, Pageable pageable) {
        User user = userRepository.getUserByEmail(username);
        return orderRepository.findAllByUser(user, pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto updateDto) {
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

    private BigDecimal countTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(e -> e.getPrice().multiply(BigDecimal.valueOf(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
