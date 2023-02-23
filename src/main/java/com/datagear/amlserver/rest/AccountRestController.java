package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AccountRestController {
    private AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        return accountService.findById(accountId);
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

    @DeleteMapping("/accounts/{accountId}")
    public Account deleteAccount(@PathVariable int accountId) {
        return accountService.deleteById(accountId);
    }
}
