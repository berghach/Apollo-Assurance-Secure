package org.example.ApolloAssurance.service;


import org.example.ApolloAssurance.dto.ContractDTO;
import org.example.ApolloAssurance.entities.Contract;
import org.example.ApolloAssurance.mappers.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.ApolloAssurance.repository.ContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private GenericMapper genericMapper;
    
    public List<ContractDTO> findAll(){
        return genericMapper.toDTOList(contractRepository.findAll(), ContractDTO.class);
    }
    public Optional<ContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Contract> contractOptional = contractRepository.findById(uuid);

        if (contractOptional.isPresent()) {
            Contract contract = contractOptional.get();
            ContractDTO contractDTO = genericMapper.toDTO(contract, ContractDTO.class);
            return Optional.of(contractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(ContractDTO contractDTO){
        Contract contract = genericMapper.fromDTO(contractDTO, Contract.class);
        contractRepository.save(contract);
    }
    @Transactional
    public boolean update(UUID uuid, ContractDTO contractUpdateDTO) throws IllegalArgumentException{
        Contract existingContract = contractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No contract corresponding to this identity."));

        Contract oldContract = existingContract;

        existingContract = genericMapper.fromDTO(contractUpdateDTO, Contract.class);

        Contract newContract = contractRepository.save(existingContract);

        return !oldContract.equals(newContract);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        Contract existingContract = contractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No contract corresponding to this identity."));

        contractRepository.delete(existingContract);

        return !contractRepository.existsById(uuid);
    }
}
