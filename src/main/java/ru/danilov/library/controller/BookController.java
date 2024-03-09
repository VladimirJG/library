package ru.danilov.library.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danilov.library.model.Book;
import ru.danilov.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/page")
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookService.getAllBooksPage(pageable);
    }

    @GetMapping("/sort")
    public Iterable<Book> getAllBooks(Sort sort) {
        return bookService.getAllBooksSort(sort);
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id) {
        return bookService.getBookById(id);
    }

    @PostMapping()
    public HttpStatus createBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }
        bookService.createBook(book);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(book);
        }
        bookService.updateBook(book, id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return HttpStatus.OK;
    }
}