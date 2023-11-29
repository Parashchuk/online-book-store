package bookstore.service.impl;

import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.entity.book.Book;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;
import bookstore.mapper.CartItemMapper;
import bookstore.repository.BookRepository;
import bookstore.repository.CartItemRepository;
import bookstore.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public void saveCartItem(CreateCartItemRequestDto addToCartDto, Cart cart) {
        Book book = bookRepository.findById(addToCartDto.book_id()).orElseThrow(
                () -> new EntityNotFoundException("Book was not found with id: "
                        + addToCartDto.book_id())
        );

        cartItemRepository.findCartItemByBookAndCart(book, cart).ifPresentOrElse(
                (cartItem) -> {
                    cartItem.setQuantity(cartItem.getQuantity() + addToCartDto.quantity());
                    cartItemRepository.save(cartItem);
                },
                () -> {
                    CartItem cartItem = cartItemMapper.toModel(cart, book, addToCartDto.quantity());
                    cartItemRepository.save(cartItem);
                }
        );
    }

    @Override
    @Transactional
    public CartItem updateCartItemById(Long id, int quantity, Cart cart) {
        CartItem cartItem = cartItemRepository.findByIdAndCart(id, cart).orElseThrow(
                () -> new EntityNotFoundException("CartItem was not found with id: " + id)
        );
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItemById(Long id, Cart cart) {
        cartItemRepository.findByIdAndCart(id, cart).orElseThrow(
                () -> new EntityNotFoundException("CartItem was not found with id: " + id)
        );
        cartItemRepository.deleteById(id);
    }
}
