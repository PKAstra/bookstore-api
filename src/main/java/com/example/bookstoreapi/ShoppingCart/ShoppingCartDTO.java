package com.example.bookstoreapi.ShoppingCart;

import java.util.List;

public class ShoppingCartDTO
{
    private int customerID;
    private List<ShoppingCartItem> items;

    //Constructors
    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(int customerID, List<ShoppingCartItem> items) {
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

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "ShoppingCartDTO{" +
                "customerID=" + customerID + '\'' +
                ", items=" + items +
                '}';
    }

}
