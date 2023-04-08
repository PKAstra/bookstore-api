//Programmed By: Laeito R.
package com.example.bookstoreapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO Credit (username, card_number, expiration_date, cvv) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void createCard(String username, String card_number, Date expiration_date, String cvv);
}
