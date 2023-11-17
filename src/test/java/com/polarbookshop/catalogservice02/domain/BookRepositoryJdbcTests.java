package com.polarbookshop.catalogservice02.domain;

import com.polarbookshop.catalogservice02.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenIsbnExisting() {
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "The Effective Engineer", "Edmond Lau", 12.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }

    @Test
    void deleteByIsbnWhenIsbnExisting() {
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "The Effective Engineer", "Edmond Lau", 12.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
        bookRepository.deleteByIsbn(bookIsbn);
        Optional<Book> missingBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(missingBook).isNotPresent();
        assertThat(missingBook).isEqualTo(Optional.empty());
        // assertThat(missingBook).isEqualTo(Optional.empty());

    }

}