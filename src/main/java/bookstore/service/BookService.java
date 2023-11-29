package bookstore.service;

import bookstore.dto.book.BookResponseDto;
import bookstore.dto.book.BookResponseWithoutCategoriesDto;
import bookstore.dto.book.CreateBookRequestDto;
import bookstore.dto.book.UpdateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto book);

    List<BookResponseWithoutCategoriesDto> findAllByCategoryId(Long id, Pageable pageable);

    BookResponseDto findById(Long id);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto updateById(Long id, UpdateBookRequestDto updateBookRequestDto);

    void deleteById(Long id);
}
