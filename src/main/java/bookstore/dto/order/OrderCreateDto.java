package bookstore.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderCreateDto(
        @Min(value = 5)
        @NotBlank
        String shippingAddress) {
}
