package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookstoreWishlistBooks extends JpaRepository<WishlistBooks, Integer> {


    @Query(value = "SELECT w FROM WishlistBooks w WHERE w.wishlist_id = ?1 AND w.book_id = ?2")
    WishlistBooks findBookWishlist(Integer wishlistId, Integer bookId);
    @Transactional @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO WishlistBooks (wishlist_id, book_id) VALUES (?1, ?2)", nativeQuery = true)
    void addBookWishlistQuery(Integer wishlistId, Integer bookId);

    @Query(value = "SELECT w FROM WishlistBooks w WHERE w.wishlist_id = ?1")
    List<WishlistBooks> findAllBooksWishlist(Integer wishlistId);

}
