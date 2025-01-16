package io.github.alvdelci.libraryapi;

import io.github.alvdelci.libraryapi.model.Author;
import io.github.alvdelci.libraryapi.repository.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		var context = SpringApplication.run(Application.class, args);

		AuthorRepository repository = context.getBean(AuthorRepository.class);

		saveRegisterExample(repository);
	}

	public static void saveRegisterExample(AuthorRepository authorRepository) {
		Author author = new Author();
		author.setName("José");
		author.setNationality("Brazilian");
		author.setBirthday(LocalDate.of(2001, 06, 20));

		var authorSaved = authorRepository.save(author);
		System.out.println("Author Saved: " + authorSaved);
	}
}
