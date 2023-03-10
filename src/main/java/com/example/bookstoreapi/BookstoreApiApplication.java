package com.example.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Basic spring boot format for a local postgres db
 * 
 */
@SpringBootApplication
public class BookstoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiApplication.class, args);
	}

}
