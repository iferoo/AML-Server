package com.datagear.amlserver.repository;

import com.datagear.amlserver.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
