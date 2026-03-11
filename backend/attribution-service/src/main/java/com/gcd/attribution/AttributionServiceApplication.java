package com.gcd.attribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AttributionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttributionServiceApplication.class, args);
    }
}
