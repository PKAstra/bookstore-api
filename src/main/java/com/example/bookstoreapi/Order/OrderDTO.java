package com.example.bookstoreapi.Order;

import java.util.List;

public class OrderDTO
{
    private int customerID;

    //Constructors
    public OrderDTO() {
    }

    public OrderDTO(int customerID)
    {
        this.customerID = customerID;
    }

    //Setters and Getters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }


}
