package bookstore.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public record CreateBookRequestDto (long id,
                       String title,
                       String author,
                       String isbn,
                       BigDecimal price,
                       String description,
                       String coverImage) {
}
