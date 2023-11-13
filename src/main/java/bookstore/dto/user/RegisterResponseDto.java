package bookstore.dto.user;

public record RegisterResponseDto(
        String email,
        String firstName,
        String lastName) {
}
