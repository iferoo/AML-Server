package com.datagear.amlserver.repository;

import com.datagear.amlserver.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
