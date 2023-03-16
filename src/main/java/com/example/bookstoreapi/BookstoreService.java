
package com.example.bookstoreapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookstoreService {
    
    @Autowired
    BookstoreRepository bookstoreRepo;
    @Autowired
    BookstoreWishlist bookstoreWish;
    @Autowired
    BookstoreWishlistBooks bookstoreWishlistBooks;
    @Autowired
    BookstoreComment bookstoreComment;
    @Autowired
    BookstoreRating bookstoreRating;
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

    public List<Comment> getAllBookComments(){
        // logic
        return bookstoreComment.findAll();
    }

    public List<Rating> getAllBookRatings(){
        return  bookstoreRating.findAll();
    }

    public List<Comment> getCommentsForBook(Integer book_id){
        List<Comment> comments = bookstoreComment.findAll();
        List<Comment> commentsForBook = new ArrayList<>();

        for (Comment comment : comments) {
            if (comment.getBook_id().equals(book_id)){
                commentsForBook.add(comment);
            }
        }
        return commentsForBook;
    }

}
