package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.order.CreateOrderRequestDto;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.entity.order.Order;
import bookstore.entity.order.OrderItem;
import bookstore.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shippingAddress", source = "createDto.shippingAddress")
    Order toModel(User user, CreateOrderRequestDto createDto);

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "price", source = "book.price")
    OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem);
}
