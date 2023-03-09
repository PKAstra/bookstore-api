package com.example.bookstoreapi.ShoppingCart;

import com.example.bookstoreapi.Book;
import com.example.bookstoreapi.BookstoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService
{
    private ShoppingCartRepo shoppingCartRepo;
    private BookstoreRepository bookstoreRepository;

    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, BookstoreRepository bookstoreRepository)
    {
        this.shoppingCartRepo = shoppingCartRepo;
        this.bookstoreRepository = bookstoreRepository;
    }

    public ShoppingCart getShoppingCart(int userID) {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepo.findById(userID);
        return shoppingCart.isPresent() ? shoppingCart.get() : null;
    }

    public ShoppingCart addBook(ShoppingCart item)
    {
        return shoppingCartRepo.save(item);
    }

    public Double getShoppingCartSubtotal(List<ShoppingCartItem> shoppingCartItems)
    {
        Double shoppingCartSubtotal = 0.0;

        //Loops Through Entire List of User "Added" Books (Based off User Input in Postman)
        for(ShoppingCartItem shoppingCartItem : shoppingCartItems)
        {
            int bookID = shoppingCartItem.getBookID();
            Optional<Book> book = bookstoreRepository.findById(bookID);
            //Checks to See if Book ID Entered by User Exist in Database
            if(book.isPresent())
            {
                Book book1 = book.get();
                //Adds Price of Book in Question to Shopping Cart Subtotal
                shoppingCartSubtotal = + shoppingCartSubtotal + book1.getPrice();
                //Adds Book Name, ID, and Price to Shopping Cart
                shoppingCartItem.setBookName(book1.getTitle());
                shoppingCartItem.setBookPrice(book1.getPrice());
            }
        }

        return shoppingCartSubtotal;
    }

    public ShoppingCart saveOrder(ShoppingCart shoppingCart)
    {
        return shoppingCartRepo.save(shoppingCart);
    }
}
