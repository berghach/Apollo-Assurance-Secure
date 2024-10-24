package service;

import dto.ZoneDTO;
import entities.Zone;
import mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ZoneRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ZoneService {
    @Autowired
    private ZoneRepository clientRepository;

    private final Mapper<Zone, ZoneDTO> clientMapper;

    public ZoneService(Mapper<Zone, ZoneDTO> clientMapper) {
        this.clientMapper = clientMapper;
    }

    public List<ZoneDTO> findAll(){
        return clientMapper.toDTOList(clientRepository.findAll());
    }
    public Optional<ZoneDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Zone> clientOptional = clientRepository.findById(uuid);

        if (clientOptional.isPresent()) {
            Zone client = clientOptional.get();
            ZoneDTO clientDTO = clientMapper.toDTO(client);
            return Optional.of(clientDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(ZoneDTO clientDTO){
        Zone client = clientMapper.fromDTO(clientDTO);
        clientRepository.save(client);
    }
    @Transactional
    public boolean update(UUID uuid, ZoneDTO clientUpdateDTO) throws IllegalArgumentException{
        Zone existingZone = clientRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No client corresponding to this identity."));

        Zone oldZone = existingZone;

        existingZone = clientMapper.fromDTO(clientUpdateDTO);

        Zone newZone = clientRepository.save(existingZone);

        return !oldZone.equals(newZone);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        Zone existingZone = clientRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No client corresponding to this identity."));

        clientRepository.delete(existingZone);

        return !clientRepository.existsById(uuid);
    }
}
