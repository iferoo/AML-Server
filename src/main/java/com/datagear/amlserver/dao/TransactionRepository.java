package com.datagear.amlserver.dao;

import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByAccountAndCreatedDate(Account account, LocalDate createdDate);
}
