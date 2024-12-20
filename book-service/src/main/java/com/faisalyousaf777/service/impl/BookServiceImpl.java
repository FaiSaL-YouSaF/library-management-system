package com.faisalyousaf777.service.impl;

import com.faisalyousaf777.entity.Book;
import com.faisalyousaf777.enums.BookStatus;
import com.faisalyousaf777.exception.BookAlreadyExistsException;
import com.faisalyousaf777.exception.BookNotFoundException;
import com.faisalyousaf777.repository.BookRepository;
import com.faisalyousaf777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(final Book book) {
        if (bookRepository.findById(book.getBookId()).isPresent()) {
            throw new BookAlreadyExistsException("Invalid ID : Book with the ID : " + book.getBookId() + " already exists.");
        }
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(final Long bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        }
        return Optional.of(bookRepository.findById(bookId).get());
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        if (bookRepository.findBookByTitle(title).isEmpty()) {
            throw new BookNotFoundException("Invalid Title : Book with the Title : " + title + " does not exists.");
        }
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        if (bookRepository.findBookByIsbn(isbn).isEmpty()) {
            throw new BookNotFoundException("Invalid ISBN : Book with the ISBN : " + isbn + " does not exists.");
        }
        return bookRepository.findBookByIsbn(isbn);
    }

    @Override
    public Optional<List<Book>> getAllBooks() {
        return Optional.of(bookRepository.findAll());
    }

    @Override
    public Optional<List<Book>> getBooksByAuthor(String author) {
        if (bookRepository.findAllBooksByAuthor(author).isEmpty()) {
            throw new BookNotFoundException("Invalid Author : Book with the Author : " + author + " does not exists.");
        }
        return bookRepository.findAllBooksByAuthor(author);
    }

    @Override
    public void updateBookById(Long bookId, Book book) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        } else {
            Book currentBook = bookRepository.findById(bookId).get();
            currentBook.setTitle(book.getTitle());
            currentBook.setAuthor(book.getAuthor());
            currentBook.setGenre(book.getGenre());
            currentBook.setIsbn(book.getIsbn());
            currentBook.setPublicationDate(book.getPublicationDate());
            currentBook.setStatus(book.getStatus());
            bookRepository.save(currentBook);
        }
    }

    @Override
    public void updateBookStatusById(Long bookId, BookStatus status) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        } else {
            Book currentBook = bookRepository.findById(bookId).get();
            currentBook.setStatus(status);
            bookRepository.save(currentBook);
        }
    }

    @Override
    public void deleteBookById(Long bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Invalid ID : Book with the ID : " + bookId + " does not exists.");
        } else {
            bookRepository.deleteById(bookId);
        }
    }
}
