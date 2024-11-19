package com.example.literaturaalura.model.entity;

import com.example.literaturaalura.model.record.BookData;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String languages;
    private Integer donwloadCount;

    public Book() {
    }

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.author = (bookData.authors() != null && !bookData.authors().isEmpty())
                ? new Author(bookData.authors().get(0))
                : null;
        this.languages = Optional.ofNullable(bookData.languages().get(0)).orElse("");
        this.donwloadCount = Optional.ofNullable(bookData.donwloadCount()).orElse(0);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "\ntitle='" + title + '\'' +
                "\nauthor=" + author +
                "\nlanguages='" + languages + '\'' +
                "\ndonwloadCount=" + donwloadCount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Integer getDonwloadCount() {
        return donwloadCount;
    }

    public void setDonwloadCount(Integer donwloadCount) {
        this.donwloadCount = donwloadCount;
    }
}
