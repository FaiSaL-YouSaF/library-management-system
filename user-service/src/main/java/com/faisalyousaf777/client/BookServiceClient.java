package com.faisalyousaf777.client;

import com.faisalyousaf777.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8082/api/v1/books")
public interface BookServiceClient {

    @GetMapping("/{bookId}")
    BookDTO getBookById(@PathVariable("bookId") final Long bookId);

    @GetMapping("/")
    List<BookDTO> getAllBooks();

}
