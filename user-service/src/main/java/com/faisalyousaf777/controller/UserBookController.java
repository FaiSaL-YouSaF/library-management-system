package com.faisalyousaf777.controller;

import com.faisalyousaf777.client.BookServiceClient;
import com.faisalyousaf777.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/books")
public class UserBookController {

    @Autowired
    private final BookServiceClient bookServiceClient;

    public UserBookController(BookServiceClient bookServiceClient) {
        this.bookServiceClient = bookServiceClient;
    }

    @GetMapping("/{bookId}")
    public BookDTO getBookById(@PathVariable("bookId") Long bookId) {
        return bookServiceClient.getBookById(bookId);
    }

    @GetMapping("/")
    public List<BookDTO> getAllBooks() {
        return bookServiceClient.getAllBooks();
    }
}
