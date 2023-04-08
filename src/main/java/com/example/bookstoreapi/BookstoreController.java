
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

    
    BookstoreController(BookService bookService) {
        this.bookService = bookService;
    }

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
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity(this.bookstoreService.getUserByUsername(username), HttpStatus.ACCEPTED);
    }
    @PostMapping("/createUser")
    public ResponseEntity createUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password")  String password,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "home_address") String home_address){
        return this.bookstoreService.createUser(username, password, name, email, home_address);
    }

    // Kenneth Richards
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book bookId) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
/**
 * @param isbn
 * @return
 * 
 */

 @Autowired
 private final BookService bookService;

@GetMapping("/{isbn}") 
public ResponseEntity<Book> getBookByIsbn(@RequestParam String isbn) {
    Book book = bookService.getBookByIsbn(isbn);
    if (book != null) {
        return ResponseEntity.ok().body(book);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    
@Autowired
private AuthorService authorService;

@PostMapping("/author")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.createAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    // Endpoint for retrieving a list of books associated with an author
    @GetMapping("/author/{authorId}/books")
    public List<Book> getBooksByAuthorId(@PathVariable Long authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }

}
    
       

