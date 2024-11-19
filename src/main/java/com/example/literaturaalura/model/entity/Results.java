package com.example.literaturaalura.model.entity;

import com.example.literaturaalura.model.record.ResultsData;

import java.util.List;
import java.util.stream.Collectors;

public class Results {
    private List<Book> books;

    public Results(ResultsData resultsData) {
        this.books = resultsData.results().stream()
                .map(book -> new Book(book))
                .collect(Collectors.toList());
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
