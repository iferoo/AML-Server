package com.datagear.amlserver.service.bank;

import com.datagear.amlserver.dao.BankRepository;
import com.datagear.amlserver.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    private BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public Optional<Bank> findById(int theId) {
        return bankRepository.findById(theId);
    }

    @Override
    public void save(Bank theBank) {
        bankRepository.save(theBank);
    }

    @Override
    public void deleteById(int theId) {
        bankRepository.deleteById(theId);
    }
}
