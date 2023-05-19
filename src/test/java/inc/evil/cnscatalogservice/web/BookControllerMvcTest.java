package inc.evil.cnscatalogservice.web;

import inc.evil.cnscatalogservice.domain.Book;
import inc.evil.cnscatalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest(BookController.class)
class BookControllerMvcTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookService bookService;

    @Test
    void get() {
        Book book = Book.of("1231231231", "Title1", "Author1", "Publisher1", 2020, 9.90);
        var expectedBooks = List.of(book);
        when(bookService.viewBookList()).thenReturn(expectedBooks);

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
        var expectedBook = Book.of("1231231232", "Title1", "Author1", "Publisher1", 2020, 9.90);
        when(bookService.viewBookDetails(expectedBook.isbn())).thenReturn(expectedBook);

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
        var expectedBook = Book.of("1231231233", "Title", "Author", "Publisher", 2020, 9.90);
        when(bookService.addBookToCatalog(expectedBook)).thenReturn(expectedBook);

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
        var expectedBook = Book.of("1231231235", "Title1", "Author1", "Publisher1", 2020, 9.90);

        webTestClient
                .delete()
                .uri("/api/v1/books/" + expectedBook.isbn())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void put() {
        var expectedBook = Book.of("1231231234", "Title1", "Author1", "Publisher1", 2020, 9.90);
        var updatedBook = Book.of("1231231234", "NewTitle", "NewAuthor", "NewPublisher", 2022, 29.90);
        when(bookService.editBookDetails(expectedBook.isbn(), updatedBook)).thenReturn(updatedBook);

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
