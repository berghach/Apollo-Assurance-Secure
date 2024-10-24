package org.example.service;

import org.example.dto.ClientDTO;
import org.example.entities.Client;
import org.example.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private  ClientRepository clientRepository;

    private final Mapper<Client, ClientDTO> clientMapper;

    public ClientService(Mapper<Client, ClientDTO> clientMapper) {
        this.clientMapper = clientMapper;
    }

    public List<ClientDTO> findAll(){
        return clientMapper.toDTOList(clientRepository.findAll());
    }
    public Optional<ClientDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Client> clientOptional = clientRepository.findById(uuid);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            ClientDTO clientDTO = clientMapper.toDTO(client);
            return Optional.of(clientDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(ClientDTO clientDTO){
        Client client = clientMapper.fromDTO(clientDTO);
        clientRepository.save(client);
    }
    @Transactional
    public boolean update(UUID uuid, ClientDTO clientUpdateDTO) throws IllegalArgumentException{
        Client existingClient = clientRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No client corresponding to this identity."));

        Client oldClient = existingClient;

        existingClient = clientMapper.fromDTO(clientUpdateDTO);

        Client newClient = clientRepository.save(existingClient);

        return !oldClient.equals(newClient);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        Client existingClient = clientRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No client corresponding to this identity."));

        clientRepository.delete(existingClient);

        return !clientRepository.existsById(uuid);
    }
}
