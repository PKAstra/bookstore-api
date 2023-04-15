package com.example.bookstoreapi;

import com.example.bookstoreapi.Order.Order;
import com.example.bookstoreapi.Order.OrderItems;
import com.example.bookstoreapi.Order.OrderRepo;
import com.example.bookstoreapi.ShoppingCart.ShoppingCart;
import com.example.bookstoreapi.ShoppingCart.ShoppingCartItems;
import com.example.bookstoreapi.ShoppingCart.ShoppingCartRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.sql.Timestamp;


@Service
public class BookstoreService {
    private static final Logger logger = LoggerFactory.getLogger(BookstoreService.class);

    @Autowired
    BookstoreRepository bookstoreRepo;
    @Autowired
    BookstoreWishlistRepository bookstoreWish;
    @Autowired
    BookstoreWishlistBooks bookstoreWishlistBooks;
    @Autowired
    BookstoreComment bookstoreComment;
    @Autowired
    BookstoreRating bookstoreRating;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingCartRepo shoppingCartRepo;
    @Autowired
    OrderRepo orderRepo;

    public List<Book> getAllBooks(){
        // logic
        return bookstoreRepo.findAll();
    }

    public List<Wishlist> getAllBooksWishlist(){
        // logic
        return bookstoreWish.findAll();
    }

    public List<WishlistBooks> getAllBooksFromWishlist(Integer wishlist_id){
        // logic
        return bookstoreWishlistBooks.getAllBooksWishlist(wishlist_id);
    }

