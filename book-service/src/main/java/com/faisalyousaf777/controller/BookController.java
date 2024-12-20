package com.faisalyousaf777.controller;


import com.faisalyousaf777.entity.Book;
import com.faisalyousaf777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody final Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok("Book added successfully.");
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Object> getBookById(@PathVariable("bookId") final Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Object> getBookByTitle(@PathVariable("title") final String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Object> getBookByIsbn(@PathVariable("isbn") final String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Object> getBooksByAuthor(@PathVariable("author") final String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<Object> updateBookById(@PathVariable("bookId") final Long bookId, @RequestBody final Book book) {
        bookService.updateBookById(bookId, book);
        return ResponseEntity.ok("Book updated successfully.");
    }

    @PutMapping("/update/status/{bookId}/")
    public ResponseEntity<Object> updateBookStatusById(@PathVariable("bookId") final Long bookId, @RequestAttribute() final Book book) {
        bookService.updateBookStatusById(bookId, book.getStatus());
        return ResponseEntity.ok("Book status updated successfully.");
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("bookId") final Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book deleted successfully.");
    }
}
