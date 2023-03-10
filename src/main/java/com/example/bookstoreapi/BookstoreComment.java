package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
public interface BookstoreComment extends JpaRepository<Comment, Integer> {
}

