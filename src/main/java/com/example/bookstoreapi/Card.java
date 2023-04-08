//Programmed By: Laeito R.

package com.example.bookstoreapi;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "credit")
@Entity
public class Card {

    @Id
    private String username;
    private String card_number;
    private Date expiration_date;
    private String cvv;



    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNumber()
    {
        return card_number;
    }

    public void setNumber(String card_number)
    {
        this.card_number = card_number;
    }


    public Date getExpiration()
    {
        return expiration_date;
    }

    public void setExpiration(Date expiration_date)
    {
        this.expiration_date = expiration_date;
    }

    public String getCvv()
    {
        return cvv;
    }

    public void setCvv(String cvv)
    {
        this.cvv = cvv;
    }



}

