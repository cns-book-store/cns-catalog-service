package inc.evil.cnscatalogservice.web;

import inc.evil.cnscatalogservice.domain.Book;
import inc.evil.cnscatalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerComponentTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BookService bookService;

    @Test
    void get() {
        Book book = new Book("1231231231", "Title1", "Author1", "Publisher1", 2020, 9.90);
        var expectedBooks = List.of(book);
        bookService.addBookToCatalog(book);

        webTestClient
                .get()
                .uri("/api/v1/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .value(actualBooks -> {
                    assertThat(actualBooks).hasSameSizeAs(expectedBooks);
                    assertThat(actualBooks).containsExactlyInAnyOrderElementsOf(expectedBooks);
                });
    }

    @Test
    void getByIsbn() {
        var expectedBook = new Book("1231231232", "Title1", "Author1", "Publisher1", 2020, 9.90);
        bookService.addBookToCatalog(expectedBook);

        webTestClient
                .get()
                .uri("/api/v1/books/" + expectedBook.isbn())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook).isEqualTo(expectedBook);
                });
    }

    @Test
    void post() {
        var expectedBook = new Book("1231231233", "Title", "Author", "Publisher", 2020, 9.90);
        webTestClient
                .post()
                .uri("/api/v1/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn())
                            .isEqualTo(expectedBook.isbn());
                });
    }

    @Test
    void delete() {
        var expectedBook = new Book("1231231235", "Title1", "Author1", "Publisher1", 2020, 9.90);
        bookService.addBookToCatalog(expectedBook);

        webTestClient
                .delete()
                .uri("/api/v1/books/" + expectedBook.isbn())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void put() {
        var expectedBook = new Book("1231231234", "Title1", "Author1", "Publisher1", 2020, 9.90);
        bookService.addBookToCatalog(expectedBook);

        var updatedBook = new Book("1231231234", "NewTitle", "NewAuthor", "NewPublisher", 2022, 29.90);

        webTestClient
                .put()
                .uri("/api/v1/books/" + expectedBook.isbn())
                .bodyValue(updatedBook)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook).isEqualTo(updatedBook);
                });
    }
}
