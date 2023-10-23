package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    List<BookDto> findAll();

    BookDto updateBookById(Long id, CreateBookRequestDto requestDto);

    void deleteBookById(Long id);
}
