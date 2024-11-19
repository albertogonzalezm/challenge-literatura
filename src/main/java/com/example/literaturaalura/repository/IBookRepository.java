package com.example.literaturaalura.repository;

import com.example.literaturaalura.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguages(String language);
}
