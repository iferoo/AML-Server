package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Transaction.Transaction;
import com.datagear.amlserver.entity.Transaction.TransactionClass;
import com.datagear.amlserver.service.account.AccountService;
import com.datagear.amlserver.service.bank.BankService;
import com.datagear.amlserver.service.branch.BranchService;
import com.datagear.amlserver.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TransactionRestController {
    private TransactionService transactionService;
    private AccountService accountService;
    private BankService bankService;
    private BranchService branchService;

    @Autowired
    public TransactionRestController(
            TransactionService transactionService,
            BankService bankService,
            BranchService branchService
    ) {
        this.transactionService = transactionService;
        this.bankService = bankService;
        this.branchService = branchService;
    }

    @GetMapping("/transactions")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/{transactionsId}")
    public Transaction getTransaction(@PathVariable int transactionsId) {

        Optional<Transaction> transaction = transactionService.findById(transactionsId);

        if (transaction.isPresent()) {
            Transaction theTransaction = transaction.get();
            return theTransaction;
        } else {
            throw new RuntimeException("Bank id not found - " + transactionsId);
        }
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction theTransaction) {

        transactionService.save(theTransaction);

        return theTransaction;
    }
    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction theTransaction) {

        transactionService.save(theTransaction);

        return theTransaction;
    }



    @DeleteMapping("/transactions/{transactionsId}")
    public Transaction deleteTransaction(@PathVariable int transactionsId) {

        Optional<Transaction> transaction = transactionService.findById(transactionsId);

        // throw exception if null

        if (transaction.isEmpty()) {
            throw new RuntimeException("Transaction id not found - " + transactionsId);
        }

        transactionService.deleteById(transactionsId);

        return transaction.get();
    }
}