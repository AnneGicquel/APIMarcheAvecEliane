package com.api.APIMarcheAvecEliane.controller;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {

    private CoordinatorService coordinatorService;

    @Autowired
    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    // http://localhost:8080/api/coordinators/getAllCoordinators
    @GetMapping("/getAllCoordinators")
    public List<Coordinator> getAllCoordinators() {
        return coordinatorService.getAllCoordinators();
    }

    // http://localhost:8080/api/coordinators/getCoordinatorById/{id}
    @GetMapping("/getCoordinatorById/{id}")
    public ResponseEntity<Coordinator> getCoordinatorById(@PathVariable UUID id) {
        Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(id);
        return coordinator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // http://localhost:8080/api/coordinators/createNewCoordinator
    @PostMapping("/createNewCoordinator")
    public ResponseEntity<Coordinator> createNewCoordinator(@RequestBody Coordinator coordinator) {
        Coordinator createdCoordinator = coordinatorService.createNewCoordinator(coordinator);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoordinator);
    }

    // http://localhost:8080/api/coordinators/updateCoordinator/{id}
    @PutMapping("/updateCoordinator/{id}")
    public ResponseEntity<Coordinator> updateCoordinator(@PathVariable UUID id, @RequestBody Coordinator coordinator) {
        Coordinator updatedCoordinator = coordinatorService.updateCoordinatorById(id, coordinator);
        return updatedCoordinator != null ? ResponseEntity.ok(updatedCoordinator) : ResponseEntity.notFound().build();
    }

    // http://localhost:8080/api/elderlies/deleteElderlyById/{id}
    @DeleteMapping("/deleteCoordinatorById/{id}")
    public ResponseEntity<String> deleteCoordinatorById(@PathVariable UUID id) {
        coordinatorService.deleteCoordinatorById(id);
        return ResponseEntity.ok("Coordinator with ID " + id + " has been successfully deleted 🧽 ");
    }
    //DELETE FROM coordinator
    //WHERE id_coordinator = '150a9fed-6748-44dd-ba2e-1f531665f325';
}
