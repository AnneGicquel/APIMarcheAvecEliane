package com.api.APIMarcheAvecEliane.repository;

import com.api.APIMarcheAvecEliane.model.Outing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutingRepository extends JpaRepository<Outing, UUID> {

    ////////// ELDERLY //////////
    Optional<Outing> findByElderlyIdAndId(UUID elderlyId, UUID id);
    List<Outing> findByElderlyId(UUID elderlyId);

    ///////// VOLUNTEER /////////
    Optional<Outing> findByVolunteerIdAndId(UUID volunteerId, UUID id);
    List<Outing> findByVolunteerId(UUID volunteerId);
}
