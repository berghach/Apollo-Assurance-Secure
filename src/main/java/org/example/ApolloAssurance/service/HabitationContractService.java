package org.example.ApolloAssurance.service;

import org.example.ApolloAssurance.dto.HabitationContractDTO;
import org.example.ApolloAssurance.entities.HabitationContract;
import org.example.ApolloAssurance.mappers.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.ApolloAssurance.repository.HabitationContractRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitationContractService {
    @Autowired
    private HabitationContractRepository habitationContractRepository;
    @Autowired
    private GenericMapper genericMapper;
    
    public List<HabitationContractDTO> findAll(){
        return genericMapper.toDTOList(habitationContractRepository.findAll(), HabitationContractDTO.class);
    }
    public Optional<HabitationContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<HabitationContract> habitationContractOptional = habitationContractRepository.findById(uuid);

        if (habitationContractOptional.isPresent()) {
            HabitationContract habitationContract = habitationContractOptional.get();
            HabitationContractDTO habitationContractDTO = genericMapper.toDTO(habitationContract, HabitationContractDTO.class);
            return Optional.of(habitationContractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(HabitationContractDTO habitationContractDTO){
        HabitationContract habitationContract = genericMapper.fromDTO(habitationContractDTO, HabitationContract.class);
        habitationContractRepository.save(habitationContract);
    }
    @Transactional
    public boolean update(UUID uuid, HabitationContractDTO habitationContractUpdateDTO) throws IllegalArgumentException{
        HabitationContract existingHabitationContract = habitationContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No habitationContract corresponding to this identity."));

        HabitationContract oldHabitationContract = existingHabitationContract;

        existingHabitationContract = genericMapper.fromDTO(habitationContractUpdateDTO, HabitationContract.class);

        HabitationContract newHabitationContract = habitationContractRepository.save(existingHabitationContract);

        return !oldHabitationContract.equals(newHabitationContract);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        HabitationContract existingHabitationContract = habitationContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No habitationContract corresponding to this identity."));

        habitationContractRepository.delete(existingHabitationContract);

        return !habitationContractRepository.existsById(uuid);
    }
    public double calculateEstimate(HabitationContractDTO habitationContractDTO){
        double base = 500.0;
        double contractAmount = base;
        if (Objects.equals(habitationContractDTO.getType(), "house")){
            contractAmount += (base * 0.02);
        }
        if (habitationContractDTO.getZone().isDangerous()){
            contractAmount += (base * 0.05);
        }
        if (habitationContractDTO.getPropertyValue().compareTo(new BigDecimal(200000)) > 0){
            contractAmount += (base * 0.1);
        }
        if (habitationContractDTO.getSecuritySystem() == null){
            // the client habitation does not have Security System
            contractAmount += (base * 0.15);
        }else {
            // the client habitation does have Security System
            contractAmount -= (base * 0.15);
        }
        return contractAmount;
    }
}
