package com.datagear.amlserver.service.branch;

import com.datagear.amlserver.entity.Branch.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    public List<Branch> findAll();

    public Optional<Branch> findById(int theId);

    public void save(Branch theBranch);

    public void deleteById(int theId);
}
