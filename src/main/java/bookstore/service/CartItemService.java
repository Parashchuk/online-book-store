package bookstore.service;

import bookstore.dto.cart.CartRequestAddDto;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;

public interface CartItemService {
    void saveCartItem(CartRequestAddDto addToCartDto, Cart cart);

    CartItem updateCartItemById(Long id, int quantity);

    void deleteCartItemById(Long id);
}
