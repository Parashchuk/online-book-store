package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.category.CategoryCreateDto;
import bookstore.dto.category.CategoryResponseDto;
import bookstore.entity.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toModel(CategoryCreateDto requestDto);

    CategoryResponseDto toDto(Category category);

    void updateCategory(CategoryCreateDto categoryCreateDto, @MappingTarget Category category);
}
