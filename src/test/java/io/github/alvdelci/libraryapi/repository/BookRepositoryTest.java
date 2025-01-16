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

    @Test
    void saveWithCascadeTest() {//usar o cascade em producao pode ser perigoso. Estudar bem se for utilizar, mas é melhor fazer a criação na mao
        Book book = new Book();
        book.setTitle("How to make tofu");
        book.setGender(BookGender.BIOGRAFIA);
        book.setIsbn("12343-4234");
        book.setPublicationDate(LocalDate.of(2000, 11, 9));

        Author author = new Author();
        author.setName("Rodolfo");
        author.setNationality("Korean");
        author.setBirthday(LocalDate.of(1984, 4, 23));

        book.setAuthor(author);

        repository.save(book);
    }

    @Test
    void updateBookAuthor() {
        UUID bookId = UUID.fromString("0805cf6b-b6b3-4737-b90b-287c54680068");
        var bookToUpdate = repository
                .findById(bookId)
                .orElse(null);

        UUID authorId = UUID.fromString("8e8a6667-ce7d-441a-a7e9-378a1c0ba118");
        Author newAuthor = authorRepository.findById(authorId).orElse(null);

        bookToUpdate.setAuthor(newAuthor);

        repository.save(bookToUpdate);
    }

    @Test
    void deleteById() {
        UUID bookId = UUID.fromString("27c50bd8-15f5-4e56-9164-5f0d10dbb895");

        repository.deleteById(bookId);
    }



}