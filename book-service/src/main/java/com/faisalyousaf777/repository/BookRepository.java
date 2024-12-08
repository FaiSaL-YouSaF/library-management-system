package com.faisalyousaf777.repository;

import com.faisalyousaf777.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle(String title);
    Optional<List<Book>> findAllBooksByAuthor(String author);
    Optional<Book> findBookByIsbn(String isbn);
}
