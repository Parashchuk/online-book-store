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
    public CartItem manage(CreateCartItemRequestDto addToCartDto, Cart cart) {
        Book book = bookRepository.findById(addToCartDto.book_id()).orElseThrow(
                () -> new EntityNotFoundException("Book was not found with id: "
                        + addToCartDto.book_id())
        );

        if (cartItemRepository.findCartItemByBookAndCart(book, cart).isPresent()) {
            CartItem cartItem = cartItemRepository.findCartItemByBookAndCart(book, cart).get();
            cartItem.setQuantity(cartItem.getQuantity() + addToCartDto.quantity());
            return cartItem;
        } else {
            return cartItemMapper.toModel(cart, book, addToCartDto.quantity());
        }
    }

    @Override
    @Transactional
    public CartItem updateById(Long id, int quantity, Cart cart) {
        CartItem cartItem = cartItemRepository.findByIdAndCart(id, cart).orElseThrow(
                () -> new EntityNotFoundException("CartItem was not found with id: " + id)
        );
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id, Cart cart) {
        cartItemRepository.findByIdAndCart(id, cart).orElseThrow(
                () -> new EntityNotFoundException("CartItem was not found with id: " + id)
        );
        cartItemRepository.deleteById(id);
    }
}
