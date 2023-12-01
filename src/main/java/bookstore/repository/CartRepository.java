package bookstore.repository;

import bookstore.entity.cart.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @EntityGraph(attributePaths = {"cartItems", "cartItems.book"})
    Cart getCartByUserEmail(String email);
}
