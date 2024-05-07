package com.example.techcloud.serviceinterface;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IEvenementService {
    Evenement addEvenement(Evenement evenement , MultipartFile file);
    List<Evenement> getAllEvenement();
    Evenement getEvenementById(long id);
    void deleteEvenement(long id);
    Evenement updateEvenement(Evenement evenement);
    public void ajouterCommentaire(Long evenementId, String contenuCommentaire);
    public List<Commentaire> getCommentairesByEvenementId(Long evenementId);
    public void updateCommentaire(Long evenementId, Long commentaireId, String nouvelleDescription);
    public void supprimerCommentaire(Long evenementId, Long commentaireId);








}
