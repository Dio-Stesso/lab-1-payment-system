package com.example.paymentsystem.mapper;

import com.example.paymentsystem.dto.AccountDTO;
import com.example.paymentsystem.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toDTO(Account account);
    Account toEntity(AccountDTO accountDTO);
}
