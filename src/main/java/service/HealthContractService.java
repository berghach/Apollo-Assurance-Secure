package service;

import dto.HealthContractDTO;
import entities.HealthContract;
import mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.HealthContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HealthContractService {
    @Autowired
    private HealthContractRepository healthContractRepository;

    private final Mapper<HealthContract, HealthContractDTO> healthContractMapper;

    public HealthContractService(Mapper<HealthContract, HealthContractDTO> healthContractMapper) {
        this.healthContractMapper = healthContractMapper;
    }

    public List<HealthContractDTO> findAll(){
        return healthContractMapper.toDTOList(healthContractRepository.findAll());
    }
    public Optional<HealthContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<HealthContract> healthContractOptional = healthContractRepository.findById(uuid);

        if (healthContractOptional.isPresent()) {
            HealthContract healthContract = healthContractOptional.get();
            HealthContractDTO healthContractDTO = healthContractMapper.toDTO(healthContract);
            return Optional.of(healthContractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(HealthContractDTO healthContractDTO){
        HealthContract healthContract = healthContractMapper.fromDTO(healthContractDTO);
        healthContractRepository.save(healthContract);
    }
    @Transactional
    public boolean update(UUID uuid, HealthContractDTO healthContractUpdateDTO) throws IllegalArgumentException{
        HealthContract existingHealthContract = healthContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No healthContract corresponding to this identity."));

        HealthContract oldHealthContract = existingHealthContract;

        existingHealthContract = healthContractMapper.fromDTO(healthContractUpdateDTO);

        HealthContract newHealthContract = healthContractRepository.save(existingHealthContract);

        return !oldHealthContract.equals(newHealthContract);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        HealthContract existingHealthContract = healthContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No healthContract corresponding to this identity."));

        healthContractRepository.delete(existingHealthContract);

        return !healthContractRepository.existsById(uuid);
    }
    public double calculateEstimate(HealthContractDTO healthContractDTO) {
        double base = 500.0;
        double contractAmount = base;
        if (healthContractDTO.getInsuredAge() > 60){
            contractAmount += (base * 0.2);
        }
        if (healthContractDTO.getChronicIllness() != null){
            // the insure does have a chronic illness
            contractAmount += (base * 0.3);
        }
        if(healthContractDTO.isPremium()){
            contractAmount += (base * 0.05);
        }else {
            contractAmount -= (base * 0.1);
        }
        return contractAmount;
    }
}
