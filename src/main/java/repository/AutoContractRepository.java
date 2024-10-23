package repository;

import entities.AutoContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoContractRepository extends JpaRepository<AutoContract, UUID> {
}
