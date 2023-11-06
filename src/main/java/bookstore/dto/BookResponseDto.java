package bookstore.dto;

import java.math.BigDecimal;

public record BookResponseDto(
        long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage) {
}
