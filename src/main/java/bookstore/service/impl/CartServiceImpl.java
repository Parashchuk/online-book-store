package bookstore.service.impl;

import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CartResponseDto;
import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.dto.cart.UpdateCartItemRequestDto;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;
import bookstore.mapper.CartItemMapper;
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
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public CartResponseDto addCartItem(CreateCartItemRequestDto addToCartDto, String username) {
        Cart cart = cartRepository.getCartByUserEmail(username);
        CartItem cartItem = cartItemService.manage(addToCartDto, cart);
        cart.getCartItems().add(cartItem);
        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto getByUsername(String username) {
        return cartMapper.toDto(cartRepository.getCartByUserEmail(username));
    }

    @Override
    @Transactional
    public CartItemResponseDto updateById(
            Long id,
            UpdateCartItemRequestDto updateCartItemRequestDto,
            String username
    ) {
        Cart cart = cartRepository.getCartByUserEmail(username);
        return cartItemMapper.toDto(
                cartItemService.updateById(
                        id, updateCartItemRequestDto.quantity(), cart
                )
        );
    }

    @Override
    public void deleteById(Long id, String username) {
        Cart cart = cartRepository.getCartByUserEmail(username);
        cartItemService.deleteById(id, cart);
    }
}
