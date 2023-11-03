package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Alert;
import com.datagear.amlserver.service.alert.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertRestController {
    private AlertService alertService;

    @Autowired
    public AlertRestController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public List<Alert> findAll() {
        return alertService.findAll();
    }

    @GetMapping("/alerts/{alertId}")
    public Alert getAlert(@PathVariable int alertId) {

        return alertService.findById(alertId);
    }
}
