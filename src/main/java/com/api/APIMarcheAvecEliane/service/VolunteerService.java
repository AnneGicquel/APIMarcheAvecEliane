package com.api.APIMarcheAvecEliane.service;

import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Optional<Volunteer> getVolunteerById(UUID id) {
        return volunteerRepository.findById(id);
    }

    public Volunteer createNewVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteerById(UUID id, Volunteer updatedVolunteer) {
        if (volunteerRepository.existsById(id)) {
            updatedVolunteer.setId(id);
            return volunteerRepository.save(updatedVolunteer);
        }
        return null; // 🟣 throw exception to do !!
    }

    public void deleteVolunteerById(UUID id) {
        volunteerRepository.deleteById(id);
    }
}
