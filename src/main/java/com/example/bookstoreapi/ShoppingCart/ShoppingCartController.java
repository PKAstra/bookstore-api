package com.example.bookstoreapi.ShoppingCart;

import com.example.bookstoreapi.BookstoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/bookstore/shopping-cart")
public class ShoppingCartController
{
    private ShoppingCartService shoppingCartService;


    //Constructors
    public ShoppingCartController(ShoppingCartService shoppingCartService)
    {
        this.shoppingCartService = shoppingCartService;
    }


    //Mapping Functions
    //Retrieves User's Shopping Cart
    @GetMapping(value = "/user-{userid}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int userid)
    {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userid);
        return ResponseEntity.ok(shoppingCart);
    }

    //Retrieves User's Shopping Cart Subtotal
    @GetMapping(value = "/user-{userid}/subtotal")
    public ResponseEntity<String> getShoppingCartSubtotal(@PathVariable int userid)
    {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userid);
        double subtotal = shoppingCartService.getShoppingCartSubtotal(shoppingCart.getItems());

        String cartSubtotal = "Shopping Cart Subtotal: $" + subtotal;
        return ResponseEntity.ok(cartSubtotal);
    }
    //Adds Book to Shopping Cart
    @PostMapping("/addbook")
    public ResponseEntity<ResponseShoppingCartDTO> addBook(@RequestBody  ShoppingCartDTO cart)
    {
        int customerID = cart.getCustomerID();
        //Shopping Cart Item List
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();


        //If Customer Does NOT have an Existing Shopping Cart
        if (shoppingCartService.getShoppingCart(customerID) == null)
        {
            //Adds Book to Shopping Cart Based Off bookID
            shoppingCartItems.add(shoppingCartService.addBookForNewCustomer(cart.getItems()));
        }
        //If Customer Already has an Existing Shopping Cart
        else
        {
            ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(customerID);
            //Adds New Book to Shopping Cart List Along With Existing Items
            shoppingCartItems = shoppingCartService.addBookForExistingCustomer(shoppingCart, cart.getItems());
        }


        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO(customerID, shoppingCartItems);

        //Saves Updated Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart(customerID, shoppingCartItems);
        shoppingCartService.saveShoppingCart(shoppingCart);


        //Response
        ResponseShoppingCartDTO responseShoppingCartDTO = new ResponseShoppingCartDTO();
        responseShoppingCartDTO.setCustomerID(shoppingCartDTO.getCustomerID());


        return ResponseEntity.ok(responseShoppingCartDTO);
    }


    @DeleteMapping("/deletebook/user-{userid}/book-{bookid}")
    public ResponseEntity<String> deleteBook(@PathVariable int userid, @PathVariable int bookid)
    {
        String status = "Book Deleted...";

        //Retrieves User's Shopping Cart
       ShoppingCart shoppingCart =  shoppingCartService.getShoppingCart(userid);
       List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
       //Deletes Requested Book
       shoppingCartItems = shoppingCartService.deleteBook(shoppingCart.getItems(), bookid);
       //Saves New Shopping Cart
       shoppingCart.setItems(shoppingCartItems);
       shoppingCartService.saveShoppingCart(shoppingCart);

        return ResponseEntity.ok(status);
    }
}
