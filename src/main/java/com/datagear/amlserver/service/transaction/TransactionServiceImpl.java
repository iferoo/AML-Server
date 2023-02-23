package com.datagear.amlserver.service.transaction;

import com.datagear.amlserver.dao.TransactionRepository;
import com.datagear.amlserver.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(int transactionId) {

        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new RuntimeException("Bank id not found - " + transactionId);
        }
    }

    @Override
    public void save(Transaction theTransaction) {
        transactionRepository.save(theTransaction);
    }

    @Override
    public Transaction deleteById(int transactionId) {

        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent()) {

            transactionRepository.deleteById(transactionId);
            return transaction.get();
        } else {
            // throw exception if null
            throw new RuntimeException("Transaction id not found - " + transactionId);
        }
    }
}
