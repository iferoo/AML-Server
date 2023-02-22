package com.datagear.amlserver.rest;

import com.datagear.amlserver.dao.BankRepository;
import com.datagear.amlserver.entity.Branch;
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


    @Autowired
    public BranchRestController(BranchService branchService) {this.branchService = branchService;}

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
    public Branch addBranch(@RequestBody Branch theBranch) {
        theBranch.setId(0);
        branchService.save(theBranch);
        return theBranch;
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
