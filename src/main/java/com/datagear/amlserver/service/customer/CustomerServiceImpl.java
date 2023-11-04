package com.datagear.amlserver.service.customer;

import com.datagear.amlserver.repository.CustomerRepository;
import com.datagear.amlserver.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("Bank id not found - " + customerId);
        }
    }

    @Override
    public void save(Customer theCustomer) {
        customerRepository.save(theCustomer);
    }

    @Override
    public Customer deleteById(int customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            Customer deletedCustomer = customer.get();
            deletedCustomer.setIsDeleted(true);
            deletedCustomer.setDeletedAt(LocalDateTime.now());
            customerRepository.save(deletedCustomer);
            return deletedCustomer;
        } else {
            // throw exception if null
            throw new RuntimeException("Customer id not found - " + customerId);
        }
    }
}
