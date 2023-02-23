package com.datagear.amlserver.service.branch;

import com.datagear.amlserver.dao.BranchRepository;
import com.datagear.amlserver.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch findById(int branchId) {

        Optional<Branch> branch = branchRepository.findById(branchId);

        if (branch.isPresent()) {
            return branch.get();
        } else {
            throw new RuntimeException("Bank id not found - " + branchId);
        }
    }

    @Override
    public void save(Branch theBranch) {branchRepository.save(theBranch);}

    @Override
    public Branch deleteById(int branchId) {

        Optional<Branch> branch = branchRepository.findById(branchId);
        // throw exception if null
        if (branch.isPresent()) {
            branchRepository.deleteById(branchId);
            return branch.get();
        } else {
            throw new RuntimeException("Employee id not found - " + branchId);
        }
    }
}
