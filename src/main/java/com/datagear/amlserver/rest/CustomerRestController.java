package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Customer;
import com.datagear.amlserver.service.bank.BankService;
import com.datagear.amlserver.service.branch.BranchService;
import com.datagear.amlserver.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private CustomerService customerService;
    private BankService bankService;
    private BranchService branchService;

    @Autowired
    public CustomerRestController(
            CustomerService customerService,
            BankService bankService,
            BranchService branchService
    ) {
        this.customerService = customerService;
        this.bankService = bankService;
        this.branchService = branchService;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customersId}")
    public Customer getCustomer(@PathVariable int customersId) {

        Optional<Customer> employee = customerService.findById(customersId);

        if (employee.isPresent()) {
            Customer theCustomer = employee.get();
            return theCustomer;
        } else {
            throw new RuntimeException("Bank id not found - " + customersId);
        }
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        theCustomer.setId(0);

        customerService.save(theCustomer);

        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        customerService.save(theCustomer);

        return theCustomer;
    }

    @DeleteMapping("/customers/{customersId}")
    public String deleteCustomer(@PathVariable int customersId) {

        Optional<Customer> employee = customerService.findById(customersId);

        // throw exception if null

        if (employee.isEmpty()) {
            throw new RuntimeException("Customer id not found - " + customersId);
        }

        customerService.deleteById(customersId);

        return "Deleted employee id - " + customersId;
    }
}
