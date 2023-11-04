package com.datagear.amlserver.repository;

import com.datagear.amlserver.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
