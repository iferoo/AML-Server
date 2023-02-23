package com.datagear.amlserver.rest;

import com.datagear.amlserver.entity.Branch;
import com.datagear.amlserver.service.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BranchRestController {
    private BranchService branchService;


    @Autowired
    public BranchRestController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/branches")
    public List<Branch> findAll() {
        return branchService.findAll();
    }

    @GetMapping("/branches/{branchId}")
    public Branch getBank(@PathVariable int branchId) {
        return branchService.findById(branchId);
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
        return branchService.deleteById(branchId);
    }
}
