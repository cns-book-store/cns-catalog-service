package inc.evil.cnscatalogservice.demo;

import inc.evil.cnscatalogservice.domain.Book;
import inc.evil.cnscatalogservice.domain.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "features.demo-data.enabled", havingValue = "true")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567891", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "Prentice Hall", 2008, 35.99);
        var book2 = new Book("1234567892", "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Addison-Wesley Professional", 1994, 59.99);
        log.debug("Saving books {} {}", book1, book2);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
