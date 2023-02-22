package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Account;
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

    @Autowired
    public AccountRestController(AccountService accountService) {this.accountService = accountService;}

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
    public Account addAccount(@RequestBody Account theAccount) {
        theAccount.setId(0);
        accountService.save(theAccount);

        return theAccount;
    }

    @PutMapping("/accounts")
    public Account updateAccount(@RequestBody Account theAccount) {

        accountService.save(theAccount);
        return theAccount;
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
