package io.github.alvdelci.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author", schema = "public")
@Getter
@Setter //Gera os getters e setters em tempo de compilacao
@ToString
public class Author {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    //@OneToMany(mappedBy = "author")
    @Transient
    private List<Book> books;
}
