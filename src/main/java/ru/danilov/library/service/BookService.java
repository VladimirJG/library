package ru.danilov.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.danilov.library.model.Book;

import java.util.List;

public interface BookService {
    Page<Book> getAllBooksPage(Pageable pageable);

    Iterable<Book> getAllBooksSort(Sort sort);

    List<Book> getAllBooks();

    Book getBookById(int id);

    void updateBook(Book book, int id);

    void createBook(Book book);

    void deleteBook(int id);
}