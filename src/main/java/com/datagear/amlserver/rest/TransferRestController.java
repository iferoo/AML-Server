package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Account.Account;
import com.datagear.amlserver.entity.Transaction.Transaction;
import com.datagear.amlserver.entity.Transfer.Transfer;
import com.datagear.amlserver.entity.Transfer.TransferClass;
import com.datagear.amlserver.service.account.AccountService;
import com.datagear.amlserver.service.transaction.TransactionService;
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
    private TransactionService transactionService;
    private AccountService accountService;

    @Autowired
    public TransferRestController(
            TransferService transferService,
            TransactionService transactionService,
            AccountService accountService
    ) {
        this.transferService = transferService;
        this.transactionService = transactionService;
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
    public Transfer addTransfer(@RequestBody TransferClass theTransferClass) {

        Optional<Transaction> transaction = transactionService.findById(theTransferClass.getTransaction());
        Optional<Account> account = accountService.findById(theTransferClass.getReciever());

        Transfer transfer = new Transfer(
                0,
                transaction.get(),
                account.get(),
                theTransferClass.getStatus());

        transferService.save(transfer);

        return transfer;
    }


    @PutMapping("/transfers")
    public Transfer updateTransfer(@RequestBody TransferClass theTransferClass) {

        Optional<Transaction> transaction = transactionService.findById(theTransferClass.getTransaction());
        Optional<Account> reciever = accountService.findById(theTransferClass.getReciever());

        Optional<Transfer> transfer = transferService.findById(theTransferClass.getId());


        if (transfer.isEmpty()) {
            throw new RuntimeException();
        } else {
            Transfer newTransfer = transfer.get();
            newTransfer.setTransaction(transaction.get());
            newTransfer.setReciever(reciever.get());
            newTransfer.setStatus(theTransferClass.getStatus());
            transferService.save(newTransfer);
            return newTransfer;
        }

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
