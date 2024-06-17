package com.api.APIMarcheAvecEliane.controller;

import com.api.APIMarcheAvecEliane.model.Outing;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.service.OutingService;
import com.api.APIMarcheAvecEliane.service.VolunteerService;
import com.api.APIMarcheAvecEliane.service.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/outings")
public class OutingController {

    private final OutingService outingService;
    private final VolunteerService volunteerService;
    //private final ElderlyService elderlyService;
    @Autowired
    public OutingController(
            OutingService outingService,
            VolunteerService volunteerService
            //ElderlyService elderlyService
    ) {
        this.outingService = outingService;
        this.volunteerService = volunteerService;
        //this.elderlyService = elderlyService;
    }

    //////////////////////// 🟣 OUTING's PART 🟣 /////////////////////////////

    // http://localhost:8080/api/outings/getAllOutings
    @GetMapping("/getAllOutings")
    public List<Outing> getAllOutings() {
        return outingService.getAllOutings();
    }

    // http://localhost:8080/api/outings/getOutingById/{id}
    @GetMapping("/getOutingById/{id}")
    public ResponseEntity<Outing> getOutingById(@PathVariable UUID id) {
        Optional<Outing> outing = outingService.getOutingById(id);
        return outing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // http://localhost:8080/api/outings/createNewOuting
    @PostMapping("/createNewOuting")
    public ResponseEntity<Outing> createNewOuting(@RequestBody Outing outing) {
        Outing createdOuting = outingService.createNewOuting(outing);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOuting);
    }


    //////////////////////// OUTING's ELDERLY PART /////////////////////////////

