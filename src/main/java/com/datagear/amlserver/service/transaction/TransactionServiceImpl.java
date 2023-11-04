package com.datagear.amlserver.service.transaction;

import com.datagear.amlserver.repository.AlertRepository;
import com.datagear.amlserver.repository.TransactionRepository;
import com.datagear.amlserver.entity.Alert;
import com.datagear.amlserver.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AlertRepository alertRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AlertRepository alertRepository) {
        this.transactionRepository = transactionRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(int transactionId) {

        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new RuntimeException("Bank id not found - " + transactionId);
        }
    }

    @Override
    public void save(Transaction theTransaction) {
        theTransaction.setCreatedTime(LocalTime.now());
        theTransaction.setCreatedDate(LocalDate.now());
        transactionRepository.save(theTransaction);

        List<Transaction> filterd = transactionRepository.findAllByAccountAndCreatedDate(theTransaction.getAccount(), theTransaction.getCreatedDate());
        Alert checkedAlert = alertRepository.findFirstByAccountAndCreatedAt(theTransaction.getAccount(), theTransaction.getCreatedDate());

        if (filterd.size() > 5) {
            if (checkedAlert == null) {
                Alert newAlert = new Alert(0, theTransaction.getAccount(), filterd.size(), "normal", LocalDate.now());
                alertRepository.save(newAlert);
                return;
            } else {
                checkedAlert.setTransactions(filterd.size());
                alertRepository.save(checkedAlert);
                if (filterd.size() > 7) {
                    if (filterd.size() > 10) {
                        checkedAlert.setTransactions(filterd.size());
                        checkedAlert.setRisk("high");
                        alertRepository.save(checkedAlert);
                        return;
                    }
                    checkedAlert.setTransactions(filterd.size());
                    checkedAlert.setRisk("medium");
                    alertRepository.save(checkedAlert);
                    return;
                }
            }
        }
    }
}
