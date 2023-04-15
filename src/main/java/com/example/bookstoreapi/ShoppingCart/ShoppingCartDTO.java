package com.example.bookstoreapi.ShoppingCart;

import java.util.List;

public class ShoppingCartDTO
{
    private int customerID;
    private List<ShoppingCartItems> items;

    //Constructors
    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(int customerID, List<ShoppingCartItems> items) {
        this.customerID = customerID;
        this.items = items;
    }

    //Setters and Getters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<ShoppingCartItems> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItems> items) {
        this.items = items;
    }


}
