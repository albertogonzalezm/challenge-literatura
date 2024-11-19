package com.example.literaturaalura.repository;

import com.example.literaturaalura.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
