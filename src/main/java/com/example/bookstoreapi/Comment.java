
package com.example.bookstoreapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "comments")
@Entity
public class Comment{

    @Id
    private String comment;
    private Integer book_id;
    private Integer user_id;
    private Timestamp timestamp;
    public Comment() {
    }

    public Comment(String comment, Integer book_id, Integer user_id, Timestamp timestamp) {
        this.comment = comment;
        this.book_id = book_id;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}

