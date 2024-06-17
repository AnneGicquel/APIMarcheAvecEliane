package com.api.APIMarcheAvecEliane.service;

import com.api.APIMarcheAvecEliane.model.Elderly;
import com.api.APIMarcheAvecEliane.model.Outing;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.repository.ElderlyRepository;
import com.api.APIMarcheAvecEliane.repository.OutingRepository;
import com.api.APIMarcheAvecEliane.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutingService {

    private final OutingRepository outingRepository;
    private final ElderlyRepository elderlyRepository ;
    private final VolunteerRepository volunteerRepository ;

    @Autowired
    public OutingService(OutingRepository outingRepository,
                         ElderlyRepository elderlyRepository,
                         VolunteerRepository volunteerRepository) {
        this.outingRepository = outingRepository;
        this.elderlyRepository = elderlyRepository;
        this.volunteerRepository = volunteerRepository;
    }

    //////////////////////// OUTING's PART /////////////////////////////
    public List<Outing> getAllOutings() {
        return outingRepository.findAll();
    }

    public Optional<Outing> getOutingById(UUID id) {
        return outingRepository.findById(id);
    }

    public Outing createNewOuting(Outing outing) {
        return outingRepository.save(outing);
    }

    //////////////////////// OUTING's ELDERLY PART /////////////////////////////
    // 🟣 Get all outings by Elderly id
    public  List<Outing> getOutingsByElderlyId(UUID elderlyId) {
        return outingRepository.findByElderlyId(elderlyId);
    }
    // 🟣 Get one outing (by outing id) by Elderly id
    public Optional<Outing> getOutingByElderlyIdAndOutingId(UUID elderlyId, UUID outingId) {
        return outingRepository.findByElderlyIdAndId(elderlyId, outingId);
    }

    // 🟣 Update (item from List) outings by Elderly id
    public Outing updateOutingByElderlyId(UUID elderlyId, UUID outingId, Outing updatedOuting) {
        Optional<Elderly> elderly = elderlyRepository.findById(elderlyId);
        Optional<Outing> existingOuting = outingRepository.findById(outingId);

        if (elderly.isPresent() && existingOuting.isPresent()) {
            Outing outing = existingOuting.get();
            // Mettre à jour les dates de la sortie
            outing.setOutingDates(updatedOuting.getOutingDates());
            // Enregistrez la sortie mise à jour
            outingRepository.save(outing);
            return outing;
        } else {
            return null;
        }
    }

    // 🟣 Delete (item from List) outings by Elderly id
    public void deleteOutingByElderlyId(UUID elderlyId, UUID outingId) {
        Optional<Outing> outing = outingRepository.findByElderlyIdAndId(elderlyId, outingId);
        outing.ifPresent(outingRepository::delete);
    }

    // 🟣 Delete (all the List) all outings by Elderly id
    public void deleteAllOutingsByElderlyId(UUID elderlyId) {
        List<Outing> outings = outingRepository.findByElderlyId(elderlyId);
        outingRepository.deleteAll(outings);
    }



    //////////////////////// OUTING's VOLUNTEER PART /////////////////////////////

    // 🟣 Get all outings by Volunteer id
    public  List<Outing> getOutingsByVolunteerId(UUID volunteerId) {
        return outingRepository.findByVolunteerId(volunteerId);
    }

    // 🟣 Get one outing (by outing id) by Volunteer id
    public Optional<Outing> getOutingByVolunteerIdAndOutingId(UUID volunteerId, UUID outingId) {
        return outingRepository.findByVolunteerIdAndId(volunteerId, outingId);
    }

    // 🟣 Update (item from List) outings by Volunteer id
    public Outing updateOutingByVolunteerId(UUID volunteerId, UUID outingId, Outing updatedOuting) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(volunteerId);
        Optional<Outing> existingOuting = outingRepository.findById(outingId);

        if (volunteer.isPresent() && existingOuting.isPresent()) {
            Outing outing = existingOuting.get();
            // Mettre à jour les dates de la sortie
            outing.setOutingDates(updatedOuting.getOutingDates());
            // Enregistrez la sortie mise à jour
            outingRepository.save(outing);
            return outing;
        } else {
            return null;
        }
    }


    // 🟣 Delete (item from List) outings by Volunteer id
    public  void deleteOutingByVolunteerId(UUID volunteerId, UUID outingId) {
        Optional<Outing> outing = outingRepository.findByVolunteerIdAndId(volunteerId, outingId);
        outing.ifPresent(outingRepository::delete);
    }

    // 🟣 Delete (all the List) all outings by Volunteer id
    public  void deleteAllOutingsByVolunteerId(UUID volunteerId) {
        List<Outing> outings = outingRepository.findByVolunteerId(volunteerId);
        outingRepository.deleteAll(outings);
    }



    ///// OUTING PART ..... IF NEEDED LATER


    public Outing updateOutingById(UUID id, Outing updatedOuting) {
        if (outingRepository.existsById(id)) {
            updatedOuting.setId(id);
            return outingRepository.save(updatedOuting);
        }
        return null; // 🟣 throw exception to do !!
    }

    public void deleteOutingById(UUID id) {
        outingRepository.deleteById(id);
    }
}
