package com.datagear.amlserver.mapper;

import com.datagear.amlserver.DTO.BankDTO;
import com.datagear.amlserver.entity.Bank;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankDTO toDto (Bank target);
    List<BankDTO> toListDto (List<Bank> target);
    Bank toEntity (Bank source);
}
