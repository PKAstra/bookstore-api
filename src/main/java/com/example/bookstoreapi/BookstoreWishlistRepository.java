package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookstoreWishlistRepository extends JpaRepository<Wishlist, Integer> {
}
