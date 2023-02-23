package com.datagear.amlserver.service.employee;

import com.datagear.amlserver.dao.EmployeeRepository;
import com.datagear.amlserver.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Bank id not found - " + employeeId);
        }
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public Employee deleteById(int employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(employeeId);
            return employee.get();
        } else {
            // throw exception if null
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

    }
}
