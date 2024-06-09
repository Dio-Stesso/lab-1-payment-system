package com.example.paymentsystem.service;

import com.example.paymentsystem.dto.CreditCardDTO;
import com.example.paymentsystem.mapper.CreditCardMapper;
import com.example.paymentsystem.model.CreditCard;
import com.example.paymentsystem.repository.CreditCardRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CreditCardService {
    private CreditCardRepository creditCardRepository = new CreditCardRepository();
    private CreditCardMapper creditCardMapper = CreditCardMapper.INSTANCE;

    public void createCreditCard(CreditCardDTO creditCardDTO) throws SQLException {
        CreditCard creditCard = creditCardMapper.toEntity(creditCardDTO);
        creditCardRepository.createCreditCard(creditCard);
    }

    public CreditCardDTO findCreditCardById(Long id) throws SQLException {
        CreditCard creditCard = creditCardRepository.findCreditCardById(id);
        return creditCardMapper.toDTO(creditCard);
    }

    public List<CreditCardDTO> findAllCreditCardsByClientId(Long clientId) throws SQLException {
        List<CreditCard> creditCards = creditCardRepository.findAllCreditCardsByClientId(clientId);
        return creditCards.stream().map(creditCardMapper::toDTO).collect(Collectors.toList());
    }

    public void updateCreditCard(CreditCardDTO creditCardDTO) throws SQLException {
        CreditCard creditCard = creditCardMapper.toEntity(creditCardDTO);
        creditCardRepository.updateCreditCard(creditCard);
    }

    public void deleteCreditCard(Long id) throws SQLException {
        creditCardRepository.deleteCreditCard(id);
    }
}
