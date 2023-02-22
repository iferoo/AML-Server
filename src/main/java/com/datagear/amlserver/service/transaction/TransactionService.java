package com.datagear.amlserver.service.transaction;

import com.datagear.amlserver.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    public List<Transaction> findAll();

    public Optional<Transaction> findById(int theId);

    public void save(Transaction theTransaction);

    public void deleteById(int theId);
}
