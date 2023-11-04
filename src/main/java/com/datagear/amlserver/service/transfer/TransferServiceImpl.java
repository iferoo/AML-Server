package com.datagear.amlserver.service.transfer;

import com.datagear.amlserver.repository.AccountRepository;
import com.datagear.amlserver.repository.TransferRepository;
import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Transaction;
import com.datagear.amlserver.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, AccountRepository accountRepository) {
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
    public Transfer save(Transfer theTransfer) {

        Transaction transaction = theTransfer.getTransaction();

        if (transaction.getOperation().equals("transfer")) {
            return tellerTransfer(theTransfer, theTransfer.getTransaction());
        } else {
            return tellerDeposite(theTransfer, theTransfer.getTransaction());
        }
    }


    public Transfer tellerTransfer(Transfer theTransfer, Transaction transaction) {
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

    public Transfer tellerDeposite(Transfer theTransfer, Transaction transaction) {

        Optional<Account> account = accountRepository.findById(transaction.getAccount().getAccountId());
        if (account.isPresent()) {
            if (transaction.getOperation().equals("deposit")) {
                // update balance
                account.get().setBalance(account.get().getBalance() + transaction.getAmount());
                //save balance
                accountRepository.save(account.get());
                // update transaction account
                transaction.setAccount(account.get());
                // update reciever
                theTransfer.setReciever(account.get());
                //update transfer transaction
                theTransfer.setTransaction(transaction);
                // save transfer
                transferRepository.save(theTransfer);
                return theTransfer;
            } else {
                // update balance
                account.get().setBalance(account.get().getBalance() - transaction.getAmount());
                //save balance
                accountRepository.save(account.get());
                // update transaction account
                transaction.setAccount(account.get());
                // update reciever
                theTransfer.setReciever(account.get());
                //update transfer transaction
                theTransfer.setTransaction(transaction);
                // save transfer
                transferRepository.save(theTransfer);
                return theTransfer;
            }

        } else {
            throw new RuntimeException("Account Not Exist - " + account.get().getAccountId());

        }
    }
}
