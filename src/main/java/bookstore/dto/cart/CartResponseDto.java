package bookstore.dto.cart;

import java.util.Set;

public record CartResponseDto(
        Long id,
        Long user_id,
        Set<CartItemResponseDto> cartItems) {
}
