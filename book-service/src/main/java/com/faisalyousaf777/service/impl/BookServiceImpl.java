package com.faisalyousaf777.service.impl;

import com.faisalyousaf777.entity.Book;
import com.faisalyousaf777.exception.BookAlreadyExistsException;
import com.faisalyousaf777.exception.BookNotFoundException;
import com.faisalyousaf777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookService bookService;

    public BookServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void addBook(final Book book) {
        if (bookService.getBookById(book.getBookId()).isPresent()) {
            throw new BookAlreadyExistsException("Invalid ID : Book with the ID : " + book.getBookId() + " already exists.");
        }
        bookService.addBook(book);
    }

    @Override
    public Optional<Book> getBookById(final Long bookId) {
        if (bookService.getBookById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        }
        return bookService.getBookById(bookId);
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        if (bookService.getBookByTitle(title).isEmpty()) {
            throw new BookNotFoundException("Invalid Title : Book with the Title : " + title + " does not exists.");
        }
        return bookService.getBookByTitle(title);
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        if (bookService.getBookByIsbn(isbn).isEmpty()) {
            throw new BookNotFoundException("Invalid ISBN : Book with the ISBN : " + isbn + " does not exists.");
        }
        return bookService.getBookByIsbn(isbn);
    }

    @Override
    public Optional<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public Optional<List<Book>> getBooksByAuthor(String author) {
        if (bookService.getBooksByAuthor(author).isEmpty()) {
            throw new BookNotFoundException("Invalid Author : Book with the Author : " + author + " does not exists.");
        }
        return bookService.getBooksByAuthor(author);
    }

    @Override
    public void updateBookById(Long bookId, Book book) {
        if (bookService.getBookById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        } else {
            Book currentBook = bookService.getBookById(bookId).get();
            currentBook.setTitle(book.getTitle());
            currentBook.setAuthor(book.getAuthor());
            currentBook.setGenre(book.getGenre());
            currentBook.setIsbn(book.getIsbn());
            currentBook.setPublicationDate(book.getPublicationDate());
            currentBook.setStatus(book.getStatus());
            bookService.addBook(currentBook);
        }
    }

    @Override
    public void deleteBookById(Long bookId) {
        if (bookService.getBookById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        } else {
            bookService.deleteBookById(bookId);
        }
    }
}
