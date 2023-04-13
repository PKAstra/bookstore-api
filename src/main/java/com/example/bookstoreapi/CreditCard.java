//Programmed By: Laeito Riley

package com.example.bookstoreapi;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "credit"
@Entity
public class CreditCard {

    @Id
    private String username;
    private String card_number;
    private Date expiration_date;
    private String cvv;

    /*public CreditCard(String username, String card_number, Date expiration_date, String cvv) {
        this.username = username;
        this.card_number = card_number;
        this.expiration_date = expiration_date;
        this.cvv = cvv;
    }

     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }


    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}


