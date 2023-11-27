package bookstore.service;

import bookstore.dto.category.CategoryCreateDto;
import bookstore.dto.category.CategoryResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryCreateDto categoryCreateDto);

    CategoryResponseDto updateById(Long id, CategoryCreateDto categoryCreateDto);

    void deleteById(Long id);
}
