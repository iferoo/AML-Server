package com.datagear.amlserver.service.alert;

import com.datagear.amlserver.dao.AlertRepository;
import com.datagear.amlserver.entity.Account;
import com.datagear.amlserver.entity.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {
    private AlertRepository alertRepository;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    @Override
    public Alert findById(int alertId) {

        Optional<Alert> alert = alertRepository.findById(alertId);

        if (alert.isPresent()) {
            return alert.get();
        } else {
            throw new RuntimeException("Alert id not found - " + alertId);
        }
    }

    public Alert findByIdAccountAndCreateAt(Account account, LocalDate createAt) {
        List<Alert> alertList = findAll();
        Optional<Alert> Alert = alertList.stream().filter(alert -> alert.getAccount().equals(account) && alert.getCreatedAt().equals(createAt)).findFirst();
        if (Alert.isPresent()) {
            return Alert.get();
        } else {
            return null;
        }
    }

    @Override
    public void save(Alert alert) {
        alertRepository.save(alert);
    }

}
