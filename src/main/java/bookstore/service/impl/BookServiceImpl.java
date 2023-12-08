package bookstore.service.impl;

import bookstore.dto.book.BookResponseDto;
import bookstore.dto.book.BookResponseWithoutCategoriesDto;
import bookstore.dto.book.CreateBookRequestDto;
import bookstore.dto.book.UpdateBookRequestDto;
import bookstore.entity.book.Book;
import bookstore.entity.category.Category;
import bookstore.exception.DuplicateValueException;
import bookstore.mapper.BookMapper;
import bookstore.repository.BookRepository;
import bookstore.repository.CategoryRepository;
import bookstore.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookResponseDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toModel(createBookRequestDto);
        setCategories(book, createBookRequestDto.categories());
        try {
            return bookMapper.toDto(bookRepository.save(book));
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateValueException("The isbn key already exists: "
                    + createBookRequestDto.isbn());
        }
    }

    @Override
    public List<BookResponseWithoutCategoriesDto> findAllByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoriesId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookRepository.findBookById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book was not found with id: " + id)
                );
    }

    @Override
    @Transactional
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public BookResponseDto updateById(Long id, UpdateBookRequestDto updateBookRequestDto) {
        Book book = bookRepository.findBookById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Book wasn't updated, because book with id: " + id + " doesn't exist"
                )
        );
        setCategories(book, updateBookRequestDto.categories());
        bookMapper.update(updateBookRequestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    private void setCategories(Book book, Set<Long> categoryIds) {
        Set<Category> categories = categoryIds.stream()
                .map(id -> categoryRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Category was not found with id: " + id)
                ))
                .collect(Collectors.toSet());
        book.setCategories(categories);
    }
}
