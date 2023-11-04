package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Employee;
import com.datagear.amlserver.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setEmployeeId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        employeeService.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public Employee deleteEmployee(@PathVariable int employeeId) {
        return employeeService.deleteById(employeeId);
    }
}
