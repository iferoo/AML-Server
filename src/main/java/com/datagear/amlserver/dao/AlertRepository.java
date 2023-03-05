package com.datagear.amlserver.dao;

import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Alert;
import com.datagear.amlserver.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    Alert findFirstByAccountAndCreatedAt(Account account, LocalDate createdDate);
}
