package inc.evil.cnscatalogservice.domain;

import inc.evil.cnscatalogservice.config.DataConfig;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Tag("integration")
@Import(DataConfig.class)
@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    @Sql("/db-data/books.sql")
    void findByIsbn() {
        Optional<Book> actualBook = bookRepository.findByIsbn("ISBN-1");
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo("ISBN-1");
    }

    @Test
    @Sql("/db-data/books.sql")
    void existsByIsbn() {
        assertThat(bookRepository.existsByIsbn("ISBN-1")).isTrue();
        assertThat(bookRepository.existsByIsbn("ISBN-0")).isFalse();
    }

    @Test
    @Sql("/db-data/books.sql")
    void deleteByIsbn() {
        bookRepository.deleteByIsbn("ISBN-1");
        assertThat(bookRepository.existsByIsbn("ISBN-1")).isFalse();
    }
}