    public ResponseEntity deleteBookWishlist(Integer wishlist_id, Integer book_id){
        WishlistBooks wishlistBooks = bookstoreWishlistBooks.findBookWishlist(wishlist_id, book_id);

        if(null != wishlistBooks){
            bookstoreWishlistBooks.delete(wishlistBooks);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity addBookWishlist(Integer wishlist_id, Integer book_id){
        bookstoreWishlistBooks.addBookWishlistQuery(wishlist_id, book_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity addWishlist(String wishlist_name, Integer user_id){
        bookstoreWish.addWishlistQuery(wishlist_name, user_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Comment> getAllBookComments(){
        // logic
        return bookstoreComment.findAll();
    }

    public ResponseEntity<?> findBooksByGenre(String genre){
        logger.info("Books with genre to find: " + genre);
        List<Book> books = bookstoreRepo.findBookByGenre(genre);

        if(!books.isEmpty()){
            logger.info("Books found in db");
            return new ResponseEntity<>(books, HttpStatus.OK);
        }else{
            logger.error("Books not found with genre: " + genre);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    public ResponseEntity<?> findTop10BooksSold(){
        logger.info("Top 10 best selling books: ");
        List<Book> books = bookstoreRepo.findTop10BooksByCopiesSold();
        if(!books.isEmpty()){
            logger.info("Books found in db.");
            return new ResponseEntity<>(books, HttpStatus.OK);
        }else{
            logger.error("Books not found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<?> findBooksByRating(Double rating) {
        logger.info("Books with rating " + rating + " or higher:");
        List<Book> books = bookstoreRepo.findBooksByRating(rating);

        if (!books.isEmpty()) {
            logger.info("Books found in db");
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            logger.error("No books found with rating " + rating + " or higher");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<?> getUserByUsername(String username){
        //  logic
        logger.info("Matching Users:");
        List<User> users = userRepository.getUserByUsername(username);


        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    public ResponseEntity createUser(String username, String password, String name, String email, String home_address){
        userRepository.createUser(username, password, name, email, home_address);
        return new ResponseEntity<>(HttpStatus.OK);
    }


  //    KENNETH ENDPOINT SERVICE
    public ResponseEntity addNewBook(String ISBN, String title, String description, Double price, String author, String genre, String publisher, Integer year, Integer copies_sold){
        bookstoreRepo.addNewBook(ISBN, title, description, price, author, genre, publisher, year, copies_sold);
        return new ResponseEntity<>(HttpStatus.OK);
    }

 public List<Book> findBooksByPublisher(String publisher) {
        return bookstoreRepo.findByPublisherName(publisher);
    }

    public ResponseEntity updateBooksDiscountByPublisher(String publisherName, Double discountPercent) {
        List<Book> books = bookstoreRepo.findByPublisherName(publisherName);

        for (Book book : books) {
            book.setDiscountPercent(discountPercent);
        }

        bookstoreRepo.saveAll(books);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//SHOPPING CART SERVICE

    //Retrieves User's Shopping Cart
    public ShoppingCart getShoppingCart(int userID) {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepo.findById(userID);
        return shoppingCart.isPresent() ? shoppingCart.get() : null;
    }

    //Adds New Book to Existing Customer's Shopping Cart
    public List<ShoppingCartItems> addBookForExistingCustomer(ShoppingCart shoppingCart, List<ShoppingCartItems> newBook)
    {

        //Creates a New Shopping Cart List
        List<ShoppingCartItems> shoppingCartItems;
        //Fills New Shopping Cart List with Previous Items in Cart
        shoppingCartItems = shoppingCart.getItems();
        //Adds New Item to New Shopping Cart List
        for(ShoppingCartItems tempBook : newBook)
        {
            //Creates Book Object Based Off bookID
            int bookID = tempBook.getBookID();
            Optional<Book> book = bookstoreRepo.findById(bookID);
            ShoppingCartItems shoppingCartItem = new ShoppingCartItems(book);
            shoppingCartItems.add(shoppingCartItem);
        }

        //Returns New Shopping Cart List
        return shoppingCartItems;

    }

    //Calculates Shopping Cart Subtotal
    public Double getShoppingCartSubtotal(List<ShoppingCartItems> shoppingCartItems)
    {
        Double shoppingCartSubtotal = 0.0;

        //Loops Through Entire List of User "Added" Books (Based off User Input in Postman)
        for(ShoppingCartItems shoppingCartItem : shoppingCartItems)
        {
            int bookID = shoppingCartItem.getBookID();
            Optional<Book> book = bookstoreRepo.findById(bookID);
            //Checks to See if Book ID Entered by User Exist in Database
            if(book.isPresent())
            {
                Book book1 = book.get();
                //Adds Price of Book in Question to Shopping Cart Subtotal
                shoppingCartSubtotal = + shoppingCartSubtotal + book1.getPrice();
                //Adds Book Name, ID, and Price to Shopping Cart
                shoppingCartItem.setBookName(book1.getTitle());
                shoppingCartItem.setBookPrice(book1.getPrice());
            }
        }

        return shoppingCartSubtotal;
    }
    //Finds Book, Adds to Shopping Cart, and Returns Cart
    public ShoppingCartItems addBookForNewCustomer(List<ShoppingCartItems> cart)
    {
        ShoppingCartItems shoppingCartItems = new ShoppingCartItems();
        for(ShoppingCartItems tempCart : cart)
        {
            int bookID = tempCart.getBookID();
            Optional<Book> book = bookstoreRepo.findById(bookID);
            shoppingCartItems = new ShoppingCartItems(book);
        }
        return shoppingCartItems;
    }

    public List<ShoppingCartItems> deleteBook(List<ShoppingCartItems> cart, int bookid)
    {
        //Creates a New Shopping Cart List
        List<ShoppingCartItems> shoppingCartItems = new ArrayList<>();

        //Copies Existing Shopping Cart MINUS Deleted Book to New Shopping Cart List
        for(ShoppingCartItems tempBook : cart)
        {
            if(tempBook.getBookID() != bookid)
            {
                shoppingCartItems.add(tempBook);
            }
        }

        //Returns New Shopping Cart List
        return shoppingCartItems;
    }

    //Saves Shopping Cart
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart)
    {
        return shoppingCartRepo.save(shoppingCart);
    }



//ORDER SERVICE

    //Retrieves Order by Order ID
    public Order getOrderDetail(int orderId)
    {
        Optional<Order> order = this.orderRepo.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }

    //Retrieves Customer Orders
    public List<Order> getCustomerOrders(int customerID)
    {
        //Retrieves All Orders
        List<Order> tempOrders = this.orderRepo.findAll();
        //Stores Customer Specific Orders
        List<Order> customerOrders = new ArrayList<>();
        //Loops Through All Orders and Extracts Customer Specific Orders
        for(Order temp:tempOrders)
        {
            if(temp.getCustomerID() == customerID)
            {
                customerOrders.add(temp);
            }
        }
        //Returns Null if No Orders found
        if(customerOrders.isEmpty())
        {
            return null;
        }

        return customerOrders;
    }

    public Order placeOrder(int customerID)
    {
        //Retrieves Customer's Shopping Cart
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepo.findById(customerID);


        //Subtotal
        double subtotal = getShoppingCartSubtotal(shoppingCart.get().getItems());
        //Order Number
        int orderNumber = new Random().nextInt(100);
        while(getOrderDetail(orderNumber) != null)
        {
            //Ensures Order Number is NOT in Use
            orderNumber = new Random().nextInt(100);
        }
        //Items in Order
        List<OrderItems> orderItems = new ArrayList<>();


        //Loops Through User's Shopping Cart, Adding Each Item to the Order
        for(ShoppingCartItems items : shoppingCart.get().getItems())
        {
            int bookID = items.getBookID();
            OrderItems tempOrderItems = new OrderItems(this.bookstoreRepo.findById(bookID));
            orderItems.add(tempOrderItems);
        }

        //Clears Customer's Shopping Cart
        this.shoppingCartRepo.findById(customerID).get().setItems(null);

        //Creates a New Order With All the Items in the User's Cart
        Order newOrder = new Order(orderNumber,customerID,orderItems, subtotal);


        return newOrder;
    }

    //Saves Order
    public Order saveOrder(Order newOrder)
    {
        return orderRepo.save(newOrder);
    }
    
//Ratings and Comments
    public List<Rating> getAllBookRatings(){
        return  bookstoreRating.findAll();
    }

    public List<Comment> getCommentsForBook(Integer book_id){
        List<Comment> comments = bookstoreComment.findAll();
        List<Comment> commentsForBook = new ArrayList<>();

        for (Comment comment : comments) {
            if (comment.getBook_id().equals(book_id)){
                commentsForBook.add(comment);
            }
        }
        return commentsForBook;
    }

    public void createRating(Rating rating) {
        rating.setTimestamp(new Timestamp(System.currentTimeMillis()));
        bookstoreRating.save(rating);
    }

    public Comment createComment(Comment comment) {
        comment.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return bookstoreComment.save(comment);
    }
}

