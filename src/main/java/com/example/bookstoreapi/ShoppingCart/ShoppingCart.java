package com.example.bookstoreapi.ShoppingCart;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart
{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCartItem.class)
    @JoinColumn(name = "shoppingcart_id", referencedColumnName = "id")
    private List<ShoppingCartItem> items;


    //Constructors
    public ShoppingCart() {
    }


    public ShoppingCart(int id, List<ShoppingCartItem> items) {
        this.id = id;
        this.items = items;
    }

    public int getCustomerID() {
        return id;
    }

    public void setCustomerID(int customerID) {
        this.id = customerID;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }


}
