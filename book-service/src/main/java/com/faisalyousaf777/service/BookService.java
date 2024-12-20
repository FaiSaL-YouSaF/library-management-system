package com.faisalyousaf777.service;

import com.faisalyousaf777.entity.Book;
import com.faisalyousaf777.enums.BookStatus;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void addBook(final Book book);
    Optional<Book> getBookById(final Long bookId);
    Optional<Book> getBookByTitle(final String title);
    Optional<Book> getBookByIsbn(final String isbn);
    Optional<List<Book>> getAllBooks();
    Optional<List<Book>> getBooksByAuthor(final String author);
    void updateBookById(final Long bookId, final Book book);
    void updateBookStatusById(final Long bookId, final BookStatus status);
    void deleteBookById(final Long bookId);
}
