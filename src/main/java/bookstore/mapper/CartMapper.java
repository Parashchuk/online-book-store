package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CartResponseDto;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;
import bookstore.entity.user.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class)
public interface CartMapper {
    @Mapping(ignore = true, target = "id")
    Cart toModel(User user);

    @Mapping(source = "user.id", target = "user_id")
    CartResponseDto toDto(Cart cart);

    @Mapping(source = "book.id", target = "book_id")
    @Mapping(source = "book.title", target = "book_title")
    CartItemResponseDto cartItemToResponseDto(CartItem cartItem);
}
