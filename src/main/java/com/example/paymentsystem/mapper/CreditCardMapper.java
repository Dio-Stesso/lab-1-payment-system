package com.example.paymentsystem.mapper;

import com.example.paymentsystem.dto.CreditCardDTO;
import com.example.paymentsystem.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditCardMapper {
    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardDTO toDTO(CreditCard creditCard);
    CreditCard toEntity(CreditCardDTO creditCardDTO);
}
