package com.api.APIMarcheAvecEliane.controller;

import com.api.APIMarcheAvecEliane.model.Elderly;
import com.api.APIMarcheAvecEliane.service.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api/elderlies")
public class ElderlyController {

    private final ElderlyService elderlyService;

    @Autowired
    public ElderlyController(ElderlyService elderlyService) {
        this.elderlyService = elderlyService;
    }

    // http://localhost:8080/api/elderlies/getAllElderlies
    @GetMapping("/getAllElderlies")
    public List<Elderly> getAllElderlies() {
        return elderlyService.getAllElderlies();
    }

    // http://localhost:8080/api/elderlies/getElderlyById/{id}"
    @GetMapping("/getElderlyById/{id}")
    public ResponseEntity<Elderly> getElderlyById(@PathVariable UUID id) {
        Optional<Elderly> elderly = elderlyService.getElderlyById(id);
        return elderly.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // http://localhost:8080/api/elderlies/createNewElderly

    @PostMapping("/createNewElderly")
    public ResponseEntity<Elderly> createNewElderly(@RequestBody Elderly elderly) {
        Elderly createdElderly = elderlyService.createNewElderly(elderly);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElderly);
    }

    // http://localhost:8080/api/elderlies/updateElderly/{id}
    @PutMapping("/updateElderly/{id}")
    public ResponseEntity<Elderly> updateElderly(@PathVariable UUID id, @RequestBody Elderly elderly) {
        Elderly updatedElderly = elderlyService.updateElderly(id, elderly);
        return updatedElderly != null ? ResponseEntity.ok(updatedElderly) : ResponseEntity.notFound().build();
    }

    // http://localhost:8080/api/elderlies/deleteElderlyById/{id}
    @DeleteMapping("/deleteElderlyById/{id}")
    public ResponseEntity<String> deleteElderlyById(@PathVariable UUID id) {
        elderlyService.deleteElderlyById(id);
        return ResponseEntity.ok("Elderly with ID " + id + " has been successfully deleted.");
    }
}
