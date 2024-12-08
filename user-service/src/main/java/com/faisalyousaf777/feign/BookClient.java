package com.faisalyousaf777.feign;


import com.faisalyousaf777.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "book-service", url = "http://localhost:8081/api/v1")      //URL of the book-service
public interface BookClient {

    @GetMapping("/api/books/{id}")      //URL of the book-service
    BookDTO getBookById(@PathVariable("id") Long id);

    @PostMapping("/api/library-cards/{cardId}/issue-book/{bookId}")      //URL of the book-service
    String issueBook(@PathVariable("cardId") Long cardId, @PathVariable("bookId") Long bookId);
}
