package com.faisalyousaf777.controller;


import com.faisalyousaf777.dto.BookDTO;
import com.faisalyousaf777.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LendingController {

    @Autowired
    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping("/borrow/{userId}/{bookId}")
    public BookDTO borrowBook(Long userId, Long bookId) {
        return lendingService.borrowBook(userId, bookId);
    }

    @PostMapping("/return/{userId}/{bookId}")
    public String returnBook(Long userId, Long bookId) {
        return lendingService.returnBook(userId, bookId);
    }
}
