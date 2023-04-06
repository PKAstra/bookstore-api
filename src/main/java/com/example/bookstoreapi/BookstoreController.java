
package com.example.bookstoreapi;


import java.util.List;
import java.util.ArrayList;

import com.example.bookstoreapi.Order.Order;
import com.example.bookstoreapi.Order.OrderDTO;
import com.example.bookstoreapi.Order.ResponseOrderDTO;
import com.example.bookstoreapi.ShoppingCart.ResponseShoppingCartDTO;
import com.example.bookstoreapi.ShoppingCart.ShoppingCart;
import com.example.bookstoreapi.ShoppingCart.ShoppingCartDTO;
import com.example.bookstoreapi.ShoppingCart.ShoppingCartItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

    @Autowired
    private BookstoreService bookstoreService;


    @GetMapping("/getAllBooks")
    public ResponseEntity getAllBooks(){
        return new ResponseEntity(this.bookstoreService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getAllBooksWishlist")
    public ResponseEntity getAllBooksWishlist(){
        return new ResponseEntity(this.bookstoreService.getAllBooksWishlist(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllBooksFromWishlist")
    public ResponseEntity getAllBooksFromWishlist(){
        return new ResponseEntity(this.bookstoreService.getAllBooksFromWishlist(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeBookWishlist")
    public ResponseEntity deleteBookWishlist(
            @RequestParam(value = "wishlist_id") Integer wishlist_id,
            @RequestParam(value = "book_id") Integer book_id){
        return this.bookstoreService.deleteBookWishlist(wishlist_id, book_id);
    }

    @PostMapping("/addBookWishlist")
    public ResponseEntity addBookWishlist(
            @RequestParam(value = "wishlist_id") Integer wishlist_id,
            @RequestParam(value = "book_id") Integer book_id){
        return this.bookstoreService.addBookWishlist(wishlist_id, book_id);
    }

    @PostMapping("/addWishlist")
    public ResponseEntity addWishlist(
            @RequestParam(value = "wishlist_name") String wishlist_name,
            @RequestParam(value = "user_id") Integer user_id){
        return this.bookstoreService.addWishlist(wishlist_name, user_id);
    }

    @GetMapping("/getAllBookComments")
    public ResponseEntity getAllBookComments(){
        return new ResponseEntity(this.bookstoreService.getAllBookComments(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBooksByGenre/{genre}")
    public ResponseEntity getBooksByGenre(@PathVariable("genre") String genre){
        return this.bookstoreService.findBooksByGenre(genre);
    }

    @GetMapping("/getTop10BooksSold")
    public ResponseEntity getTop10BooksSold(){
        return this.bookstoreService.findTop10BooksSold();
    }

    @GetMapping("/getBooksByRating/{rating}")
    public ResponseEntity getBooksByRating(@PathVariable("rating") Double rating){
        return this.bookstoreService.findBooksByRating(rating);
    }
    
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity(this.bookstoreService.getUserByUsername(username), HttpStatus.ACCEPTED);
    }
    @PostMapping("/createUser")
    public ResponseEntity createUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password")  String password,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "home_address") String home_address){
        return this.bookstoreService.createUser(username, password, name, email, home_address);
    }

//    KENNETH ENDPOINT CONTROLLER
    @PostMapping("/addBook")
    public ResponseEntity addBook(
            @RequestParam(value = "ISBN") String ISBN,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "price") Double price,
            @RequestParam(value = "author") String author,
            @RequestParam(value = "genre") String genre,
            @RequestParam(value = "publisher") String publisher,
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "copies_sold") Integer copies_sold){
        return new ResponseEntity(this.bookstoreService.addNewBook(ISBN, title, description, price, author, genre, publisher, year, copies_sold), HttpStatus.OK);
    }



//SHOPPING CART CONTROLLER

    //Retrieves User's Shopping Cart
    @GetMapping("/shopping-cart/user-{userid}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int userid)
    {
        ShoppingCart shoppingCart = this.bookstoreService.getShoppingCart(userid);
        return ResponseEntity.ok(shoppingCart);
    }

    //Retrieves User's Shopping Cart Subtotal
    @GetMapping( "/shopping-cart/user-{userid}/subtotal")
    public ResponseEntity<String> getShoppingCartSubtotal(@PathVariable int userid)
    {
        ShoppingCart shoppingCart = this.bookstoreService.getShoppingCart(userid);
        double subtotal = this.bookstoreService.getShoppingCartSubtotal(shoppingCart.getItems());

        String cartSubtotal = "Shopping Cart Subtotal: $" + subtotal;
        return ResponseEntity.ok(cartSubtotal);
    }
    //Adds Book to Shopping Cart
    @PostMapping("/shopping-cart/addbook")
    public ResponseEntity<ResponseShoppingCartDTO> addBook(@RequestBody ShoppingCartDTO cart)
    {
        int customerID = cart.getCustomerID();
        //Shopping Cart Item List
        List<ShoppingCartItems> shoppingCartItems = new ArrayList<>();


        //If Customer Does NOT have an Existing Shopping Cart
        if (this.bookstoreService.getShoppingCart(customerID) == null)
        {
            //Adds Book to Shopping Cart Based Off bookID
            shoppingCartItems.add(this.bookstoreService.addBookForNewCustomer(cart.getItems()));
        }
        //If Customer Already has an Existing Shopping Cart
        else
        {
            ShoppingCart shoppingCart = this.bookstoreService.getShoppingCart(customerID);
            //Adds New Book to Shopping Cart List Along With Existing Items
            shoppingCartItems = this.bookstoreService.addBookForExistingCustomer(shoppingCart, cart.getItems());
        }


        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO(customerID, shoppingCartItems);

        //Saves Updated Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart(customerID, shoppingCartItems);
        this.bookstoreService.saveShoppingCart(shoppingCart);


        //Response
        ResponseShoppingCartDTO responseShoppingCartDTO = new ResponseShoppingCartDTO();
        responseShoppingCartDTO.setCustomerID(shoppingCartDTO.getCustomerID());


        return ResponseEntity.ok(responseShoppingCartDTO);
    }

    //Deletes Book from Shopping Cart
    @DeleteMapping("/shopping-cart/deletebook/user-{userid}/book-{bookid}")
    public ResponseEntity<String> deleteBook(@PathVariable int userid, @PathVariable int bookid)
    {
        String status = "Book Deleted...";

        //Retrieves User's Shopping Cart
        ShoppingCart shoppingCart =  this.bookstoreService.getShoppingCart(userid);
        List<ShoppingCartItems> shoppingCartItems = new ArrayList<>();
        //Deletes Requested Book
        shoppingCartItems = this.bookstoreService.deleteBook(shoppingCart.getItems(), bookid);
        //Saves New Shopping Cart
        shoppingCart.setItems(shoppingCartItems);
        this.bookstoreService.saveShoppingCart(shoppingCart);

        return ResponseEntity.ok(status);
    }


//ORDER CONTROLLER

    //Retrieves Order by Order ID
    @GetMapping("/getOrder/{orderID}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderID)
    {
        Order order = this.bookstoreService.getOrderDetail(orderID);
        return ResponseEntity.ok(order);
    }

    //Retrieves Order by Order ID
    @GetMapping("/getOrder/customer-{userID}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable int userID)
    {
        List<Order> customerOrders = this.bookstoreService.getCustomerOrders(userID);
        return ResponseEntity.ok(customerOrders);
    }


    //Place Order by Customer ID
    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO order)
    {
        //Creates and Saves a New Order
        Order newOrder = this.bookstoreService.placeOrder(order.getCustomerID());
        this.bookstoreService.saveOrder(newOrder);
        //Response
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO(newOrder.getId(), newOrder.getCustomerID(), newOrder.getOrderTotal());

        return ResponseEntity.ok(responseOrderDTO);
    }
}
