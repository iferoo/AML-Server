package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Customer;
import com.datagear.amlserver.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {
    private CustomerService customerService;


    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        return customerService.findById(customerId);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        theCustomer.setCustomerId(0);
        customerService.save(theCustomer);
        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        customerService.save(theCustomer);
        return theCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public Customer deleteCustomer(@PathVariable int customerId) {
        return customerService.deleteById(customerId);
    }
}
