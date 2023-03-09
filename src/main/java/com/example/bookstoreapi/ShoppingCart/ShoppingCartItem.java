package com.example.bookstoreapi.ShoppingCart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int bookID;
    private String bookName;
    private double bookPrice;

    //Constructors
    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int bookID)
    {
        this.bookID = bookID;
    }

    public ShoppingCartItem(int bookID, String bookName, int bookPrice) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                ", bookId=" + bookID +
                '}';
    }
}
