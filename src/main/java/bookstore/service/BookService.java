package bookstore.service;

import bookstore.dto.book.BookRequestDto;
import bookstore.dto.book.BookResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(BookRequestDto book);

    List<BookResponseDto> findAllByCategoryId(Long id, Pageable pageable);

    BookResponseDto findById(Long id);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto updateById(Long id, BookRequestDto requestDto);

    void deleteById(Long id);
}
