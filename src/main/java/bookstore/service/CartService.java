package bookstore.service;

import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CartResponseDto;
import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.dto.cart.UpdateCartItemRequestDto;

public interface CartService {
    CartResponseDto addCartItem(CreateCartItemRequestDto addToCartDto, String username);

    CartResponseDto getByUsername(String username);

    CartItemResponseDto updateById(
            Long id,
            UpdateCartItemRequestDto updateCartItemRequestDto,
            String username
    );

    void deleteById(Long id, String username);
}
