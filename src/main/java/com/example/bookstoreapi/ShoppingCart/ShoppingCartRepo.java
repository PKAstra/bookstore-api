package com.example.bookstoreapi.ShoppingCart;

import com.example.bookstoreapi.ShoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer>
{

}
