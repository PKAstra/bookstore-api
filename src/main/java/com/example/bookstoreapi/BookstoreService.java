
package com.example.bookstoreapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
