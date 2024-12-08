package com.faisalyousaf777.service;

import com.faisalyousaf777.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public void addBook(final Book book);
    public Optional<Book> getBookById(final Long bookId);
    public Optional<Book> getBookByTitle(final String title);
    public Optional<Book> getBookByIsbn(final String isbn);
    public Optional<List<Book>> getAllBooks();
    public Optional<List<Book>> getBooksByAuthor(final String author);
    public void updateBookById(final Long bookId, final Book book);
    public void deleteBookById(final Long bookId);
}
