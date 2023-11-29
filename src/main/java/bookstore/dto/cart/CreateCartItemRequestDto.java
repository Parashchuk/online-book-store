package bookstore.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequestDto(
        @Positive
        Long book_id,

        @Min(value = 1)
        int quantity) {
}
