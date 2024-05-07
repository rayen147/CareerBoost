package com.example.techcloudpi.ServiceIMPL;

import com.example.techcloudpi.Repository.ReclamationRep;
import com.example.techcloudpi.Repository.UserRep;
import com.example.techcloudpi.ServiceInterfaces.Ireclamation;
import com.example.techcloudpi.entity.Reclamation;
import com.example.techcloudpi.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceReclamation implements Ireclamation {
@Autowired
    ReclamationRep reclamationRepository;
    UserRep userRep;
    SmsService smsService;

    @Override
    public List<Reclamation> retrieveAllreclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation, Long idUser) {
        Optional<User> optionalUser = userRep.findById(idUser);
        User user = optionalUser.get();
        reclamation.setUser(user);
        reclamation.setStatut("enAttente");

        // Enregistrer la réclamation
        Reclamation savedReclamation = reclamationRepository.save(reclamation);

        // Envoyer un SMS
        String message = "Nouvelle réclamation ajoutée avec succès : " + reclamation.getDescription();
        smsService.sendSms("+21654441354", message);

        return savedReclamation;
    }

    @Override
    public Reclamation updateReclamation(Long idrec, Reclamation updatedReclamation) {
            Reclamation existingReclamation = reclamationRepository.findById(idrec)
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + idrec));

        existingReclamation.setDescription(updatedReclamation.getDescription());
        existingReclamation.setEmailrec(updatedReclamation.getEmailrec());
        existingReclamation.setObjet(updatedReclamation.getObjet());
            return reclamationRepository.save(existingReclamation);

    }


    @Override
    public Reclamation retrieveReclamation(Long idrec) {
        return reclamationRepository.findById(idrec).orElse(null);
    }

    @Override
    public void removeReclamation(Long idrec) { reclamationRepository.deleteById(idrec);

    }

    @Override
    public List<Reclamation> getReclamationsUtilisateur(Long idUser) {
        return reclamationRepository.findByUserIdUser(idUser);
    }
}
