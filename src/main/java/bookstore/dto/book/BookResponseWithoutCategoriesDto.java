package bookstore.dto.book;

import java.math.BigDecimal;

public record BookResponseWithoutCategoriesDto(
        Long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage){
}
