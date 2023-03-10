package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreWishlistBooks extends JpaRepository<WishlistBooks, Integer> {
}
