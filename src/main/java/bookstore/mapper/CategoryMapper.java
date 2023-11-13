package bookstore.mapper;

import bookstore.dto.category.CategoryDto;
import bookstore.entity.category.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface CategoryMapper {
    Category toModel(CategoryDto requestDto);

    CategoryDto toDto(Category category);

    void updateCategory(CategoryDto categoryDto, @MappingTarget Category category);
}
