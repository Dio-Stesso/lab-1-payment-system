package com.example.paymentsystem.service;

import com.example.paymentsystem.dto.AccountDTO;
import com.example.paymentsystem.mapper.AccountMapper;
import com.example.paymentsystem.model.Account;
import com.example.paymentsystem.repository.AccountRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    private AccountMapper accountMapper = AccountMapper.INSTANCE;

    public void createAccount(AccountDTO accountDTO) throws SQLException {
        Account account = accountMapper.toEntity(accountDTO);
        accountRepository.createAccount(account);
    }

    public AccountDTO findAccountById(Long id) throws SQLException {
        Account account = accountRepository.findAccountById(id);
        return accountMapper.toDTO(account);
    }

    public List<AccountDTO> findAllAccountsByCreditCardId(Long creditCardId) throws SQLException {
        List<Account> accounts = accountRepository.findAllAccountsByCreditCardId(creditCardId);
        return accounts.stream().map(accountMapper::toDTO).collect(Collectors.toList());
    }

    public void updateAccount(AccountDTO accountDTO) throws SQLException {
        Account account = accountMapper.toEntity(accountDTO);
        accountRepository.updateAccount(account);
    }

    public void deleteAccount(Long id) throws SQLException {
        accountRepository.deleteAccount(id);
    }
}
