package bookstore.repository;

import bookstore.entity.Book;
import bookstore.exception.DataProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory factory;

    @Override
    public Book save(Book book) {
        try {
            factory.inTransaction(e -> e.persist(book));
        } catch (Exception e) {
            throw new DataProcessingException("Can't create a book, ", e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try {
            return factory.fromSession(e -> e.createQuery("from Book", Book.class)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Can't find all books from DB, ", e);
        }
    }
}
