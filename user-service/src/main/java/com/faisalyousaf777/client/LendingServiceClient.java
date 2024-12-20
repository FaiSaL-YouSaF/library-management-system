package com.faisalyousaf777.client;


import com.faisalyousaf777.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "lending-service", url = "http://localhost:8081/api/v1/lendings")
public interface LendingServiceClient {

    @GetMapping("/issue-book/{userId}/{bookId}")
    BookDTO issueBook(@PathVariable("userId") final Long userId, @PathVariable("bookId") final Long bookId);

    @PutMapping("/return-book/{userId}/{bookId}")
    ResponseEntity<Object> returnBook(@PathVariable("userId") final Long userId, @PathVariable("bookId") final Long bookId);
}
