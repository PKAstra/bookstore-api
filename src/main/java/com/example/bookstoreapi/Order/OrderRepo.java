package com.example.bookstoreapi.Order;


import com.example.bookstoreapi.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>
{

}
