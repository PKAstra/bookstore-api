
package com.example.bookstoreapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "ratings")
@Entity
public class Rating{

    @Id
    private Integer id;
    private Double rating;
    private Integer book_id;
    private Integer user_id;
    private Timestamp timestamp;
    public Rating() {
    }

    public Rating(Integer id, Double rating, Integer book_id, Integer user_id, Timestamp timestamp) {
        this.id = id;
        this.rating = rating;
        this.book_id = book_id;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public Rating(Double rating, Integer book_id, Integer user_id, Timestamp timestamp) {
        this.rating = rating;
        this.book_id = book_id;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}