package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Bank;
import com.datagear.amlserver.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BankRestController {
    private BankService bankService;

    @Autowired
    public BankRestController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/banks")
    public List<Bank> findAll() {
        return bankService.findAll();
    }

    @GetMapping("/banks/{bankId}")
    public Bank getBank(@PathVariable int bankId) {
        return bankService.findById(bankId);
    }

    @PostMapping("/banks")
    public Bank addBank(@RequestBody Bank theBank) {
        theBank.setId(0);
        bankService.save(theBank);
        return theBank;
    }

    @PutMapping("/banks")
    public Bank updateBank(@RequestBody Bank theBank) {

        bankService.save(theBank);
        return theBank;
    }

    @DeleteMapping("/banks/{bankId}")
    public Bank deleteBank(@PathVariable int bankId) {
        return bankService.deleteById(bankId);
    }
}
