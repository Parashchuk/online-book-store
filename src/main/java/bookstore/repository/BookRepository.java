package bookstore.repository;

import bookstore.entity.book.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = "categories")
    List<Book> findAllByCategoriesId(Long id);

    @EntityGraph(attributePaths = "categories")
    Optional<Book> findBookById(Long id);
}
