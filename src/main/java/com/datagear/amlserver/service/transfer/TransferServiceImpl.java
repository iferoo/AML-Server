package com.datagear.amlserver.service.transfer;

import com.datagear.amlserver.dao.TransferRepository;
import com.datagear.amlserver.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Optional<Transfer> findById(int theId) {
        return transferRepository.findById(theId);
    }

    @Override
    public void save(Transfer theTransfer) {
        transferRepository.save(theTransfer);
    }

    @Override
    public void deleteById(int theId) {
        transferRepository.deleteById(theId);
    }

}
