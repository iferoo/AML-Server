package com.datagear.amlserver.service.branch;

import com.datagear.amlserver.dao.BankRepository;
import com.datagear.amlserver.dao.BranchRepository;
import com.datagear.amlserver.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;
    private final BankRepository bankRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository,
                             BankRepository bankRepository) {
        this.branchRepository = branchRepository;
        this.bankRepository = bankRepository;
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
    public void save(Branch theBranch) {
        branchRepository.save(theBranch);
    }

    @Override
    public Branch deleteById(int branchId) {

        Optional<Branch> branch = branchRepository.findById(branchId);

        if (branch.isPresent()) {
            Branch deletedBranch = branch.get();
            deletedBranch.setIsDeleted(true);
            deletedBranch.setDeletedAt(LocalDateTime.now());
            branchRepository.save(deletedBranch);
            return deletedBranch;
        } else {
            // throw exception if null
            throw new RuntimeException("Branch id not found - " + branchId);
        }
    }
}
