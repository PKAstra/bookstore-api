package com.example.bookstoreapi.ShoppingCart;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCartItem.class)
    @JoinColumn(name = "shoppingcart_id", referencedColumnName = "id")
    private List<ShoppingCartItem> items;

    //Constructors
    public ShoppingCart() {
    }
    public ShoppingCart(Customer customer, List<ShoppingCartItem> items) {
        this.customer = customer;
        this.items = items;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }
}
