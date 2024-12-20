package com.faisalyousaf777.client;

import com.faisalyousaf777.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/v1/users")
public interface LendingUserClient {

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("userId") final Long userId);

}
