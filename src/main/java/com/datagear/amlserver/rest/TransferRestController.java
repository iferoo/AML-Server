package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Transaction;
import com.datagear.amlserver.entity.Transfer;
import com.datagear.amlserver.service.account.AccountService;
import com.datagear.amlserver.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/transfers/{transfersId}")
    public Transfer getTransfer(@PathVariable int transfersId) {

        Optional<Transfer> transfer = transferService.findById(transfersId);
        if (transfer.isPresent()) {
            Transfer theTransfer = transfer.get();
            return theTransfer;
        } else {
            throw new RuntimeException("Bank id not found - " + transfersId);
        }
    }

    @PostMapping("/transfers")
    public Transfer addTransfer(@RequestBody Transfer theTransfer) {

        Transaction transaction = theTransfer.getTransaction();

        // - balance of sender
        Account sender = transaction.getAccount();
        sender.setBalance(sender.getBalance() - transaction.getAmount());
        transaction.setAccount(sender);
        accountService.save(sender);
        // + balance of sender
        Account reciever = theTransfer.getReciever();
        reciever.setBalance(reciever.getBalance() + transaction.getAmount());
        accountService.save(reciever);

        //save changed data
        theTransfer.setTransaction(transaction);
        theTransfer.setReciever(reciever);

        transferService.save(theTransfer);

        return theTransfer;
    }

    @DeleteMapping("/transfers/{transfersId}")
    public String deleteTransfer(@PathVariable int transfersId) {

        Optional<Transfer> transfer = transferService.findById(transfersId);
        // throw exception if null
        if (transfer.isEmpty()) {
            throw new RuntimeException("Transfer id not found - " + transfersId);
        }
        transferService.deleteById(transfersId);

        return "Deleted transfer id - " + transfersId;
    }
}
