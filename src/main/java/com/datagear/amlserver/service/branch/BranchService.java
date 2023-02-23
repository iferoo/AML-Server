package com.datagear.amlserver.service.branch;

import com.datagear.amlserver.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    public List<Branch> findAll();

    public Branch findById(int branchId);

    public void save(Branch theBranch);

    public Branch deleteById(int branchId);
}
