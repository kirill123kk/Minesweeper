package com.example.meens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableJpaRepositories
@SpringBootApplication

public class MinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinsApplication.class, args);
    }

}
