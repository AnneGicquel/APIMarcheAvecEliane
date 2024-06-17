package com.api.APIMarcheAvecEliane.repository;

import com.api.APIMarcheAvecEliane.model.Elderly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElderlyRepository extends JpaRepository<Elderly, UUID> {
}