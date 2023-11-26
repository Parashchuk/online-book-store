package bookstore.service.impl;

import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CartRequestAddDto;
import bookstore.dto.cart.CartRequestUpdateDto;
import bookstore.dto.cart.CartResponseDto;
import bookstore.entity.cart.Cart;
import bookstore.mapper.CartMapper;
import bookstore.repository.CartRepository;
import bookstore.service.CartItemService;
import bookstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartResponseDto addCartItem(CartRequestAddDto addToCartDto, String username) {
        Cart cart = cartRepository.getCartByUserEmail(username);
        cartItemService.saveCartItem(addToCartDto, cart);
        return getCart(username);
    }

    @Override
    public CartResponseDto getCart(String username) {
        return cartMapper.toDto(cartRepository.getCartByUserEmail(username));
    }

    @Override
    @Transactional
    public CartItemResponseDto updateCartItem(
            Long id,
            CartRequestUpdateDto cartRequestUpdateDto,
            String username
    ) {
        return cartMapper.cartItemToResponseDto(
                cartItemService.updateCartItemById(id, cartRequestUpdateDto.quantity())
        );
    }

    @Override
    public void deleteCartItem(Long id, String username) {
        cartItemService.deleteCartItemById(id);
    }
}
