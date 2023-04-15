package com.example.bookstoreapi.ShoppingCart;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart
{
    @Id
    @Column(name = "customer_id")
    private int customerID;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCartItems.class)
    @JoinColumn(name = "shoppingcart_id", referencedColumnName = "customer_id")
    private List<ShoppingCartItems> items;


    //Constructors
    public ShoppingCart() {
    }


    public ShoppingCart(int id, List<ShoppingCartItems> items) {
        this.customerID = id;
        this.items = items;
    }

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
