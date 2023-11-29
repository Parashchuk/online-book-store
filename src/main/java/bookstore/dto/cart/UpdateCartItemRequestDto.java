package bookstore.dto.cart;

import jakarta.validation.constraints.Min;

public record UpdateCartItemRequestDto(
        @Min(value = 1)
        int quantity
){
}
