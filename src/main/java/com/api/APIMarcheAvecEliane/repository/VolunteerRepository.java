package com.api.APIMarcheAvecEliane.repository;

import com.api.APIMarcheAvecEliane.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, UUID> {
}
