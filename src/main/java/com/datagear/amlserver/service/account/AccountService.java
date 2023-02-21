package com.datagear.amlserver.service.account;

import com.datagear.amlserver.entity.Account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<Account> findAll();

    public Optional<Account> findById(int theId);

    public void save(Account theAccount);

    public void deleteById(int theId);
}
