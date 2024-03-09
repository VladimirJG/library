package ru.danilov.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.danilov.library.model.Book;


@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer>, JpaRepository<Book, Integer> {
    Page<Book> findAll(Pageable pageable);
}