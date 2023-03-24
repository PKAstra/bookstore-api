package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookstoreWishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Wishlist (user_id, wishlist_name) VALUES (?2, ?1)", nativeQuery = true)
    void addWishlistQuery(String wishlist_name, Integer user_id);
}
