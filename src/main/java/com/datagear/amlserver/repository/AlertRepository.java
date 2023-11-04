package com.datagear.amlserver.repository;

import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    Alert findFirstByAccountAndCreatedAt(Account account, LocalDate createdDate);
}
