package com.example.techcloud.serviceimpl;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.repository.ICommentaireRepository;
import com.example.techcloud.serviceinterface.ICommentaireService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService implements ICommentaireService {
ICommentaireRepository ICommentaireRepository;
    @Override
    public Commentaire addCommentaire(Commentaire commentaire) {
        return ICommentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> getAllCommentaire() {
        return ICommentaireRepository.findAll();
    }

    @Override
    public Commentaire getCommentaireById(long idCommentaire) {
        return ICommentaireRepository.findById(idCommentaire).orElse(null);
    }

    @Override
    public void deleteCommentaire(long idCommentaire) {
        ICommentaireRepository.deleteById(idCommentaire);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire commentaire) {
        return ICommentaireRepository.save(commentaire);
    }
}
