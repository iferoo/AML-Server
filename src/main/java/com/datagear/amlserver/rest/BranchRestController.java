package com.datagear.amlserver.rest;

import com.datagear.amlserver.dao.BankRepository;
import com.datagear.amlserver.entity.Bank;
import com.datagear.amlserver.entity.Branch.Branch;
import com.datagear.amlserver.entity.Branch.BranchClass;
import com.datagear.amlserver.service.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BranchRestController {
    private BranchService branchService;
    private final BankRepository bankRepository;

    @Autowired
    public BranchRestController(BranchService branchService,
                                BankRepository bankRepository) {
        this.branchService = branchService;
        this.bankRepository = bankRepository;
    }

    @GetMapping("/branches")
    public List<Branch> findAll() {
        return branchService.findAll();
    }

    @GetMapping("/branches/{branchId}")
    public Branch getBank(@PathVariable int branchId) {

        Optional<Branch> branch = branchService.findById(branchId);

        if (branch.isPresent()) {
            Branch theBranch = branch.get();
            return theBranch;
        } else {
            throw new RuntimeException("Bank id not found - " + branchId);
        }
    }

    @PostMapping("/branches")
    public Branch addBranch(@RequestBody BranchClass theBranchClass) {
//        theBranch.setId(0);
        Optional<Bank> bank = bankRepository.findById(theBranchClass.getBank());
        Branch newBranch = new Branch(0, theBranchClass.getAddress(), theBranchClass.getCity(), bank.get());
        branchService.save(newBranch);

        return newBranch;
    }

    @PutMapping("/branches")
    public Branch updateBranch(@RequestBody Branch theBranch) {
        branchService.save(theBranch);
        return theBranch;
    }

    @DeleteMapping("/branches/{branchId}")
    public Branch deleteBranch(@PathVariable int branchId) {

        Optional<Branch> branch = branchService.findById(branchId);
        // throw exception if null
        if (branch.isEmpty()) {
            throw new RuntimeException("Employee id not found - " + branchId);
        }
        branchService.deleteById(branchId);

        return branch.get();
    }
}