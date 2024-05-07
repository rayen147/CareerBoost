package com.example.techcloud.serviceimpl;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import com.example.techcloud.repository.ICommentaireRepository;
import com.example.techcloud.repository.IEvenementRepository;
import com.example.techcloud.serviceinterface.ICommentaireService;
import com.example.techcloud.serviceinterface.IEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentaireService implements ICommentaireService {

    @Autowired
    private ICommentaireRepository commentaireRepository;
    private IEvenementRepository iEvenementRepository;
    private IEvenementService iEvenementService;

    @Override
    public Commentaire addCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> getAllCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getCommentaireById(long id) {
        return commentaireRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCommentaire(long id) {
        commentaireRepository.deleteById(id);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Map<Long, Integer> getEventParticipationCounts() {
        List<Object[]> counts = commentaireRepository.countParticipationsByEvent();
        Map<Long, Integer> eventParticipationCounts = new HashMap<>();
        for (Object[] count : counts) {
            Long eventId = ((Number) count[0]).longValue(); // Convertir en Long
            Long participationCount = (Long) count[1]; // Pas besoin de conversion
            eventParticipationCounts.put(eventId, participationCount.intValue()); // Convertir en Integer
        }
        return eventParticipationCounts;
    }


/*
    @Override
    public void affecterCommentaireEvenement(Long idcommentaire, Long idevent) {
        Evenement event = iEvenementRepository.findById(idevent).get() ;
        Commentaire commentaire = commentaireRepository.findById(idcommentaire).get();
        commentaire.setEvenement(event);
        commentaireRepository.save(commentaire);

    }
    */

    /*public Commentaire addComment(String text, long id) {
        Evenement event = iEvenementRepository.findById(id).orElse(null);
        Commentaire comment = new Commentaire();

        comment.setEvenement(event);
        comment.setDescription(text);
        return commentaireRepository.save(comment);
    }*/

}
