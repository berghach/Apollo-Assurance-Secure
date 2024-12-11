package org.example.ApolloAssurance.service;

import org.example.ApolloAssurance.dto.AutoContractDTO;
import org.example.ApolloAssurance.entities.AutoContract;
import org.example.ApolloAssurance.mappers.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.ApolloAssurance.repository.AutoContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutoContractService {
    @Autowired
    private AutoContractRepository autoContractRepository;
    @Autowired
    private GenericMapper genericMapper;


    public List<AutoContractDTO> findAll(){
        return genericMapper.toDTOList(autoContractRepository.findAll(), AutoContractDTO.class);
    }
    public Optional<AutoContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<AutoContract> autoContractOptional = autoContractRepository.findById(uuid);

        if (autoContractOptional.isPresent()) {
            AutoContract autoContract = autoContractOptional.get();
            AutoContractDTO autoContractDTO = genericMapper.toDTO(autoContract, AutoContractDTO.class);
            return Optional.of(autoContractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(AutoContractDTO autoContractDTO){
        AutoContract autoContract = genericMapper.fromDTO(autoContractDTO, AutoContract.class);
        autoContractRepository.save(autoContract);
    }
    @Transactional
    public boolean update(UUID uuid, AutoContractDTO autoContractUpdateDTO) throws IllegalArgumentException{
        AutoContract existingAutoContract = autoContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No autoContract corresponding to this identity."));

        AutoContract oldAutoContract = existingAutoContract;

        existingAutoContract = genericMapper.fromDTO(autoContractUpdateDTO, AutoContract.class);

        AutoContract newAutoContract = autoContractRepository.save(existingAutoContract);

        return !oldAutoContract.equals(newAutoContract);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        AutoContract existingAutoContract = autoContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No autoContract corresponding to this identity."));

        autoContractRepository.delete(existingAutoContract);

        return !autoContractRepository.existsById(uuid);
    }
    public double calculateEstimate(AutoContractDTO autoContractDTO){
        double base = 500.0;
        double contractAmount = base;
        if(autoContractDTO.getDriverAge() < 25){
            contractAmount += (base * 0.1);
        }
        if (autoContractDTO.isLuxurious()){
            contractAmount += (base * 0.15);
        }
        if (autoContractDTO.isProfessional()){
            contractAmount += (base * 0.1);
        }
        if (autoContractDTO.isDamaged()){
            contractAmount += (base * 0.1);
        }else {
            contractAmount -= (base * 0.2);
        }
        return contractAmount;
    }
}
