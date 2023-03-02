package com.datagear.amlserver.service.transfer;

import com.datagear.amlserver.dao.AccountRepository;
import com.datagear.amlserver.dao.TransferRepository;
import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Bank;
import com.datagear.amlserver.entity.Transaction;
import com.datagear.amlserver.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransferServiceImpl(
            TransferRepository transferRepository,
            AccountRepository accountRepository
    ) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer findById(int transferId) {

        Optional<Transfer> transfer = transferRepository.findById(transferId);

        if (transfer.isPresent()) {
            return transfer.get();
        } else {
            throw new RuntimeException("Bank id not found - " + transferId);
        }
    }

    @Override
    public Transfer addTransfer(Transfer theTransfer) {

        Transaction transaction = theTransfer.getTransaction();

        // - balance of sender
        Account sender = transaction.getAccount();
        sender.setBalance(sender.getBalance() - transaction.getAmount());
        transaction.setAccount(sender);
        accountRepository.save(sender);
        // + balance of sender
        Account reciever = theTransfer.getReciever();
        reciever.setBalance(reciever.getBalance() + transaction.getAmount());
        accountRepository.save(reciever);

        //save changed data
        theTransfer.setTransaction(transaction);
        theTransfer.setReciever(reciever);

        transferRepository.save(theTransfer);

        return theTransfer;
    }

    @Override
    public Transfer deleteById(int transferId) {

        Optional<Transfer> transfer = transferRepository.findById(transferId);
        if (transfer.isPresent()) {
            Transfer deletedTransfer = transfer.get();
            deletedTransfer.setIsDeleted(true);
            deletedTransfer.setDeletedAt(LocalDateTime.now());
            transferRepository.save(deletedTransfer);
            return deletedTransfer;
        } else {
            // throw exception if null
            throw new RuntimeException("Transfer id not found - " + transferId);
        }
    }
}
