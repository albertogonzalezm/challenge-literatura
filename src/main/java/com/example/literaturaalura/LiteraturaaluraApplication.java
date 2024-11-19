package com.example.literaturaalura;

import com.example.literaturaalura.home.Home;
import com.example.literaturaalura.repository.IAuthorRepository;
import com.example.literaturaalura.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaaluraApplication implements CommandLineRunner {

    @Autowired
    private IAuthorRepository authorRepository;
    @Autowired
    private IBookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaaluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Home home = new Home(authorRepository, bookRepository);
        home.showMenu();
    }
}
