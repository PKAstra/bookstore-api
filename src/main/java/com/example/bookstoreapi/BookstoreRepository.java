
package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookstoreRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b WHERE b.genre = ?1")
    public List<Book> findBookByGenre(String genre);

    @Query(value = "SELECT * FROM books ORDER BY copies_sold DESC LIMIT 10", nativeQuery = true)
    public List<Book> findTop10BooksByCopiesSold();

    @Query(value = "SELECT b FROM Book b WHERE b.rating >= ?1")
    public List<Book> findBooksByRating(Double rating);
}