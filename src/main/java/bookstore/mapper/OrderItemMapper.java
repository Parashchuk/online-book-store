package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.entity.cart.CartItem;
import bookstore.entity.order.Order;
import bookstore.entity.order.OrderItem;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    OrderItem cartItemToOrderModel(CartItem cartItem, Order order, BigDecimal price);
}
