package ru.danilov.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.danilov.library.model.Author;
import ru.danilov.library.model.Book;
import ru.danilov.library.repository.BookRepository;
import ru.danilov.library.service.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
class LibraryApplicationTests {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", 2000, new Author(1, "Margarita")));
        books.add(new Book(2, "Book2", 1990, new Author(2, "Miron")));

        when(bookRepository.findAll()).thenReturn(books);
        List<Book> bookList = bookService.getAllBooks();

        Assertions.assertNotNull(bookList);
        Assertions.assertEquals(2, bookList.size());
        Assertions.assertEquals("Margarita", bookList.get(0).getOwner().getName());
        Assertions.assertEquals("Book2", bookList.get(1).getName());
    }

    @Test
    public void testGetAllBooksPagesAndSort() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", 2000, new Author(1, "Margarita")));
        books.add(new Book(2, "Book2", 2021, new Author(2, "Miron")));
        books.add(new Book(3, "Book3", 1955, new Author(2, "Alena")));
        books.add(new Book(4, "Book4", 1980, new Author(2, "Kinder")));

        Pageable pageable = PageRequest.of(0, 2, Sort.by("year"));
        Page<Book> bookPage = new PageImpl<>(books, pageable, pageable.getPageSize());

        Mockito.when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        Page<Book> result = bookService.getAllBooksPage(pageable);

        Assertions.assertEquals(1, result.getTotalPages());
        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertEquals(4, result.getNumberOfElements());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals("Miron", result.getContent().get(1).getOwner().getName());
    }

    @Test
    public void getBookById() {
        Book book = new Book(4, "Book4", 1980, new Author(2, "Kinder"));

        when(bookRepository.findById(4)).thenReturn(Optional.of(book));
        Book book1 = bookService.getBookById(4);

        Assertions.assertNotNull(book1);
        Assertions.assertEquals("Kinder", book1.getOwner().getName());
    }

    @Test
    public void testUpdateBook() {
        Book updateBook = new Book(1, "Updated Book", 2022, new Author(1, "Updated Author"));
        int id = 1;

        when(bookRepository.save(updateBook)).thenReturn(updateBook);

        bookService.updateBook(updateBook, id);
        verify(bookRepository).save(updateBook);
    }

    @Test
    public void testCreateBook() {
        Book book = new Book(1, "New Book", 2022, new Author(1, "New Author"));
        bookService.createBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    public void testDeleteBook() {
        int id = 1;
        bookService.deleteBook(id);
        verify(bookRepository).deleteById(id);
    }
}