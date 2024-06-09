package com.example.paymentsystem.service;

import com.example.paymentsystem.dto.ClientDTO;
import com.example.paymentsystem.mapper.ClientMapper;
import com.example.paymentsystem.model.Client;
import com.example.paymentsystem.repository.ClientRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();
    private ClientMapper clientMapper = ClientMapper.INSTANCE;

    public void createClient(ClientDTO clientDTO) throws SQLException {
        Client client = clientMapper.toEntity(clientDTO);
        clientRepository.createClient(client);
    }

    public ClientDTO findClientById(Long id) throws SQLException {
        Client client = clientRepository.findClientById(id);
        return clientMapper.toDTO(client);
    }

    public List<ClientDTO> findAllClients() throws SQLException {
        List<Client> clients = clientRepository.findAllClients();
        return clients.stream().map(clientMapper::toDTO).collect(Collectors.toList());
    }

    public void updateClient(ClientDTO clientDTO) throws SQLException {
        Client client = clientMapper.toEntity(clientDTO);
        clientRepository.updateClient(client);
    }

    public void deleteClient(Long id) throws SQLException {
        clientRepository.deleteClient(id);
    }
}
