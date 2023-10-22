package bookstore.repository;

import bookstore.entity.Book;
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
            throw new RuntimeException("Save operation was unsuccessful: ", e);
        }

        return book;
    }

    @Override
    public List<Book> findAll() {
        try {
            return factory.fromSession(e -> e.createQuery("from Book", Book.class)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Save operation was unsuccessful: ", e);
        }

    }
}
