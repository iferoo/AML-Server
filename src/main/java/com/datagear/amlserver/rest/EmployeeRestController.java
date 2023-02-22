package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Employee;
import com.datagear.amlserver.service.bank.BankService;
import com.datagear.amlserver.service.branch.BranchService;
import com.datagear.amlserver.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeesId}")
    public Employee getEmployee(@PathVariable int employeesId) {

        Optional<Employee> employee = employeeService.findById(employeesId);

        if (employee.isPresent()) {
            Employee theEmployee = employee.get();
            return theEmployee;
        } else {
            throw new RuntimeException("Bank id not found - " + employeesId);
        }
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        employeeService.save(theEmployee);

        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {


        employeeService.save(theEmployee);

        return theEmployee;
    }

    @DeleteMapping("/employees/{employeesId}")
    public String deleteEmployee(@PathVariable int employeesId) {

        Optional<Employee> employee = employeeService.findById(employeesId);
        // throw exception if null
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + employeesId);
        }
        employeeService.deleteById(employeesId);

        return "Deleted employee id - " + employeesId;
    }
}