    // 🟣 Get all outings by Elderly id
    // http://localhost:8080/api/outings/getOutingsByElderlyId/e6c344e5-f5a0-4fb7-9962-95bd12b0e951
    @GetMapping("/getOutingsByElderlyId/{elderlyId}")
    public ResponseEntity<List<Outing>> getOutingsByElderlyId(@PathVariable UUID elderlyId) {
        List<Outing> outings = outingService.getOutingsByElderlyId(elderlyId);
        if (outings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(outings);
    }
    // 🟣 Get one outing (by outing id) by Elderly id

    // http://localhost:8080/api/outings/getOutingByElderlyId/e6c344e5-f5a0-4fb7-9962-95bd12b0e951/outing/9d2b4af1-e04b-4ac1-9c3b-2cb01a5dd072
    @GetMapping("/getOutingByElderlyId/{elderlyId}/outing/{outingId}")
    public ResponseEntity<Outing> getOutingByElderlyIdAndOutingId(@PathVariable UUID elderlyId, @PathVariable UUID outingId) {
        Optional<Outing> outing = outingService.getOutingByElderlyIdAndOutingId(elderlyId, outingId);
        return outing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

     // 🟣 Update (item from List) outings by Elderly id

    // http://localhost:8080/api/outings/updateOutingByElderlyId/cee01e53-eb48-4a32-9024-aa15e6f5cda0/outing/a61e9037-8698-471d-a039-a6dca66ba4e7
    @PutMapping("/updateOutingByElderlyId/{elderlyId}/outing/{outingId}")
    public ResponseEntity<Outing> updateOutingByElderlyId(@PathVariable UUID elderlyId, @PathVariable UUID outingId, @RequestBody Outing updatedOuting) {
        Outing outing = outingService.updateOutingByElderlyId(elderlyId, outingId, updatedOuting);
        if (outing != null) {
            return ResponseEntity.ok(outing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🟣 Delete (item from List) outings by Elderly id

    // http://localhost:8080/api/outings/deleteOutingByElderlyId/e6c344e5-f5a0-4fb7-9962-95bd12b0e951/outing/9d2b4af1-e04b-4ac1-9c3b-2cb01a5dd072
    @DeleteMapping("/deleteOutingByElderlyId/{elderlyId}/outing/{outingId}")
    public ResponseEntity<?> deleteOutingByElderlyId(@PathVariable UUID elderlyId, @PathVariable UUID outingId) {
        outingService.deleteOutingByElderlyId(elderlyId, outingId);
        return ResponseEntity.ok("The outing"+ outingId + "of this Elderly ID" + elderlyId + "has been successfully deleted 🧽 ");
    }

    // 🟣 Delete (all the List) all outings by Elderly id

    // http://localhost:8080/api/outings/deleteAllOutingsByElderlyId/e6c344e5-f5a0-4fb7-9962-95bd12b0e951
    @DeleteMapping("/deleteAllOutingsByElderlyId/{elderlyId}")
    public ResponseEntity<?> deleteAllOutingsByElderlyId(@PathVariable UUID elderlyId) {
        outingService.deleteAllOutingsByElderlyId(elderlyId);
        return ResponseEntity.ok("All the outings of this Elderly ID" + elderlyId + "has been successfully deleted 🧽 ");
    }



    //////////////////////// OUTING's VOLUNTEER PART /////////////////////////////
    // 🟣 Get all outings by Volunteer id

    // http://localhost:8080/api/outings/getOutingsByVolunteerId/cee01e53-eb48-4a32-9024-aa15e6f5cda0
    @GetMapping("/getOutingsByVolunteerId/{volunteerId}")
    public ResponseEntity<List<Outing>> getOutingsByVolunteerId(@PathVariable UUID volunteerId) {
        List<Outing> outings = outingService.getOutingsByVolunteerId(volunteerId);
        if (outings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(outings);
    }
    // 🟣 Get one outing (by outing id) by Volunteer id

    // http://localhost:8080/api/outings/getOutingByVolunteerId/cee01e53-eb48-4a32-9024-aa15e6f5cda0/outing/a61e9037-8698-471d-a039-a6dca66ba4e7
    @GetMapping("/getOutingByVolunteerId/{volunteerId}/outing/{outingId}")
    public ResponseEntity<Outing> getOutingByVolunteerIdAndOutingId(@PathVariable UUID volunteerId, @PathVariable UUID outingId) {
        Optional<Outing> outing = outingService.getOutingByVolunteerIdAndOutingId(volunteerId, outingId);
        return outing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // 🟣 Update (item from List) outings by Volunteer id

    // http://localhost:8080/api/outings/updateOutingByVolunteerId/cee01e53-eb48-4a32-9024-aa15e6f5cda0/outing/a61e9037-8698-471d-a039-a6dca66ba4e7
    @PutMapping("/updateOutingByVolunteerId/{volunteerId}/outing/{outingId}")
    public ResponseEntity<Outing> updateOutingByVolunteerId(@PathVariable UUID volunteerId, @PathVariable UUID outingId, @RequestBody Outing updatedOuting) {
        Outing outing = outingService.updateOutingByVolunteerId(volunteerId, outingId, updatedOuting);
        if (outing != null) {
            return ResponseEntity.ok(outing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 🟣 Delete (item from List) outings by Volunteer id

    // http://localhost:8080/api/outings/deleteOutingByVolunteerId/cee01e53-eb48-4a32-9024-aa15e6f5cda0/outing/a61e9037-8698-471d-a039-a6dca66ba4e7
    @DeleteMapping("/deleteOutingByVolunteerId/{volunteerId}/outing/{outingId}")
    public ResponseEntity<?> deleteOutingByVolunteerId(@PathVariable UUID volunteerId, @PathVariable UUID outingId) {
        outingService.deleteOutingByVolunteerId(volunteerId, outingId);
        return ResponseEntity.ok("The outing"+ outingId + "of this Volunteer ID" + volunteerId + "has been successfully deleted 🧽 ");
    }
    // 🟣 Delete (all the List) all outings by Volunteer id

    // http://localhost:8080/api/outings/deleteAllOutingsByVolunteerId/cee01e53-eb48-4a32-9024-aa15e6f5cda0
    @DeleteMapping("/deleteAllOutingsByVolunteerId/{volunteerId}")
    public ResponseEntity<?> deleteAllOutingsByVolunteerId(@PathVariable UUID volunteerId) {
        outingService.deleteAllOutingsByVolunteerId(volunteerId);
        return ResponseEntity.ok("All the outings of this Volunteer ID" + volunteerId + "has been successfully deleted 🧽 ");
    }


    ///// OUTING PART ..... IF NEEDED LATER

    // http://localhost:8080/api/outings/updateOuting/{id}
    @PutMapping("/updateOuting/{id}")
    public ResponseEntity<Outing> updateOuting(@PathVariable UUID id, @RequestBody Outing outing) {
        Outing updatedOuting = outingService.updateOutingById(id, outing);
        return updatedOuting != null ? ResponseEntity.ok(updatedOuting) : ResponseEntity.notFound().build();
    }

    // http://localhost:8080/api/outings/deleteOutingById/{id}
    @DeleteMapping("/deleteOutingById/{id}")
    public ResponseEntity<String> deleteOutingById(@PathVariable UUID id) {
        outingService.deleteOutingById(id);
        return ResponseEntity.ok("Outing with ID " + id + " has been successfully deleted 🧽 ");
    }
}
