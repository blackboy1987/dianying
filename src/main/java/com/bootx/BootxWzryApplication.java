package com.bootx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BootxWzryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootxWzryApplication.class, args);
    }

}
