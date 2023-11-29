package bookstore.dto.user;

import bookstore.validation.Email;
import bookstore.validation.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordsMatch(first = "password", second = "repeatPassword")
public record RegisterRequestDto(
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8, max = 72)
        String password,

        @NotBlank
        @Size(min = 8, max = 72)
        String repeatPassword,

        @NotBlank
        @Size(min = 3, max = 255)
        String firstName,

        @NotBlank
        @Size(min = 3, max = 255)
        String lastName,

        String shippingAddress) {
}
