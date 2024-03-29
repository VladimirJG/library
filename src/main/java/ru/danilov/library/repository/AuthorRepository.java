package ru.danilov.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilov.library.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}