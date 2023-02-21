package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Account.Account;
import com.datagear.amlserver.entity.Account.AccountClass;
import com.datagear.amlserver.entity.Branch.Branch;
import com.datagear.amlserver.entity.Customer;
import com.datagear.amlserver.entity.Employee.Employee;
import com.datagear.amlserver.service.account.AccountService;
import com.datagear.amlserver.service.branch.BranchService;
import com.datagear.amlserver.service.customer.CustomerService;
import com.datagear.amlserver.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AccountRestController {
    private AccountService accountService;
    private EmployeeService employeeService;
    private CustomerService customerService;
    private BranchService branchService;

    @Autowired
    public AccountRestController(
            AccountService accountService,
            EmployeeService employeeService,
            BranchService branchService,
            CustomerService customerService
    ) {
        this.accountService = accountService;
        this.employeeService = employeeService;
        this.branchService = branchService;
        this.customerService = customerService;
    }

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{accountsId}")
    public Account getAccount(@PathVariable int accountsId) {

        Optional<Account> account = accountService.findById(accountsId);

        if (account.isPresent()) {
            Account theAccount = account.get();
            return theAccount;
        } else {
            throw new RuntimeException("Bank id not found - " + accountsId);
        }
    }

    @PostMapping("/accounts")
    public Account addAccount(@RequestBody AccountClass theAccountClass) {

        Optional<Employee> employee = employeeService.findById(theAccountClass.getEmployee());
        Optional<Branch> branch = branchService.findById(theAccountClass.getBranch());
        Optional<Customer> customer = customerService.findById(theAccountClass.getCustomer());

        Account account = new Account(
                0,
                theAccountClass.getBalance(),
                theAccountClass.getType(),
                employee.get(),
                branch.get(),
                customer.get()
        );
        accountService.save(account);

        return account;
    }

    @PutMapping("/accounts")
    public Account updateAccount(@RequestBody AccountClass theAccountClass) {

            Optional<Employee> employee = employeeService.findById(theAccountClass.getEmployee());
            Optional<Branch> branch = branchService.findById(theAccountClass.getBranch());
            Optional<Customer> customer = customerService.findById(theAccountClass.getCustomer());

            Optional<Account> account = accountService.findById(theAccountClass.getId());

            Account newAccount = account.get();

            if (account.isEmpty()) {

            } else {

                newAccount.setBalance(theAccountClass.getBalance());
                newAccount.setType(theAccountClass.getType());
                newAccount.setEmployee(employee.get());
                newAccount.setBranch(branch.get());
                newAccount.setCustomer(customer.get());

                accountService.save(newAccount);
            }
        return newAccount;
    }

    @DeleteMapping("/accounts/{accountsId}")
    public Account deleteAccount(@PathVariable int accountsId) {

        Optional<Account> account = accountService.findById(accountsId);

        // throw exception if null

        if (account.isEmpty()) {
            throw new RuntimeException("Account id not found - " + accountsId);
        }

        accountService.deleteById(accountsId);

        return account.get();
    }
}
