package bookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequestDto(
        @NotBlank
        @Size(min = 3, max = 20)
        String name,

        String description) {
}
