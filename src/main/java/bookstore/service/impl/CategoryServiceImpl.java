package bookstore.service.impl;

import bookstore.dto.category.CategoryResponseDto;
import bookstore.dto.category.CreateCategoryRequestDto;
import bookstore.entity.category.Category;
import bookstore.exception.DuplicateValueException;
import bookstore.mapper.CategoryMapper;
import bookstore.repository.CategoryRepository;
import bookstore.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDto save(CreateCategoryRequestDto createCategoryRequestDto) {
        try {
            return categoryMapper.toDto(
                    categoryRepository.save(categoryMapper.toModel(createCategoryRequestDto))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateValueException(
                    "The category name already exists: " + createCategoryRequestDto.name()
            );
        }

    }

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category was not found with id: " + id)
                );
    }

    @Override
    public CategoryResponseDto updateById(
            Long id,
            CreateCategoryRequestDto createCategoryRequestDto
    ) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Category wasn't updated, because category with id: " + id
                                + " doesn't exist"
                )
        );
        categoryMapper.update(createCategoryRequestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
