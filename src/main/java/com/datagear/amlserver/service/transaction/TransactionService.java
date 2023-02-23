package com.datagear.amlserver.service.transaction;

import com.datagear.amlserver.entity.Transaction;

import java.util.List;

public interface TransactionService {
    public List<Transaction> findAll();

    public Transaction findById(int transactionId);

    public void save(Transaction theTransaction);

    public Transaction deleteById(int transactionId);
}
