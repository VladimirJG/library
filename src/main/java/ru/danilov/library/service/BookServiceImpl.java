package ru.danilov.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilov.library.exception.BookNotFoundException;
import ru.danilov.library.model.Book;
import ru.danilov.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> getAllBooksPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Iterable<Book> getAllBooksSort(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    @Transactional
    @Override
    public void updateBook(Book updateBook, int id) {
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }

    @Transactional
    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
