package service;

import dto.AutoContractDTO;
import entities.AutoContract;
import mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AutoContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutoContractService {
    @Autowired
    private AutoContractRepository autoContractRepository;

    private final Mapper<AutoContract, AutoContractDTO> autoContractMapper;

    public AutoContractService(Mapper<AutoContract, AutoContractDTO> autoContractMapper) {
        this.autoContractMapper = autoContractMapper;
    }

    public List<AutoContractDTO> findAll(){
        return autoContractMapper.toDTOList(autoContractRepository.findAll());
    }
    public Optional<AutoContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<AutoContract> autoContractOptional = autoContractRepository.findById(uuid);

        if (autoContractOptional.isPresent()) {
            AutoContract autoContract = autoContractOptional.get();
            AutoContractDTO autoContractDTO = autoContractMapper.toDTO(autoContract);
            return Optional.of(autoContractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(AutoContractDTO autoContractDTO){
        AutoContract autoContract = autoContractMapper.fromDTO(autoContractDTO);
        autoContractRepository.save(autoContract);
    }
    @Transactional
    public boolean update(UUID uuid, AutoContractDTO autoContractUpdateDTO) throws IllegalArgumentException{
        AutoContract existingAutoContract = autoContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No autoContract corresponding to this identity."));

        AutoContract oldAutoContract = existingAutoContract;

        existingAutoContract = autoContractMapper.fromDTO(autoContractUpdateDTO);

        AutoContract newAutoContract = autoContractRepository.save(existingAutoContract);

        return !oldAutoContract.equals(newAutoContract);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        AutoContract existingAutoContract = autoContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No autoContract corresponding to this identity."));

        autoContractRepository.delete(existingAutoContract);

        return !autoContractRepository.existsById(uuid);
    }
}
