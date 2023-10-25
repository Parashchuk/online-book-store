package bookstore.dto;

import bookstore.validation.Isbn;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateBookRequestDto(
        @NotNull
        @Size(min = 2, max = 50)
        String title,

        @NotNull
        @Size(min = 2, max = 30)
        String author,

        @NotNull
        @Isbn
        String isbn,

        @DecimalMin("0.01")
        BigDecimal price,

        @Max(1024)
        String description,

        @Max(1024)
        String coverImage) {
}
