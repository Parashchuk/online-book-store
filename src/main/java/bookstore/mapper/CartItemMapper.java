package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.entity.book.Book;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "id", ignore = true)
    CartItem toModel(Cart cart, Book book, int quantity);
}
