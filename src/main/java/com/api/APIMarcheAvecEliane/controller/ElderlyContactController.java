package com.api.APIMarcheAvecEliane.controller;

import com.api.APIMarcheAvecEliane.model.ElderlyContact;
import com.api.APIMarcheAvecEliane.service.ElderlyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/elderlyContacts")
public class ElderlyContactController {

    private final ElderlyContactService elderlyContactService;

    @Autowired
    public ElderlyContactController(ElderlyContactService elderlyContactService) {
        this.elderlyContactService = elderlyContactService;
    }

    // http://localhost:8080/api/elderlyContacts/getAllElderlyContacts
    @GetMapping("/getAllElderlyContacts")
    public List<ElderlyContact> getAllElderlyContacts() {
        return elderlyContactService.getAllElderlyContacts();
    }

    // http://localhost:8080/api/elderlyContacts/getElderlyContactById/{id}
    @GetMapping("/getElderlyContactById/{id}")
    public ResponseEntity<ElderlyContact> getElderlyContactById(@PathVariable UUID id) {
        Optional<ElderlyContact> elderlyContact = elderlyContactService.getElderlyContactById(id);
        return elderlyContact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // http://localhost:8080/api/elderlyContacts/createNewElderlyContact
    @PostMapping("/createNewElderlyContact")
    public ResponseEntity<ElderlyContact> createNewElderlyContact(@RequestBody ElderlyContact elderlyContact) {
        ElderlyContact createdElderlyContact = elderlyContactService.createNewElderlyContact(elderlyContact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElderlyContact);
    }

    // http://localhost:8080/api/elderlyContacts/updateElderlyContact/{id}
    @PutMapping("/updateElderlyContact/{id}")
    public ResponseEntity<ElderlyContact> updateElderlyContact(@PathVariable UUID id, @RequestBody ElderlyContact elderlyContact) {
        ElderlyContact updatedElderlyContact = elderlyContactService.updateElderlyContactById(id, elderlyContact);
        return updatedElderlyContact != null ? ResponseEntity.ok(updatedElderlyContact) : ResponseEntity.notFound().build();
    }

    // http://localhost:8080/api/elderlyContacts/deleteElderlyContactById/{id}
    @DeleteMapping("/deleteElderlyContactById/{id}")
    public ResponseEntity<String> deleteElderlyContactById(@PathVariable UUID id) {
        elderlyContactService.deleteElderlyContactById(id);
        return ResponseEntity.ok("ElderlyContact with ID " + id + " has been successfully deleted 🧽 ");
    }
}
