
package com.example.bookstoreapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookstoreService {

    private static final Logger logger = LoggerFactory.getLogger(BookstoreService.class);

    @Autowired
    BookstoreRepository bookstoreRepo;
    @Autowired
    BookstoreWishlistRepository bookstoreWish;
    @Autowired
    BookstoreWishlistBooks bookstoreWishlistBooks;
    @Autowired
    BookstoreComment bookstoreComment;
     @Autowired
    UserRepository userRepository; 
    
    public List<Book> getAllBooks(){
        // logic
        return bookstoreRepo.findAll();
    }

    public List<Wishlist> getAllBooksWishlist(){
        // logic
        return bookstoreWish.findAll();
    }

    public List<WishlistBooks> getAllBooksFromWishlist(){
        // logic
        return bookstoreWishlistBooks.findAll();
    }

    public ResponseEntity deleteBookWishlist(Integer wishlist_id, Integer book_id){
        WishlistBooks wishlistBooks = bookstoreWishlistBooks.findBookWishlist(wishlist_id, book_id);

        if(null != wishlistBooks){
            bookstoreWishlistBooks.delete(wishlistBooks);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity addBookWishlist(Integer wishlist_id, Integer book_id){
        bookstoreWishlistBooks.addBookWishlistQuery(wishlist_id, book_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity addWishlist(String wishlist_name, Integer user_id){
        bookstoreWish.addWishlistQuery(wishlist_name, user_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Comment> getAllBookComments(){
        // logic
        return bookstoreComment.findAll();
    }

    public ResponseEntity<?> findBooksByGenre(String genre){
        logger.info("Books with genre to find: " + genre);
        List<Book> books = bookstoreRepo.findBookByGenre(genre);

        if(!books.isEmpty()){
            logger.info("Books found in db");
            return new ResponseEntity<>(books, HttpStatus.OK);
        }else{
            logger.error("Books not found with genre: " + genre);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> findTop10BooksSold(){
        logger.info("Top 10 best selling books: ");
        List<Book> books = bookstoreRepo.findTop10BooksByCopiesSold();
        if(!books.isEmpty()){
            logger.info("Books found in db.");
            return new ResponseEntity<>(books, HttpStatus.OK);
        }else{
            logger.error("Books not found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

       public List<User> getUserByUsername(String username){
       //  logic
        return userRepository.findAll();
    }
}

