
package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BookstoreRepository extends JpaRepository<Book, Integer> {
    

    
}
