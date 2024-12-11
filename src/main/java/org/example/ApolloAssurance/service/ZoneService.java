package org.example.ApolloAssurance.service;

import org.example.ApolloAssurance.dto.ZoneDTO;
import org.example.ApolloAssurance.entities.Zone;
import org.example.ApolloAssurance.mappers.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.ApolloAssurance.repository.ZoneRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private GenericMapper genericMapper;

    public List<ZoneDTO> findAll(){
        return genericMapper.toDTOList(zoneRepository.findAll(), ZoneDTO.class);
    }
    public Optional<ZoneDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Zone> zoneOptional = zoneRepository.findById(uuid);

        if (zoneOptional.isPresent()) {
            Zone zone = zoneOptional.get();
            ZoneDTO zoneDTO = genericMapper.toDTO(zone, ZoneDTO.class);
            return Optional.of(zoneDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(ZoneDTO zoneDTO){
        Zone zone = genericMapper.fromDTO(zoneDTO, Zone.class);
        zoneRepository.save(zone);
    }
    @Transactional
    public boolean update(UUID uuid, ZoneDTO zoneUpdateDTO) throws IllegalArgumentException{
        Zone existingZone = zoneRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No zone corresponding to this identity."));

        Zone oldZone = existingZone;

        existingZone = genericMapper.fromDTO(zoneUpdateDTO, Zone.class);

        Zone newZone = zoneRepository.save(existingZone);

        return !oldZone.equals(newZone);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        Zone existingZone = zoneRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No zone corresponding to this identity."));

        zoneRepository.delete(existingZone);

        return !zoneRepository.existsById(uuid);
    }
}
