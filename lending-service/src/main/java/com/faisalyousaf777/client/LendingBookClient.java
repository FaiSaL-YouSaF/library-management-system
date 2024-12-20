package com.faisalyousaf777.client;


import com.faisalyousaf777.dto.BookDTO;
import com.faisalyousaf777.enums.BookStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@FeignClient(name = "book-service", url = "http://localhost:8082/api/v1/books")
public interface LendingBookClient {

    @GetMapping("/{bookId}")
    BookDTO getBookById(@PathVariable("bookId") Long bookId);

    @PutMapping("/update/status/{bookId}")
    void updateBookStatusById(@PathVariable("bookId") final Long bookId, @RequestAttribute final BookStatus status);

}
