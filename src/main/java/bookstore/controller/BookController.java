package bookstore.controller;

import bookstore.dto.book.BookResponseDto;
import bookstore.dto.book.BookResponseWithoutCategoriesDto;
import bookstore.dto.book.CreateBookRequestDto;
import bookstore.dto.book.UpdateBookRequestDto;
import bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book", description = "Endpoints for managing books")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Create a new book")
    public BookResponseDto createBook(@RequestBody @Valid CreateBookRequestDto book) {
        return bookService.save(book);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get list of all books from DB")
    public List<BookResponseDto> getAll(
            @ParameterObject Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{categoryId}/books")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get list of all books by its categories from DB")
    public List<BookResponseWithoutCategoriesDto> getBooksByCategoryId(
            @PathVariable @Positive Long categoryId,
            @ParameterObject Pageable pageable
    ) {
        return bookService.findAllByCategoryId(categoryId, pageable);
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get a book by its id")
    public BookResponseDto getBookById(@PathVariable @Positive Long bookId) {
        return bookService.findById(bookId);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Update a book by its ID")
    public BookResponseDto updateBookById(
            @PathVariable @Positive Long bookId,
            @Valid @RequestBody UpdateBookRequestDto updateBookRequestDto
    ) {
        return bookService.updateById(bookId, updateBookRequestDto);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Delete a book by its ID")
    public void deleteBookById(@PathVariable @Positive Long bookId) {
        bookService.deleteById(bookId);
    }
}
