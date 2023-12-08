package bookstore.repository;

import bookstore.entity.order.Order;
import bookstore.entity.user.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByIdAndUser(Long id, User user);

    @EntityGraph(attributePaths = {"orderItems", "orderItems.book"})
    List<Order> findAllByUser(User user, Pageable pageable);
}
