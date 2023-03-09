package com.example.bookstoreapi.ShoppingCart;

import com.example.bookstoreapi.BookstoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bookstore/shopping-cart")
public class ShoppingCartController
{
    private ShoppingCartService shoppingCartService;
    private BookstoreService bookstoreService;
    private CustomerService customerService;

    //Constructors
    public ShoppingCartController(ShoppingCartService shoppingCartService, BookstoreService bookstoreService, CustomerService customerService)
    {
        this.shoppingCartService = shoppingCartService;
        this.bookstoreService = bookstoreService;
        this.customerService = customerService;
    }

    private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    //Mapping Functions
    @GetMapping(value = "/{userid}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int userid)
    {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userid);
        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping("/addbook")
    public ResponseEntity<ResponseShoppingCartDTO> addBook(@RequestBody List<ShoppingCartItem> items)
    {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO(1, items);
        logger.info("Request Payload " + shoppingCartDTO.toString());
        ResponseShoppingCartDTO responseShoppingCartDTO = new ResponseShoppingCartDTO();
        double subtotal = shoppingCartService.getShoppingCartSubtotal(shoppingCartDTO.getItems());

        Customer customer = new Customer(shoppingCartDTO.getCustomerID());
        Integer customerIdFromDb = customerService.isCustomerPresent(customer);
        //Customer Exists I.E. Has a Shopping Cart
        if (customerIdFromDb != null) {
            customer.setId(customerIdFromDb);
            logger.info("Customer already present in db with id : " + customerIdFromDb);
        }
        //Customer Does Not Exist
        else
        {
            customer = customerService.saveCustomer(customer);
            logger.info("Customer saved.. with id : " + customer.getId());
        }

        ShoppingCart shoppingCart = new ShoppingCart(customer, shoppingCartDTO.getItems());
        shoppingCart = shoppingCartService.saveOrder(shoppingCart);
        logger.info("Order processed successfully..");

        responseShoppingCartDTO.setShoppingCartSubtotal(subtotal);
        responseShoppingCartDTO.setCustomerID(shoppingCartDTO.getCustomerID());
        responseShoppingCartDTO.setOrderID(shoppingCart.getId());
        //ResponseShoppingCartDTO.setCustomerID(shoppingCartDTO.getCustomerID());


        logger.info("test push..");

        return ResponseEntity.ok(responseShoppingCartDTO);
    }

}
