package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Transfer;
import com.datagear.amlserver.service.account.AccountService;
import com.datagear.amlserver.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferRestController {
    private TransferService transferService;
    private AccountService accountService;

    @Autowired
    public TransferRestController(TransferService transferService, AccountService accountService) {
        this.transferService = transferService;
        this.accountService = accountService;
    }

    @GetMapping("/transfers")
    public List<Transfer> findAll() {
        return transferService.findAll();
    }

    @GetMapping("/transfers/{transferId}")
    public Transfer getTransfer(@PathVariable int transferId) {

        return transferService.findById(transferId);
    }

    @PostMapping("/transfers")
    public Transfer addTransfer(@RequestBody Transfer theTransfer) {
        return transferService.save(theTransfer);
    }
}
