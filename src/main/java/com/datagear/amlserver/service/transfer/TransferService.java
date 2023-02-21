package com.datagear.amlserver.service.transfer;

import com.datagear.amlserver.entity.Transfer.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferService {
    public List<Transfer> findAll();

    public Optional<Transfer> findById(int theId);

    public void save(Transfer theTransfer);

    public void deleteById(int theId);
}
