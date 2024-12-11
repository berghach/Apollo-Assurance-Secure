package org.example.ApolloAssurance.repository;

import org.example.ApolloAssurance.entities.HabitationContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HabitationContractRepository extends JpaRepository<HabitationContract, UUID> {
}
