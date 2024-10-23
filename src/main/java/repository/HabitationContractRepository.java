package repository;

import entities.HabitationContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitationContractRepository extends JpaRepository<HabitationContract, UUID> {
}
