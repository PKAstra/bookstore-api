package com.example.bookstoreapi.ShoppingCart;

import com.example.bookstoreapi.Book;
import com.example.bookstoreapi.BookstoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService
{
    private ShoppingCartRepo shoppingCartRepo;
    private BookstoreRepository bookstoreRepository;

    //Constructor
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, BookstoreRepository bookstoreRepository)
    {
        this.shoppingCartRepo = shoppingCartRepo;
        this.bookstoreRepository = bookstoreRepository;
    }


    //Gets
    public ShoppingCart getShoppingCart(int userID) {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepo.findById(userID);
        return shoppingCart.isPresent() ? shoppingCart.get() : null;
    }

    public List<ShoppingCartItem> addBookForExistingCustomer(ShoppingCart shoppingCart, List<ShoppingCartItem> newBook)
    {

        //Creates a New Shopping Cart List
        List<ShoppingCartItem> shoppingCartItems;
        //Fills New Shopping Cart List with Previous Items in Cart
        shoppingCartItems = shoppingCart.getItems();
        //Adds New Item to New Shopping Cart List
        for(ShoppingCartItem tempBook : newBook)
        {
            //Creates Book Object Based Off bookID
            int bookID = tempBook.getBookID();
            Optional<Book> book = bookstoreRepository.findById(bookID);
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(book);
            shoppingCartItems.add(shoppingCartItem);
        }

        //Returns New Shopping Cart List
        return shoppingCartItems;

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
    //Finds Book, Adds to Shopping Cart, and Returns Cart
    public ShoppingCartItem addBookForNewCustomer(List<ShoppingCartItem> cart)
    {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        for(ShoppingCartItem tempCart : cart)
        {
            int bookID = tempCart.getBookID();
            Optional<Book> book = bookstoreRepository.findById(bookID);
            shoppingCartItem = new ShoppingCartItem(book);
        }
        return shoppingCartItem;
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart)
    {
        return shoppingCartRepo.save(shoppingCart);
    }
}
