package com.example.bookstoreapi.ShoppingCart;

import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private CustomerRepo customerRepo;

    private CustomerService(CustomerRepo customerRepo)
    {
        this.customerRepo = customerRepo;
    }
    public Customer saveCustomer(Customer customer)
    {
        return customerRepo.save(customer);
    }
    public Integer isCustomerPresent(Customer customer)
    {
        Customer customer1 = customerRepo.getCustomerById(customer.getUserId());
        return customer1!=null ? customer1.getId(): null ;
    }
}
