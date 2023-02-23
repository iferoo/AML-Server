package com.datagear.amlserver.service.account;

import com.datagear.amlserver.dao.AccountRepository;
import com.datagear.amlserver.entity.Account;
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
    public List<Account> findAll() {return accountRepository.findAll();}

    @Override
    public Account findById(int accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Bank id not found - " + accountId);
        }
    }

    @Override
    public void save(Account theAccount) {
        accountRepository.save(theAccount);
    }

    @Override
    public Account deleteById(int accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            accountRepository.deleteById(accountId);
            return account.get();
        } else {
            // throw exception if null
            throw new RuntimeException("Account id not found - " + accountId);
        }
    }
}
