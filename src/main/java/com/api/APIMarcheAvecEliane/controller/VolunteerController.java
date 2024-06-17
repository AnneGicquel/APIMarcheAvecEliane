package com.api.APIMarcheAvecEliane.controller;

import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    // http://localhost:8080/api/volunteers/getAllVolunteers
    @GetMapping("/getAllVolunteers")
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    // http://localhost:8080/api/volunteers/getVolunteerById/{id}
    @GetMapping("/getVolunteerById/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable UUID id) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteerById(id);
        return volunteer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // http://localhost:8080/api/volunteers/createNewVolunteer
    @PostMapping("/createNewVolunteer")
    public ResponseEntity<Volunteer> createNewVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.createNewVolunteer(volunteer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVolunteer);
    }

    // http://localhost:8080/api/volunteers/updateVolunteer/{id}
    @PutMapping("/updateVolunteer/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable UUID id, @RequestBody Volunteer volunteer) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteerById(id, volunteer);
        return updatedVolunteer != null ? ResponseEntity.ok(updatedVolunteer) : ResponseEntity.notFound().build();
    }

    // http://localhost:8080/api/volunteers/deleteVolunteerById/{id}
    @DeleteMapping("/deleteVolunteerById/{id}")
    public ResponseEntity<String> deleteVolunteerById(@PathVariable UUID id) {
        volunteerService.deleteVolunteerById(id);
        return ResponseEntity.ok("Volunteer with ID " + id + " has been successfully deleted 🧽 ");
    }
    //Make 'deleteVolunteerById()' return 'org.springframework.http.ResponseEntity<java.lang.String>'
}
