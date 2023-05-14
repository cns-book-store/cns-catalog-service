package inc.evil.cnscatalogservice.domain;

public record Book(String isbn, String title, String author, String publisher, Integer publicationYear, Double price) {
}
