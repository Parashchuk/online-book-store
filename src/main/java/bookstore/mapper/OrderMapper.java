package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.order.OrderCreateDto;
import bookstore.dto.order.OrderResponseDto;
import bookstore.entity.order.Order;
import bookstore.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfig.class)
@Component
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shippingAddress", source = "createDto.shippingAddress")
    Order toModel(User user, OrderCreateDto createDto);

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);
}
