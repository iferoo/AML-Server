package com.datagear.amlserver.service.bank;

import com.datagear.amlserver.entity.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    public List<Bank> findAll();

    public Bank findById(int theId);

    public void save(Bank theBank);

    public Bank deleteById(int theId);
}
