package service;

import dto.HabitationContractDTO;
import entities.HabitationContract;
import mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.HabitationContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitationContractService {
    @Autowired
    private HabitationContractRepository habitationContractRepository;

    private final Mapper<HabitationContract, HabitationContractDTO> habitationContractMapper;

    public HabitationContractService(Mapper<HabitationContract, HabitationContractDTO> habitationContractMapper) {
        this.habitationContractMapper = habitationContractMapper;
    }

    @Transactional
    public List<HabitationContractDTO> findAll(){
        return habitationContractMapper.toDTOList(habitationContractRepository.findAll());
    }
    @Transactional
    public Optional<HabitationContractDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<HabitationContract> habitationContractOptional = habitationContractRepository.findById(uuid);

        if (habitationContractOptional.isPresent()) {
            HabitationContract habitationContract = habitationContractOptional.get();
            HabitationContractDTO habitationContractDTO = habitationContractMapper.toDTO(habitationContract);
            return Optional.of(habitationContractDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(HabitationContractDTO habitationContractDTO){
        HabitationContract habitationContract = habitationContractMapper.fromDTO(habitationContractDTO);
        habitationContractRepository.save(habitationContract);
    }
    @Transactional
    public boolean update(UUID uuid, HabitationContractDTO habitationContractUpdateDTO) throws IllegalArgumentException{
        HabitationContract existingHabitationContract = habitationContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No habitationContract corresponding to this identity."));

        HabitationContract oldHabitationContract = existingHabitationContract;

        existingHabitationContract = habitationContractMapper.fromDTO(habitationContractUpdateDTO);

        HabitationContract newHabitationContract = habitationContractRepository.save(existingHabitationContract);

        return !oldHabitationContract.equals(newHabitationContract);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        HabitationContract existingHabitationContract = habitationContractRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No habitationContract corresponding to this identity."));

        habitationContractRepository.delete(existingHabitationContract);

        return !habitationContractRepository.existsById(uuid);
    }
}
