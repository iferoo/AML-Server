package com.datagear.amlserver.service.account;

import com.datagear.amlserver.dao.AccountRepository;
import com.datagear.amlserver.entity.Account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(int theId) {
        return accountRepository.findById(theId);
    }

    @Override
    public void save(Account theAccount) {
        accountRepository.save(theAccount);
    }

    @Override
    public void deleteById(int theId) {
        accountRepository.deleteById(theId);
    }
}
