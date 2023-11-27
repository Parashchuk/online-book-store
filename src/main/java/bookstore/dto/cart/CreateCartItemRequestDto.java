package bookstore.dto.cart;

import jakarta.validation.constraints.Positive;

public record CreateCartItemRequestDto(
        @Positive
        Long book_id,
        @Positive
        int quantity) {
}