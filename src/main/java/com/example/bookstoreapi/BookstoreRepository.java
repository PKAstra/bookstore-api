
package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookstoreRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b WHERE b.genre = ?1")
    public List<Book> findBookByGenre(String genre);

}