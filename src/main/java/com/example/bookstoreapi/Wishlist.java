
package com.example.bookstoreapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wishlist")
@Entity
public class Wishlist {

    @Id
    private Integer id;
    private Integer user_id;
    private String wishlist_name;

    public Wishlist() {
    }

    public Wishlist(Integer id, Integer user_id, String wishlist_name) {
        this.id = id;
        this.user_id = user_id;
        this.wishlist_name = wishlist_name;
    }

    public Wishlist(Integer user_id, String wishlist_name) {
        this.user_id = user_id;
        this.wishlist_name = wishlist_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getWishlist_name() {
        return wishlist_name;
    }

    public void setWishlist_name(String wishlist_name) {
        this.wishlist_name = wishlist_name;
    }
}
