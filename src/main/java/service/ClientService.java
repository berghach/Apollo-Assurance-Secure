package service;

import dto.ClientDTO;
import entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

import java.util.ArrayList;
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

    public List<ClientDTO> findAll(){
        List<ClientDTO> clientDTOList = new ArrayList<>();
        clientRepository.findAll().forEach(client -> {
            ClientDTO clientDTO;
            clientDTO = new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getAddress(),
                    client.getPhone(),
                    client.getEmail(),
                    client.getPassword()
            );
            client.setContracts(client.getContracts());
            clientDTOList.add(clientDTO);
        });
        return clientDTOList;
    }
    public Optional<ClientDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Client> clientOptional = clientRepository.findById(uuid);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            ClientDTO clientDTO;
            clientDTO = new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getAddress(),
                    client.getPhone(),
                    client.getEmail(),
                    client.getPassword()
            );
            client.setContracts(client.getContracts());
            return Optional.of(clientDTO);
        } else {
            return Optional.empty();
        }
    }
    public void create(ClientDTO clientDTO){
        Client client = new Client();

        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());

        clientRepository.save(client);
    }
    @Transactional
    public boolean update(UUID uuid, ClientDTO clientUpdateDTO) throws IllegalArgumentException{
        Client existingClient = clientRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No client corresponding to this identity."));

        Client oldClient = existingClient;
        if (clientUpdateDTO.getName() != null){
            existingClient.setName(clientUpdateDTO.getName());
        }
        if (clientUpdateDTO.getAddress() != null){
            existingClient.setAddress(clientUpdateDTO.getAddress());
        }
        if (clientUpdateDTO.getPhone() != null){
            existingClient.setPhone(clientUpdateDTO.getPhone());
        }
        if (clientUpdateDTO.getEmail() != null){
            existingClient.setEmail(clientUpdateDTO.getEmail());
        }
        if (clientUpdateDTO.getPassword() != null){
            existingClient.setPassword(clientUpdateDTO.getPassword());
        }

        Client newClient = clientRepository.save(existingClient);

        return !oldClient.equals(newClient);
    }
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        Client existingClient = clientRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No client corresponding to this identity."));

        clientRepository.delete(existingClient);

        return !clientRepository.existsById(uuid);
    }
}
