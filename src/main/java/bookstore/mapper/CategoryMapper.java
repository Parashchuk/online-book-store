package bookstore.mapper;

import bookstore.config.MapperConfig;
import bookstore.dto.category.CategoryResponseDto;
import bookstore.dto.category.CreateCategoryRequestDto;
import bookstore.entity.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toModel(CreateCategoryRequestDto requestDto);

    CategoryResponseDto toDto(Category category);

    void update(CreateCategoryRequestDto createCategoryRequestDto,
                @MappingTarget Category category);
}
