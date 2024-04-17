package com.example.techcloud.serviceinterface;

import com.example.techcloud.entity.Evenement;

import java.util.List;

public interface IEvenementService {
    Evenement addEvenement(Evenement evenement);
    List<Evenement> getAllEvenement();
    Evenement getEvenementById(long id);
    void deleteEvenement(long id);
    Evenement updateEvenement(Evenement evenement);
}
