package com.polarbookshop.catalogservice02.demo;

import com.polarbookshop.catalogservice02.domain.Book;
import com.polarbookshop.catalogservice02.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookDataTest() {
        var book1 = new Book("1234567891", "Nothern Lights",
                "Lyra Silverstar", 9.90);

        var book2 = new Book("1234567892", "Polar Journey",
                "Iorek Larsson", 12.90);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}