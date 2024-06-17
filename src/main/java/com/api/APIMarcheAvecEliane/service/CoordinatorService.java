package com.api.APIMarcheAvecEliane.service;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.repository.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;

    @Autowired
    public CoordinatorService(CoordinatorRepository coordinatorRepository) {
        this.coordinatorRepository = coordinatorRepository;
    }

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public Optional<Coordinator> getCoordinatorById(UUID id) {
        return coordinatorRepository.findById(id);
    }

    public Coordinator createNewCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Coordinator updateCoordinatorById(UUID id, Coordinator updatedCoordinator) {
        if (coordinatorRepository.existsById(id)) {
            updatedCoordinator.setId(id);
            return coordinatorRepository.save(updatedCoordinator);
        }
        return null; // 🟣 throw exception to do !!
    }

    public void deleteCoordinatorById(UUID id) {
        coordinatorRepository.deleteById(id);
    }
}