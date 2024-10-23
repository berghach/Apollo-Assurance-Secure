package repository;

import entities.HealthContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HealthContractRepository extends JpaRepository<HealthContract, UUID> {
}
