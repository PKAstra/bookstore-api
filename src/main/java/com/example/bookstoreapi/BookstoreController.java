
package com.example.bookstoreapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

    @Autowired
    private BookstoreService bookstoreService;
    @Autowired
    private BookstoreRating bookstoreRating;
    @Autowired
    private BookstoreComment bookstoreComment;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @GetMapping("/getAllBookRatings")
    public ResponseEntity getAllBookRatings(){
        return new ResponseEntity(this.bookstoreService.getAllBookRatings(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getCommentsForBook/{book_id}")
    public ResponseEntity getCommentsForBook(@PathVariable Integer book_id){
        return new ResponseEntity(this.bookstoreService.getCommentsForBook(book_id), HttpStatus.ACCEPTED);
    }
    @PostMapping("/addRating")
    public ResponseEntity<Void> createRating(@RequestBody Rating rating) {
        bookstoreService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        bookstoreService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/average-rating")
    public Double getBookAverageRating(@PathVariable Integer id) {
        String query = "SELECT AVG(rating) FROM ratings WHERE book_id = ?";
        List<Double> results = jdbcTemplate.queryForList(query, Double.class, id);
        return results.get(0);
    }
}
