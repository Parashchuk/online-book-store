package bookstore.dto.cart;

import jakarta.validation.constraints.Min;

public record CartRequestUpdateDto(
        @Min(value = 1)
        int quantity
){
}
