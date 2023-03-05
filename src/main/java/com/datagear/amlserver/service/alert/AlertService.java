package com.datagear.amlserver.service.alert;

import com.datagear.amlserver.entity.Alert;

import java.util.List;

public interface AlertService {
    public List<Alert> findAll();

    public Alert findById(int alertId);

    public void save(Alert alert);

}
