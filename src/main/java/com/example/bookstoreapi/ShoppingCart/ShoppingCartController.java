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
    private BookstoreService bookstoreService;

    //Constructors
    public ShoppingCartController(ShoppingCartService shoppingCartService, BookstoreService bookstoreService)
    {
        this.shoppingCartService = shoppingCartService;
        this.bookstoreService = bookstoreService;
    }


    //Mapping Functions
    //Retrieves User's Shopping Cart
    @GetMapping(value = "/{userid}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int userid)
    {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userid);
        return ResponseEntity.ok(shoppingCart);
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

        //Calculates Shopping Cart Subtotal
        double subtotal = shoppingCartService.getShoppingCartSubtotal(shoppingCartDTO.getItems());

        ShoppingCart shoppingCart = new ShoppingCart(customerID, shoppingCartItems, subtotal);
        shoppingCartService.saveShoppingCart(shoppingCart);



        ResponseShoppingCartDTO responseShoppingCartDTO = new ResponseShoppingCartDTO();


        responseShoppingCartDTO.setShoppingCartSubtotal(subtotal);
        responseShoppingCartDTO.setCustomerID(shoppingCartDTO.getCustomerID());


        return ResponseEntity.ok(responseShoppingCartDTO);
    }

}
