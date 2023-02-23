package com.datagear.amlserver.service.transfer;

import com.datagear.amlserver.entity.Transfer;

import java.util.List;

public interface TransferService {
    public List<Transfer> findAll();

    public Transfer findById(int transferId);

    public Transfer addTransfer(Transfer theTransfer);

    public Transfer deleteById(int transferId);
}
