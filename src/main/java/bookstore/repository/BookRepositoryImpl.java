package bookstore.repository;

import bookstore.entity.Book;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory factory;

    @Autowired
    public BookRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Book save(Book book) {
        factory.inTransaction(e -> e.persist(book));
        return book;
    }

    @Override
    public List<Book> findAll() {
        return factory.fromSession(e -> e.createQuery("from Book", Book.class).getResultList());
    }
}
