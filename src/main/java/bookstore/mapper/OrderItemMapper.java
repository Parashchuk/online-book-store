package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.order.OrderItemResponseDto;
import bookstore.entity.order.OrderItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfig.class)
@Component
public interface OrderItemMapper {
    OrderItemResponseDto toDto(OrderItem orderItem);
}
