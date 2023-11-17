package com.polarbookshop.catalogservice02;

import com.polarbookshop.catalogservice02.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogService02ApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	@Test
	void whenPostRequestThenBookCreated() {
		// var expectedBook = new Book("1234567890", "Title", "Author", 9.90);
		var expectedBook = Book.of("1234567890", "Nothern Lights", "Lyra Silverstar", 9.90, "Manning Publisher");

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook -> {
				    assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
	}

}
