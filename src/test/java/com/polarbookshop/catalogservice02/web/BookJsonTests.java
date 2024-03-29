package com.polarbookshop.catalogservice02.web;


import com.polarbookshop.catalogservice02.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        // var book = new Book("1234567890", "Title", "Author", 9.90);
        var book = Book.of("1234567890", "Nothern Lights", "Lyra Silverstar", 9.90, "Longhorn Publishers");

        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
                .isEqualTo(book.publisher());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());

    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
      {
        "isbn": "1234567890",
        "title": "Nothern Lights",
        "author": "Lyra Silverstar",
        "price": 9.90,
        "publisher": "Longhorn Publishers"
      }
      """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Nothern Lights", "Lyra Silverstar", 9.90, "Longhorn Publishers"));
    }
}