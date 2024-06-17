package com.api.APIMarcheAvecEliane.repository;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, UUID> {
}