package inc.evil.cnscatalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book(
        @Id
        Long id,
        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,

        @NotBlank(
                message = "The book title must be defined."
        )
        String title,
        @NotBlank(message = "The book author must be defined.")
        String author,

        String publisher,

        Integer publicationYear,
        @NotNull(message = "The book price must be defined.")
        @Positive(
                message = "The book price must be greater than zero."
        )
        Double price,

        @Version
        int version,

        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Book of(String isbn, String title, String author, String publisher, Integer publicationYear, Double price) {
        return new Book(null, isbn, title, author, publisher, publicationYear, price, 0, null, null);
    }
}
