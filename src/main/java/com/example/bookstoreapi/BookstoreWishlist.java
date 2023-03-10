package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreWishlist extends JpaRepository<Wishlist, Integer> {
}
