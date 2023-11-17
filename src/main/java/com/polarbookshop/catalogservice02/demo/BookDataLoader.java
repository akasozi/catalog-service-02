package com.polarbookshop.catalogservice02.demo;

import com.polarbookshop.catalogservice02.domain.Book;
import com.polarbookshop.catalogservice02.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookDataTest() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Nothern Lights",
                "Lyra Silverstar", 9.90, "Longhorn publishers");

        var book2 = Book.of("1234567892", "Polar Journey",
                "Iorek Larsson", 12.90, "Longhorn publishers");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
