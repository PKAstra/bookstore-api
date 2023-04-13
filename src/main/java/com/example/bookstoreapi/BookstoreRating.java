package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
public interface BookstoreRating extends JpaRepository<Rating, Integer> {
}
