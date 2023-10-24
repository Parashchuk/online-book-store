package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.dto.CreateBookRequestDto;
import bookstore.entity.Book;
import bookstore.mapper.BookMapper;
import bookstore.repository.BookRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepositoryImpl bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findBookById(Long id) {
        return bookRepository.findBookById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book was not found with id: " + id)
        );
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
