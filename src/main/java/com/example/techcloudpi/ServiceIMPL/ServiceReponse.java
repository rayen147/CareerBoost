package com.example.techcloudpi.ServiceIMPL;

import com.example.techcloudpi.Repository.ReclamationRep;
import com.example.techcloudpi.Repository.ReponseRep;
import com.example.techcloudpi.ServiceInterfaces.EmailService;
import com.example.techcloudpi.ServiceInterfaces.Ireponse;
import com.example.techcloudpi.entity.Reclamation;
import com.example.techcloudpi.entity.Reponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceReponse implements Ireponse {
    ReponseRep reponseRepository;
    ReclamationRep reclamationRep;
    EmailService emailService;
    @Override
    public List<Reponse> retrieveAllreponses() {

        return reponseRepository.findAll();
    }

    @Override
    public Reponse addReponse(Reponse reponse, Long idrec) {
        Optional<Reclamation> optionalrec = reclamationRep.findById(idrec);
        Reclamation rec = optionalrec.get();
        reponse.setReclamation(rec);
        rec.setStatut("traite");
        reclamationRep.save(rec); // Enregistrement de la réclamation mise à jour

        // Enregistrer la réponse dans la base de données
        Reponse savedReponse = reponseRepository.save(reponse);

        // Envoyer un e-mail pour informer que la réclamation a été traitée
        String toEmail = rec.getEmailrec(); // L'adresse e-mail du destinataire
        String subject = "Réponse à votre réclamation";
        String body = "Votre réclamation a été traitée avec succès. Voici la description de la réponse : " + reponse.getDescription();
        emailService.sendMail(toEmail, subject, body);

        return savedReponse;
    }

    @Override
    public Reponse updateReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public Reponse retrieveReponse(Long idrep) {
        return reponseRepository.findById(idrep).orElse(null);
    }

    @Override
    public void removeReponse(Long idrep) {

    }
}
