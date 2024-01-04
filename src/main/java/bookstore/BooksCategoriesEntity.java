package bookstore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "books_categories", schema = "book-store", catalog = "")
public class BooksCategoriesEntity {
    @Basic
    @Column(name = "book_id")
    private long bookId;
    @Basic
    @Column(name = "category_id")
    private long categoryId;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksCategoriesEntity that = (BooksCategoriesEntity) o;
        return bookId == that.bookId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, categoryId);
    }
}
