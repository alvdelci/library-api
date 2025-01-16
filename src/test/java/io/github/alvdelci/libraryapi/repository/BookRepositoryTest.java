package io.github.alvdelci.libraryapi.repository;

import io.github.alvdelci.libraryapi.model.Author;
import io.github.alvdelci.libraryapi.model.Book;
import io.github.alvdelci.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest() {
        Book book = new Book();
        book.setTitle("How to make tofu");
        book.setGender(BookGender.BIOGRAFIA);
        book.setIsbn("12343-4234");
        book.setPublicationDate(LocalDate.of(2000, 11, 9));

        Author author = authorRepository
                .findById(UUID.fromString("44a0fee8-a5b5-4153-9ce8-b57e8b6b3f55"))
                .orElse(null);

        book.setAuthor(author);

        repository.save(book);
    }
}