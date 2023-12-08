package bookstore.service;

import bookstore.dto.category.CategoryResponseDto;
import bookstore.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryResponseDto updateById(Long id, CreateCategoryRequestDto createCategoryRequestDto);

    void deleteById(Long id);
}
