package bookstore.service;

import bookstore.dto.book.BookCreateDto;
import bookstore.dto.book.BookResponseDto;
import bookstore.dto.book.BookResponseWithoutCategoriesDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(BookCreateDto book);

    List<BookResponseWithoutCategoriesDto> findAllByCategoryId(Long id, Pageable pageable);

    BookResponseDto findById(Long id);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto updateById(Long id, BookCreateDto requestDto);

    void deleteById(Long id);
}
