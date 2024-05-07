package com.example.techcloud.serviceinterface;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICommentaireService {
    Commentaire addCommentaire(Commentaire commentaire);
    List<Commentaire> getAllCommentaire();
    Commentaire getCommentaireById(long id);
    void deleteCommentaire(long id);
    Commentaire updateCommentaire(Commentaire commentaire);


    //public void affecterCommentaireEvenement (Long idcommentaire, Long idevent);

    public Map<Long, Integer> getEventParticipationCounts();
}
