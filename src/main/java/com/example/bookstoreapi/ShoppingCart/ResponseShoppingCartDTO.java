package com.example.bookstoreapi.ShoppingCart;

public class ResponseShoppingCartDTO
{

    private int customerID;
    private String status = "Book(s) Added";
    private int shoppingCartID;
    private int orderID;
    private double shoppingCartSubtotal;

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

    public int getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(int shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getShoppingCartSubtotal() {
        return shoppingCartSubtotal;
    }

    public void setShoppingCartSubtotal(double shoppingCartSubtotal) {
        this.shoppingCartSubtotal = shoppingCartSubtotal;
    }
}
