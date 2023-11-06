package bookstore.service;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(BookRequestDto book);

    BookResponseDto findById(Long id);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto updateById(Long id, BookRequestDto requestDto);

    void deleteById(Long id);
}
