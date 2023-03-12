package com.example.bookstoreapi.ShoppingCart;

import com.example.bookstoreapi.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class ShoppingCartItem
{
    @Id
    private int bookID;
    private String bookName;
    private double bookPrice;


    //Constructors
    public ShoppingCartItem()
    {

    }
    public ShoppingCartItem(Optional<Book> book)

    {
        Book book1 = book.get();
        this.bookID = book1.getBookId();
        this.bookName = book1.getTitle();
        this.bookPrice = book1.getPrice();

    }

    //Getters and Setters
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
}
