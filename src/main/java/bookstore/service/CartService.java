package bookstore.service;

import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CartRequestAddDto;
import bookstore.dto.cart.CartRequestUpdateDto;
import bookstore.dto.cart.CartResponseDto;

public interface CartService {
    CartResponseDto addCartItem(CartRequestAddDto addToCartDto, String username);

    CartResponseDto getCart(String username);

    CartItemResponseDto updateCartItem(
            Long id,
            CartRequestUpdateDto cartRequestUpdateDto,
            String username
    );

    void deleteCartItem(Long id, String username);
}
