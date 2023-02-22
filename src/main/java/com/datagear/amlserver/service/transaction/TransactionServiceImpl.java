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
    public Optional<Transaction> findById(int theId) {
        return transactionRepository.findById(theId);
    }

    @Override
    public void save(Transaction theTransaction) {
        transactionRepository.save(theTransaction);
    }

    @Override
    public void deleteById(int theId) {
        transactionRepository.deleteById(theId);
    }
}
