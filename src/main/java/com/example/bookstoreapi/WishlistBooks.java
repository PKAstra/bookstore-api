package com.example.bookstoreapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wishlist_books")
@Entity
public class WishlistBooks {
    @Id
    private Integer id;
    private Integer wishlist_id;
    private Integer book_id;

    public WishlistBooks() {
    }

    public WishlistBooks(Integer id, Integer wishlist_id, Integer book_id) {
        this.id = id;
        this.wishlist_id = wishlist_id;
        this.book_id = book_id;
    }

    public WishlistBooks(Integer wishlist_id, Integer book_id) {
        this.wishlist_id = wishlist_id;
        this.book_id = book_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(Integer wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
