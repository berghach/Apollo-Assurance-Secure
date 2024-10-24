package service;


import dto.ContractDTO;
import entities.Contract;
import mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    private final Mapper<Contract, ContractDTO> contractMapper;

    public ContractService(Mapper<Contract, ContractDTO> contractMapper) {
        this.contractMapper = contractMapper;
    }

    @Transactional
    public List<ContractDTO> findAll(){
        return contractMapper.toDTOList(contractRepository.findAll());
    }
    @Transactional
    public Optional<ContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Contract> contractOptional = contractRepository.findById(uuid);

        if (contractOptional.isPresent()) {
            Contract contract = contractOptional.get();
            ContractDTO contractDTO = contractMapper.toDTO(contract);
            return Optional.of(contractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(ContractDTO contractDTO){
        Contract contract = contractMapper.fromDTO(contractDTO);
        contractRepository.save(contract);
    }
    @Transactional
    public boolean update(UUID uuid, ContractDTO contractUpdateDTO) throws IllegalArgumentException{
        Contract existingContract = contractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No contract corresponding to this identity."));

        Contract oldContract = existingContract;

        existingContract = contractMapper.fromDTO(contractUpdateDTO);

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
