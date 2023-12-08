package bookstore.repository;

import bookstore.entity.order.Order;
import bookstore.entity.order.OrderItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long id);

    Optional<OrderItem> findByIdAndOrder(Long id, Order order);
}
