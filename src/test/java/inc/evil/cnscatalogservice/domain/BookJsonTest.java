package inc.evil.cnscatalogservice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTest {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Author", "Publisher", 2020, 9.90);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher").isEqualTo(book.publisher());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.publicationYear").isEqualTo(book.publicationYear());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "publisher": "Publisher",
                "publicationYear": 2020,
                "price": 9.90
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", "Publisher", 2020, 9.90));
    }

}
