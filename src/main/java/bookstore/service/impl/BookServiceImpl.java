package bookstore.service.impl;

import bookstore.dto.book.BookRequestDto;
import bookstore.dto.book.BookResponseDto;
import bookstore.entity.book.Book;
import bookstore.mapper.BookMapper;
import bookstore.repository.BookRepository;
import bookstore.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookResponseDto> findAllByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoriesId(id).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book was not found with id: " + id)
                );
    }

    @Override
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto updateById(Long id, BookRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Book wasn't updated, because book with id: " + id + " doesn't exist"
                )
        );
        bookMapper.updateBook(requestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
