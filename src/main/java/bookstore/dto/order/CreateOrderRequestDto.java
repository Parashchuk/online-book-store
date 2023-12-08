package bookstore.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateOrderRequestDto(
        @Size(min = 5)
        @NotBlank
        String shippingAddress) {
}
