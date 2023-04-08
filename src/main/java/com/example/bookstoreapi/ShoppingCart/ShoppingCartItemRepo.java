package com.example.bookstoreapi.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepo extends JpaRepository<ShoppingCartItem, Integer> {
}
