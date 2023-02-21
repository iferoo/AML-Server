package com.datagear.amlserver.service.customer;


import com.datagear.amlserver.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> findAll();

    public Optional<Customer> findById(int theId);

    public void save(Customer theCustomer);

    public void deleteById(int theId);
}
