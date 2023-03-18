//Programmed By: Laeito Riley

package com.example.bookstoreapi;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class User {

    @Id
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String home_address;
    //private Integer credit_card;

    /*
    public User(String username, String password, String email, String name, String home_address)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.home_address = home_address;
    }

     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer bookId) {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return home_address;
    }

    public void setAddress(String address)
    {
        this.home_address = home_address;
    }

    /* public int getCreditCard()
    {
        return credit_card;
    }

    public void setCreditCard(Integer creditCard)
    {
        this.credit_card = creditCard;
    }
    */

}
