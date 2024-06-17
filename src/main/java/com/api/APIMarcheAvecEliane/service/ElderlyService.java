package com.api.APIMarcheAvecEliane.service;


import com.api.APIMarcheAvecEliane.model.Elderly;
import com.api.APIMarcheAvecEliane.repository.ElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ElderlyService {

    private final ElderlyRepository elderlyRepository;

    @Autowired // @Autowired injection automatiquement de bean selon le besoin
    public ElderlyService(ElderlyRepository elderlyRepository) {
        this.elderlyRepository = elderlyRepository;
    }

    public boolean existsById(UUID id) {
        return elderlyRepository.existsById(id);
    }

    public List<Elderly> getAllElderlies() {
        return elderlyRepository.findAll();
    }

    public Optional<Elderly> getElderlyById(UUID id) {
        return elderlyRepository.findById(id);
    }

    public Elderly createNewElderly(Elderly elderly) {
        return elderlyRepository.save(elderly);
    }

    public Elderly updateElderly(UUID id, Elderly updatedElderly) {
        if (elderlyRepository.existsById(id)) {
            updatedElderly.setId(id);
            return elderlyRepository.save(updatedElderly);
        }
        return null; // 🟣 throw exception to do !!
    }

    public void deleteElderlyById(UUID id) {
        elderlyRepository.deleteById(id);
    }
}