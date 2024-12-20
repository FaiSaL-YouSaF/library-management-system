package com.faisalyousaf777.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "lending-service", url = "http://localhost:8082/api/v1")
public interface LendingServiceClient {

    @PutMapping("/issue-book/{cardId}/{bookId}")
    ResponseEntity<Object> issueBook(Long cardId, Long bookId);

    @PutMapping("/return-book/{cardId}/{bookId}")
    ResponseEntity<Object> returnBook(Long cardId, Long bookId);
}
