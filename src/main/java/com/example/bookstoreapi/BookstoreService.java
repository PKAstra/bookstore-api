
package com.example.bookstoreapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookstoreService {
    
    @Autowired
    BookstoreRepository bookstoreRepo;
    
    public List<Book> getAllBooks(){
        // logic
        return bookstoreRepo.findAll();
    }
    
}
