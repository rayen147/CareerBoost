package com.example.techcloudpi.ServiceInterfaces;

import com.example.techcloudpi.entity.Reclamation;

import java.util.List;

public interface Ireclamation {
    List<Reclamation> retrieveAllreclamations();
    Reclamation addReclamation(Reclamation reclamation, Long idUser);
    Reclamation updateReclamation (Long idrec, Reclamation updatedReclamation);
    Reclamation retrieveReclamation (Long idrec);
    void removeReclamation (Long idrec);
    List<Reclamation> getReclamationsUtilisateur(Long idUser);
}
