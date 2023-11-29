package bookstore.repository;

import bookstore.entity.book.Book;
import bookstore.entity.cart.Cart;
import bookstore.entity.cart.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemByBookAndCart(Book book, Cart cart);

    Optional<CartItem> findByIdAndCart(Long id, Cart cart);
}
