package com.example.techcloud.serviceinterface;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;

import java.util.List;

public interface ICommentaireService {
    Commentaire addCommentaire(Commentaire commentaire);
    List<Commentaire> getAllCommentaire();
    Commentaire getCommentaireById(long idCommentaire);
    void deleteCommentaire(long idCommentaire);
    Commentaire updateCommentaire(Commentaire commentaire);
}
