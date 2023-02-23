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
    public Bank findById(int bankId) {

        Optional<Bank> bank = bankRepository.findById(bankId);
        if (bank.isPresent()) {
            return bank.get();
        } else {
            throw new RuntimeException("Bank id not found - " + bankId);
        }
    }

    @Override
    public void save(Bank theBank) {
        bankRepository.save(theBank);
    }

    @Override
    public Bank deleteById(int bankId) {

        Optional<Bank> bank = bankRepository.findById(bankId);

        if (bank.isPresent()) {
            bankRepository.deleteById(bankId);
            return bank.get();

        } else {
            // throw exception if null
            throw new RuntimeException("Employee id not found - " + bankId);
        }
    }
}
