package bookstore.controller;

import bookstore.dto.book.BookResponseWithoutCategoriesDto;
import bookstore.dto.category.CategoryResponseDto;
import bookstore.dto.category.CreateCategoryRequestDto;
import bookstore.service.BookService;
import bookstore.service.CategoryService;
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

@Tag(name = "Category", description = "Endpoints for managing categories")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get list of all categories from DB")
    public List<CategoryResponseDto> getAll(
            @ParameterObject Pageable pageable
    ) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get a category by its id")
    public CategoryResponseDto getById(@PathVariable @Positive Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Create a new category")
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CreateCategoryRequestDto createCategoryRequestDto
    ) {
        return categoryService.save(createCategoryRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Update a category by its ID")
    public CategoryResponseDto updateCategoryById(
            @PathVariable @Positive Long id,
            @RequestBody @Valid CreateCategoryRequestDto createCategoryRequestDto
    ) {
        return categoryService.updateById(id, createCategoryRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Delete a category by its ID")
    public void deleteById(@PathVariable @Positive Long id) {
        categoryService.deleteById(id);
    }

    @RequestMapping("{categoryId}/books")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get list of all books by its categories from DB")
    public List<BookResponseWithoutCategoriesDto> getBooksByCategoryId(
            @PathVariable @Positive Long categoryId,
            @ParameterObject Pageable pageable
    ) {
        return bookService.findAllByCategoryId(categoryId, pageable);
    }
}
