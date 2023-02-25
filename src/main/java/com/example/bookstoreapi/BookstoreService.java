
package com.example.bookstoreapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookstoreService {
    
    @Autowired
    BookstoreRepository bookstoreRepo;
    @Autowired
    BookstoreWishlistRepository bookstoreWish;
    @Autowired
    BookstoreWishlistBooks bookstoreWishlistBooks;
    @Autowired
    BookstoreComment bookstoreComment;
    
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

    public List<Comment> getAllBookComments(){
        // logic
        return bookstoreComment.findAll();
    }
}
