
package com.example.bookstoreapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

    @Autowired
    private BookstoreService bookstoreService;

    @GetMapping("/getAllBooks")
    public ResponseEntity getAllBooks(){
        return new ResponseEntity(this.bookstoreService.getAllBooks(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllBooksWishlist")
    public ResponseEntity getAllBooksWishlist(){
        return new ResponseEntity(this.bookstoreService.getAllBooksWishlist(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllBooksFromWishlist")
    public ResponseEntity getAllBooksFromWishlist(){
        return new ResponseEntity(this.bookstoreService.getAllBooksFromWishlist(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllBookComments")
    public ResponseEntity getAllBookComments(){
        return new ResponseEntity(this.bookstoreService.getAllBookComments(), HttpStatus.ACCEPTED);
    }


    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity getUserByUsername(String username){
         ;
        return new ResponseEntity(this.bookstoreService.getUserByUsername(username), HttpStatus.ACCEPTED);
    }

}
