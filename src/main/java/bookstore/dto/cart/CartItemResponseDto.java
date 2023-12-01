package bookstore.dto.cart;

public record CartItemResponseDto(
        Long id,
        Long book_id,
        String book_title,
        int quantity) {
}
