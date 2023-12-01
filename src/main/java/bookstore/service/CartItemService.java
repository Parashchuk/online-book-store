package bookstore.service;

import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;

public interface CartItemService {
    void saveCartItem(CreateCartItemRequestDto addToCartDto, Cart cart);

    CartItem updateCartItemById(Long id, int quantity, Cart cart);

    void deleteCartItemById(Long id, Cart cart);
}
