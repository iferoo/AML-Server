package com.datagear.amlserver.service.branch;

import com.datagear.amlserver.dao.BranchRepository;
import com.datagear.amlserver.entity.Branch.Branch;
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
    public Optional<Branch> findById(int theId) {
        return branchRepository.findById(theId);
    }

    @Override
    public void save(Branch theBranch) {
        branchRepository.save(theBranch);
    }

    @Override
    public void deleteById(int theId) {
        branchRepository.deleteById(theId);
    }
}
