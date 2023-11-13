package bookstore.dto;

import bookstore.validation.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8, max = 72)
        String password) {
}
