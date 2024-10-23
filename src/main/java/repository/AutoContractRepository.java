package repository;

import entities.AutoContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoContractRepository extends JpaRepository<AutoContract, UUID> {
}
