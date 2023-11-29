package bookstore.service;

import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.dto.cart.UpdateCartItemRequestDto;
import bookstore.dto.cart.CartResponseDto;

public interface CartService {
    CartResponseDto addCartItem(CreateCartItemRequestDto addToCartDto, String username);

    CartResponseDto getCart(String username);

    CartItemResponseDto updateCartItem(
            Long id,
            UpdateCartItemRequestDto updateCartItemRequestDto,
            String username
    );

    void deleteCartItem(Long id, String username);
}
