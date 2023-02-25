package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookstoreWishlistBooks extends JpaRepository<WishlistBooks, Integer> {


    @Query(value = "SELECT w FROM WishlistBooks w WHERE w.wishlist_id = ?1 AND w.book_id = ?2")
    WishlistBooks findBookWishlist(Integer wishlistId, Integer bookId);
}
