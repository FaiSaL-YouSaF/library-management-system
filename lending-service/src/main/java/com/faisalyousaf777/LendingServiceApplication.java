package com.faisalyousaf777;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LendingServiceApplication {

        public static void main(String[] args) {
            SpringApplication.run(LendingServiceApplication.class, args);
        }
}
