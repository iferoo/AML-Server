package com.datagear.amlserver.dao;

import com.datagear.amlserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
