package bookstore.mapper;

import bookstore.dto.book.BookCreateDto;
import bookstore.dto.book.BookResponseDto;
import bookstore.dto.book.BookResponseWithoutCategoriesDto;
import bookstore.entity.book.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface BookMapper {
    BookResponseWithoutCategoriesDto toDtoWithoutCategories(Book book);

    BookResponseDto toDto(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toModel(BookCreateDto bookCreateDto);

    @Mapping(target = "categories", ignore = true)
    void updateBook(BookCreateDto dto, @MappingTarget Book book);
}

