package service;

import entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }
    public Optional<Client> findById(UUID uuid) throws IllegalArgumentException{
        return clientRepository.findById(uuid);
    }
    public void create(Client client){
        clientRepository.save(client);
    }
}
