
package com.example.bookstoreapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

    @Autowired
    private BookstoreService bookstoreService;

    @GetMapping("/getAllBooks")
    public ResponseEntity getAllBooks(){
        return new ResponseEntity(this.bookstoreService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getAllBooksWishlist")
    public ResponseEntity getAllBooksWishlist(){
        return new ResponseEntity(this.bookstoreService.getAllBooksWishlist(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllBooksFromWishlist")
    public ResponseEntity getAllBooksFromWishlist(){
        return new ResponseEntity(this.bookstoreService.getAllBooksFromWishlist(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeBookWishlist")
    public ResponseEntity deleteBookWishlist(
            @RequestParam(value = "wishlist_id") Integer wishlist_id,
            @RequestParam(value = "book_id") Integer book_id){
        return this.bookstoreService.deleteBookWishlist(wishlist_id, book_id);
    }

    @PostMapping("/addBookWishlist")
    public ResponseEntity addBookWishlist(
            @RequestParam(value = "wishlist_id") Integer wishlist_id,
            @RequestParam(value = "book_id") Integer book_id){
        return this.bookstoreService.addBookWishlist(wishlist_id, book_id);
    }

    @PostMapping("/addWishlist")
    public ResponseEntity addWishlist(
            @RequestParam(value = "wishlist_name") String wishlist_name,
            @RequestParam(value = "user_id") Integer user_id){
        return this.bookstoreService.addWishlist(wishlist_name, user_id);
    }

    @GetMapping("/getAllBookComments")
    public ResponseEntity getAllBookComments(){
        return new ResponseEntity(this.bookstoreService.getAllBookComments(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBooksByGenre/{genre}")
    public ResponseEntity getBooksByGenre(@PathVariable("genre") String genre){
        return this.bookstoreService.findBooksByGenre(genre);
    }

    @GetMapping("/getTop10BooksSold")
    public ResponseEntity getTop10BooksSold(){
        return this.bookstoreService.findTop10BooksSold();
    }

    @GetMapping("/getBooksByRating/{rating}")
    public ResponseEntity getBooksByRating(@PathVariable("rating") Double rating){
        return this.bookstoreService.findBooksByRating(rating);
    }
    
        @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity getUserByUsername(String username){
         ;
        return new ResponseEntity(this.bookstoreService.getUserByUsername(username), HttpStatus.ACCEPTED);
    }
}
