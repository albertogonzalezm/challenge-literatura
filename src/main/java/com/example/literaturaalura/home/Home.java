package com.example.literaturaalura.home;

import com.example.literaturaalura.model.entity.Author;
import com.example.literaturaalura.model.entity.Book;
import com.example.literaturaalura.model.entity.Results;
import com.example.literaturaalura.model.record.ResultsData;
import com.example.literaturaalura.repository.IAuthorRepository;
import com.example.literaturaalura.repository.IBookRepository;
import com.example.literaturaalura.service.DataConverter;
import com.example.literaturaalura.service.GutendexApi;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Home {
    private Scanner sc = new Scanner(System.in);
    private final String gutendexApiUrl = "https://gutendex.com/books/?";
    private GutendexApi gutendexApi = new GutendexApi();
    private DataConverter dataConverter = new DataConverter();


    private IAuthorRepository authorRepository;
    private IBookRepository bookRepository;

    public Home(IAuthorRepository authorRepository, IBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    String menu = """
            \n1- Buscar libro por titulo
            2- Buscar libros registrados
            3- Listar autores registrados
            4- Listar autores vivos en un determinado año
            5- Listar libros por idioma
            0- Salir
            """;


    public void showMenu() {
        int option;
        do {
            System.out.println(menu);
            System.out.print("Opcion: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    getBook();
                    break;
                case 2:
                    getRegisteredBooks();
                    break;
                case 3:
                    getRegisteredAuthors();
                    break;
                case 4:
                    getLivingAuthors();
                    break;
                case 5:
                    getBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Cerrando aplicacion...");
                    break;
                default:
                    System.out.println(String.format("\nOpcion '%d' no valida\n", option));
            }
        } while (option != 0);
    }

    private void saveData(Book book) {

        Author author = authorRepository.findByName(book.getAuthor().getName())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(book.getAuthor().getName());
                    newAuthor.setBirthYear(book.getAuthor().getBirthYear());
                    newAuthor.setDeathYear(book.getAuthor().getDeathYear());
                    return authorRepository.save(newAuthor);
                });

        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(author);
        newBook.setLanguages(book.getLanguages());
        newBook.setDonwloadCount(book.getDonwloadCount());

        bookRepository.save(newBook);
    }

    private ResultsData getBook() {
        System.out.print("\nEscribe el titulo del libro: ");
        var bookTitle = sc.nextLine();
        var json = gutendexApi.getData(gutendexApiUrl + "search=" + bookTitle.replace(" ", "+"));

        ResultsData resultsData = dataConverter.getData(json, ResultsData.class);
        Results results = new Results(resultsData);

        if (!results.getBooks().isEmpty()) {
            Book asd = results.getBooks().get(0);
            saveData(asd);
        }

        return resultsData;
    }

    private void getRegisteredBooks() {
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            System.out.println(book + "\n");
        }
    }

    private void getRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            System.out.println(author + "\n");
        }
    }

    private void getLivingAuthors() {
        System.out.print("\nEscribe el año para saber que autores aun estaban o estaban vivos\nAño:");
        var year = sc.nextInt();
        List<Author> authors = authorRepository.findAll();

        List<Author> authorsAliveInYear = authors.stream()
                .filter(author -> author.getBirthYear() <= year &&
                        (author.getDeathYear() == null || author.getDeathYear() >= year))
                .collect(Collectors.toList());

        for (Author author : authorsAliveInYear) {
            System.out.println(author + "\n");
        }
    }

    private void getBooksByLanguage() {
        System.out.print("\nEscribe el idioma del libro Ejemplo: en, es, pt...\nIdioma:");
        var language = sc.nextLine();
        List<Book> books = bookRepository.findByLanguages(language);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma. (" + language + ")");
        }

        for (Book book : books) {
            System.out.println(book + "\n");
        }
    }

}
