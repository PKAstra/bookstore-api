
package com.example.bookstoreapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "books")
@Entity
public class Book {

    @Id
    private Integer bookId;
    private String isbn;
    private String title;
    private String description;
    private String author;
    private String genre;
    private Double price;
    private Integer copiesSold;
    private Double rating;
    private String publisher;
    private Integer yearPublished;
    private Double discountPercent;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        if (discountPercent > 1.0 || discountPercent < 0.0) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 1");
        }

        if (this.discountPercent == 0.0) {
            // No existing discount, so just set the new discount
            this.discountPercent = discountPercent;
        } else if (discountPercent == 0.0) {
            // Revert back to the original price
            double originalPrice = this.price / (1 - this.discountPercent);
            this.discountPercent = discountPercent;
            this.price = originalPrice;
        } else {
            // There is an existing discount, so calculate the original price
            double originalPrice = this.price / (1 - this.discountPercent);
            // Calculate the new discount based on the original price and the new discount
            double newDiscount = 1 - (originalPrice * (1 - discountPercent) / this.price);
            this.discountPercent = newDiscount;
        }

        // Recalculate the price with the new discount
        if (this.price != 0.0) {
            this.price = calculateDiscountedPrice(this.price, this.discountPercent);
        }
    }
    public Double calculateDiscountedPrice(Double price, Double discountPercent) {
        return price * (1 - discountPercent);
    }
}
