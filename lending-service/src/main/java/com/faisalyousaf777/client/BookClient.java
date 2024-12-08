package com.faisalyousaf777.client;


import com.faisalyousaf777.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "book-service", url = "http://localhost:8081/api/v1")
public interface BookClient {

    @GetMapping("/books/{bookId}")
    BookDTO getBookById(@PathVariable("bookId") Long bookId);

    @PostMapping("/api/library-cards/{cardId}/issue-book/{bookId}")
    String issueBook(@PathVariable("cardId") Long cardId, @PathVariable("bookId") Long bookId);

}
