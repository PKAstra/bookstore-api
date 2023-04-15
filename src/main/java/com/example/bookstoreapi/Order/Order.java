package com.example.bookstoreapi.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "customer_id")
    private int customerID;

    private double orderTotal;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = OrderItems.class)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderItems> items;

    //Constructors
    public Order() {
    }


    public Order(int orderID, int customerID, List<OrderItems> items, double orderTotal) {
        this.orderID = orderID;
        this.orderTotal = orderTotal;
        this.items = items;
        this.customerID = customerID;
    }

    public int getId() {
        return orderID;
    }

    public void setId(int id) {
        this.orderID = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

}
