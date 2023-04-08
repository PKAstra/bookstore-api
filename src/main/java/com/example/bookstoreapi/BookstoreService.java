
package com.example.bookstoreapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    CardRepository cardRepository;
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    public ResponseEntity<?> findBooksByRating(Double rating) {
        logger.info("Books with rating " + rating + " or higher:");
        List<Book> books = bookstoreRepo.findBooksByRating(rating);

        if (!books.isEmpty()) {
            logger.info("Books found in db");
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            logger.error("No books found with rating " + rating + " or higher");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

       public ResponseEntity<?> getUserByUsername(String username){
       //  logic
           logger.info("Matching Users:");
           List<User> users = userRepository.getUserByUsername(username);


           return new ResponseEntity<>(users,HttpStatus.OK);
       }
    public ResponseEntity createUser(String username, String password, String name, String email, String home_address){
        userRepository.createUser(username, password, name, email, home_address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity editUser(String username, String password, String name, String email, String home_address) {
        List<User> optionalUser = userRepository.getUserByUsername(username);
        for (User user : optionalUser) {
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            user.setAddress(home_address);
        }

        userRepository.saveAll(optionalUser);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity createCard(String username, String card_number, Date expiration_date, String cvv){
        cardRepository.createCard(username, card_number, expiration_date, cvv);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    KENNETH ENDPOINT SERVICE
    public ResponseEntity addNewBook(String ISBN, String title, String description, Double price, String author, String genre, String publisher, Integer year, Integer copies_sold){
        bookstoreRepo.addNewBook(ISBN, title, description, price, author, genre, publisher, year, copies_sold);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Book> findBooksByPublisher(String publisher) {
        return bookstoreRepo.findByPublisherName(publisher);
    }

    public ResponseEntity updateBooksDiscountByPublisher(String publisherName, Double discountPercent) {
        List<Book> books = bookstoreRepo.findByPublisherName(publisherName);

        for (Book book : books) {
            book.setDiscountPercent(discountPercent);
        }

        bookstoreRepo.saveAll(books);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

