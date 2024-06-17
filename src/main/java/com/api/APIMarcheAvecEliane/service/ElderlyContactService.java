package com.api.APIMarcheAvecEliane.service;

import com.api.APIMarcheAvecEliane.model.ElderlyContact;
import com.api.APIMarcheAvecEliane.repository.ElderlyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ElderlyContactService {

    private final ElderlyContactRepository elderlyContactRepository;

    @Autowired
    public ElderlyContactService(ElderlyContactRepository elderlyContactRepository) {
        this.elderlyContactRepository = elderlyContactRepository;
    }

    public List<ElderlyContact> getAllElderlyContacts() {
        return elderlyContactRepository.findAll();
    }

    public Optional<ElderlyContact> getElderlyContactById(UUID id) {
        return elderlyContactRepository.findById(id);
    }

    public ElderlyContact createNewElderlyContact(ElderlyContact elderlyContact) {
        return elderlyContactRepository.save(elderlyContact);
    }

    public ElderlyContact updateElderlyContactById(UUID id, ElderlyContact updatedElderlyContact) {
        if (elderlyContactRepository.existsById(id)) {
            updatedElderlyContact.setId(id);
            return elderlyContactRepository.save(updatedElderlyContact);
        }
        return null; // 🟣 throw exception to do !!
    }

    public void deleteElderlyContactById(UUID id) {
        elderlyContactRepository.deleteById(id);
    }
}
