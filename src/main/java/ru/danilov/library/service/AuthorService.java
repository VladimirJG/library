package ru.danilov.library.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.danilov.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(int id);

    ResponseEntity<Author> updateAuthor(Author author, int id);

    ResponseEntity<Author> createAuthor(Author author);

    HttpStatus deleteAuthor(int id);
}