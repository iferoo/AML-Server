package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Bank;
import com.datagear.amlserver.entity.Branch.Branch;
import com.datagear.amlserver.entity.Employee.Employee;
import com.datagear.amlserver.entity.Employee.EmployeeClass;
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
    private BankService bankService;
    private BranchService branchService;

    @Autowired
    public EmployeeRestController(
            EmployeeService employeeService,
            BankService bankService,
            BranchService branchService
    ) {
        this.employeeService = employeeService;
        this.bankService = bankService;
        this.branchService = branchService;
    }

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
    public Employee addEmployee(@RequestBody EmployeeClass theEmployeeClass) {

        Optional<Branch> branch = branchService.findById(theEmployeeClass.getBranch());

        Employee employee = new Employee(
                0,
                theEmployeeClass.getName(),
                theEmployeeClass.getSalary(),
                theEmployeeClass.getEmail(),
                branch.get());

        employeeService.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody EmployeeClass theEmployeeClass) {

        Optional<Branch> branch = branchService.findById(theEmployeeClass.getBranch());

        Optional<Employee> employee = employeeService.findById(theEmployeeClass.getId());
        Employee newEmployee = employee.get();
        if (employee.isEmpty()) {

        } else {

            newEmployee.setName(theEmployeeClass.getName());
            newEmployee.setSalary(theEmployeeClass.getSalary());
            newEmployee.setEmail(theEmployeeClass.getEmail());
            newEmployee.setBranch(branch.get());

            employeeService.save(newEmployee);
        }
        return newEmployee;
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
