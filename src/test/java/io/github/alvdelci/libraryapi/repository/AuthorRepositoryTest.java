package io.github.alvdelci.libraryapi.repository;

import io.github.alvdelci.libraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Test
    public void saveTest() {
        Author author = new Author();
        author.setName("Maria");
        author.setNationality("Korean");
        author.setBirthday(LocalDate.of(1996, 06, 14));

        var authorSaved = repository.save(author);
        System.out.println("Author Saved: " + authorSaved);
    }

    @Test
    public void updateTest() {
        var id = UUID.fromString("44a0fee8-a5b5-4153-9ce8-b57e8b6b3f55");

        Optional<Author> possibleAuthor = repository.findById(id);

        if(possibleAuthor.isPresent()) {

            Author foundAuthor = possibleAuthor.get();
            System.out.println("Dados do autor: \n" + foundAuthor);

            foundAuthor.setBirthday(LocalDate.of(1992, 12, 30));
            foundAuthor.setName("Joaquim");

            repository.save(foundAuthor);
        }
    }
}
