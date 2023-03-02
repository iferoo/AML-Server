package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Transaction;
import com.datagear.amlserver.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TransactionRestController {
    private TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/{transactionId}")
    public Transaction getTransaction(@PathVariable int transactionId) {
        return transactionService.findById(transactionId);
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction theTransaction) {
        theTransaction.setId(0);
        transactionService.save(theTransaction);
        return theTransaction;
    }

}
