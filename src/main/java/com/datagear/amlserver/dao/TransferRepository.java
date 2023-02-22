package com.datagear.amlserver.dao;

import com.datagear.amlserver.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
