package com.example.paymentsystem.mapper;

import com.example.paymentsystem.dto.ClientDTO;
import com.example.paymentsystem.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);
}
