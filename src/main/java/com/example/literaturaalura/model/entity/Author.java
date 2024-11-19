package com.example.literaturaalura.model.entity;

import com.example.literaturaalura.model.record.AuthorData;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Author() {
    }

    public Author(AuthorData authorData) {
        this.name = authorData.name();
        this.birthYear = Optional.ofNullable(authorData.birthYear()).orElse(0);
        this.deathYear = Optional.ofNullable(authorData.deathYear()).orElse(0);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "\nname='" + name + '\'' +
                "\ndeathYear=" + deathYear +
                "\nbirthYear=" + birthYear +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
