package com.example.bookstoreapi.Order;

public class ResponseOrderDTO
{

    private int orderID;
    private int customerID;
    private double orderSubtotal;
    private String status = "Order Confirmed";

    //Constructor


    public ResponseOrderDTO(int orderID, int customerID, double orderSubtotal) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderSubtotal = orderSubtotal;
    }

    //Setters and Getters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(int orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
