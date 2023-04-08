
package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookstoreRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b WHERE b.genre = ?1")
    public List<Book> findBookByGenre(String genre);

    @Query(value = "SELECT * FROM books ORDER BY copies_sold DESC LIMIT 10", nativeQuery = true)
    public List<Book> findTop10BooksByCopiesSold();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO books (bookId, isbn, title, description, author, genre, price, copies_sold, publisher, year_published) VALUES (?1, ?2, ?3, ?5, ?6, ?4, ?9, ?7, ?8, 6)", nativeQuery = true)
    void addNewBook(Integer bookId, String isbn, String title, String description, String author, String genre, Double price, Integer copies_sold, String publisher, Integer yearPublished);

    public List<Book> findBooksByRating(Double rating);
}