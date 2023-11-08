package bookstore.mapper;

import bookstore.dto.BookRequestDto;
import bookstore.dto.BookResponseDto;
import bookstore.entity.book.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface BookMapper {
    BookResponseDto toResponseDto(Book book);

    Book toModel(BookRequestDto bookRequestDto);

    void updateBook(BookRequestDto dto, @MappingTarget Book book);
}
