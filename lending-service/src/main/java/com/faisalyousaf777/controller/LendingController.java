package com.faisalyousaf777.controller;


import com.faisalyousaf777.dto.BookDTO;
import com.faisalyousaf777.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lendings")
public class LendingController {

    @Autowired
    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @GetMapping("/issue-book/{userId}/{bookId}")
    public ResponseEntity<BookDTO> issueBook(@PathVariable("userId") final Long userId, @PathVariable("bookId") final Long bookId) {
        return ResponseEntity.ok(lendingService.issueBook(userId, bookId));
    }

    @PutMapping("/return-book/{cardId}/{bookId}")
    public String returnBook(@PathVariable("userId") final Long userId, @PathVariable("bookId") final Long bookId) {
        return lendingService.returnBook(userId, bookId);
    }
}
