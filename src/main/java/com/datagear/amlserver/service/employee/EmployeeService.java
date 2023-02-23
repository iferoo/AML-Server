package com.datagear.amlserver.service.employee;

import com.datagear.amlserver.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById(int employeeId);

    public void save(Employee theEmployee);

    public Employee deleteById(int employeeId);
}
