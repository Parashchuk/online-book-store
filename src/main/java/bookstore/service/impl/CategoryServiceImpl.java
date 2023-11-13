package bookstore.service.impl;

import bookstore.dto.category.CategoryDto;
import bookstore.entity.category.Category;
import bookstore.mapper.CategoryMapper;
import bookstore.repository.CategoryRepository;
import bookstore.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category was not found with id: " + id)
                );
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toModel(categoryDto)));
    }

    @Override
    public CategoryDto updateById(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Category wasn't updated, because category with id: " + id
                                + " doesn't exist"
                )
        );
        categoryMapper.updateCategory(categoryDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
