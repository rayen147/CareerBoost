package com.example.techcloud.serviceimpl;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.repository.IEvenementRepository;
import com.example.techcloud.serviceinterface.IEvenementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class  EvenementService implements IEvenementService {
    IEvenementRepository iEvenementRepository;
    @Override
    public Evenement addEvenement(Evenement evenement) {
        return iEvenementRepository.save(evenement);
    }

    @Override
    public List<Evenement> getAllEvenement() {
        return iEvenementRepository.findAll();
    }

    @Override
    public Evenement getEvenementById(long id) {
        return iEvenementRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEvenement(long id) {
        iEvenementRepository.deleteById(id);

    }

    @Override
    public Evenement updateEvenement(Evenement evenement) {
        return iEvenementRepository.save(evenement);
    }
}
