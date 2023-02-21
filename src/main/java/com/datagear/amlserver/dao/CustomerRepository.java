package com.datagear.amlserver.dao;

import com.datagear.amlserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
