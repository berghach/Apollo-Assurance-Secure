package org.example.repository;

import org.example.entities.HealthContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HealthContractRepository extends JpaRepository<HealthContract, UUID> {
}
