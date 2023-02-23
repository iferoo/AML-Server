package com.datagear.amlserver.service.customer;


import com.datagear.amlserver.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer findById(int customerId);

    public void save(Customer theCustomer);

    public Customer deleteById(int customerId);
}
