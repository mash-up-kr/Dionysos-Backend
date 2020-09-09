package com.dionysos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DionysosAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(DionysosAPIApplication.class,
                args
        );
    }
}
