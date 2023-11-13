package bookstore.dto.book;

import bookstore.validation.Isbn;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record BookRequestDto(
        @NotBlank
        @Size(min = 2, max = 50)
        String title,

        @NotBlank
        @Size(min = 2, max = 30)
        String author,

        @Isbn
        String isbn,

        @DecimalMin("0.01")
        BigDecimal price,

        @Max(1024)
        String description,

        @Max(1024)
        String coverImage) {
}
