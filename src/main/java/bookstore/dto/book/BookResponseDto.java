package bookstore.dto.book;

import bookstore.dto.category.CategoryResponseDto;
import java.math.BigDecimal;
import java.util.Set;

public record BookResponseDto(
        long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage,
        Set<CategoryResponseDto> categories) {
}
