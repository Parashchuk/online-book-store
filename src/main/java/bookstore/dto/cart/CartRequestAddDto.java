package bookstore.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record CartRequestAddDto(
        @Positive
        Long book_id,

        @Min(value = 1)
        int quantity) {
}
