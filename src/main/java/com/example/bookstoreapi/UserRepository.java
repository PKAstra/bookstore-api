package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
