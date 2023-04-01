package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookstoreComment extends JpaRepository<Comment, Integer> {

}

