package com.example.bookstoreapi.ShoppingCart;

public class ResponseShoppingCartDTO
{

    private int customerID;
    private String status = "Book Added";
    private double shoppingCartSubtotal;

    //Setters and Getters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getShoppingCartSubtotal() {
        return shoppingCartSubtotal;
    }

    public void setShoppingCartSubtotal(double shoppingCartSubtotal) {
        this.shoppingCartSubtotal = shoppingCartSubtotal;
    }
}
