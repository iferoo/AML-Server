package com.datagear.amlserver.rest;

import com.datagear.amlserver.DTO.BankDTO;
import com.datagear.amlserver.entity.Bank;
import com.datagear.amlserver.mapper.BankMapper;
import com.datagear.amlserver.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class BankRestController {
    private BankMapper mapper;
    private BankService bankService;

    @Autowired
    public BankRestController(BankMapper mapper, BankService bankService) {
        this.mapper =mapper;
        this.bankService = bankService;
    }

    @GetMapping("/banks")
//    @PreAuthorize("hasAuthority('get_bank')")
    public List<BankDTO> findAll() {
        List<Bank> banks = bankService.findAll();
//        List<BankDTO> bankDTOS = new ArrayList<>();
//        banks.forEach(bank -> {
//            bankDTOS.add(mapper.toDto(bank));
//        });
        return mapper.toListDto(bankService.findAll());
    }

    @GetMapping("/banks/{bankId}")
    public Bank getBank(@PathVariable int bankId) {
        return bankService.findById(bankId);
    }

    @PostMapping("/banks")
    public Bank addBank(@RequestBody Bank theBank) {
        theBank.setBankId(0);
        bankService.save(theBank);
        return theBank;
    }

    @PutMapping("/banks")
    public Bank updateBank(@RequestBody Bank theBank) {

        bankService.save(theBank);
        return theBank;
    }

    @DeleteMapping("/banks/{bankId}")
    public Bank deleteBank(@PathVariable int bankId) {
        return bankService.deleteById(bankId);
    }
}
