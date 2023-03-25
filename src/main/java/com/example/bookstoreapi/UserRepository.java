package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT b FROM User b WHERE b.username = ?1")
    public List<User> getUserByUsername(String username);
    @Transactional @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Users (username, password, name, email, home_address) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void createUser(String username, String password, String name, String email, String home_address);
}
