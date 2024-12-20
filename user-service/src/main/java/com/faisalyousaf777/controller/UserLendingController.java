package com.faisalyousaf777.controller;


import com.faisalyousaf777.client.LendingServiceClient;
import com.faisalyousaf777.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/lendings")
public class UserLendingController {

    @Autowired
    private final LendingServiceClient lendingServiceClient;

    public UserLendingController(LendingServiceClient lendingServiceClient) {
        this.lendingServiceClient = lendingServiceClient;
    }

    @GetMapping("/issue-book/{userId}/{bookId}")
    public ResponseEntity<BookDTO> borrowBook(@PathVariable("userId") final Long userId, @PathVariable("bookId") final Long bookId) {
        return ResponseEntity.ok(lendingServiceClient.issueBook(userId, bookId));
    }

    @GetMapping("/return-book/{cardId}/{bookId}")
    public ResponseEntity<Object> returnBook(@PathVariable("userId") final Long userId, @PathVariable("bookId") final Long bookId) {
        return ResponseEntity.ok(lendingServiceClient.returnBook(userId, bookId));
    }
}
