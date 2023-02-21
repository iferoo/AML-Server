package com.datagear.amlserver.dao;

import com.datagear.amlserver.entity.Branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
