package bookstore.service;

import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;

public interface CartItemService {
    CartItem manage(CreateCartItemRequestDto addToCartDto, Cart cart);

    CartItem updateById(Long id, int quantity, Cart cart);

    void deleteById(Long id, Cart cart);
}
