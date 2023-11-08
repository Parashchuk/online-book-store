package bookstore.dto;

import bookstore.validation.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8, max = 255)
        String password,

        @NotBlank
        @Size(min = 3, max = 255)
        String firstName,

        @NotBlank
        @Size(min = 3, max = 255)
        String lastName) {
}
