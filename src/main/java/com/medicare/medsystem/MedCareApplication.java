package com.medicare.medsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.medicare.medsystem.repository")
public class MedCareApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedCareApplication.class, args);
    }

}
