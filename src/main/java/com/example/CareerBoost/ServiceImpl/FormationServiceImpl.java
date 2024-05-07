package com.example.CareerBoost.ServiceImpl;


import com.example.CareerBoost.Entity.Formation;
import com.example.CareerBoost.Entity.User;
import com.example.CareerBoost.Repository.FormationRepository;
import com.example.CareerBoost.Repository.UserRepository;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService {
    FormationRepository formationRepository;
    UserRepository userRepository;
    @Override
    public List<Formation> retrieveAllFormations(Sort titre) {
        return formationRepository.findAll(Sort.by(Sort.Order.asc("titre")));
    }


    @Override
    public List<Formation> retrieveAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation addFormation(@NotNull @Valid Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation updateFormation(@NotNull @Valid Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation retrieveFormation(Long id) {
        return formationRepository.findById(id).orElse(null);
    }

    @Override
    public void removeFormation(Long id) {
        formationRepository.deleteById(id);

    }
    @Override
    public List<Formation> searchFormationsBytitre(String titre) {
        return formationRepository.findFormationBytitreContainingIgnoreCase(titre);
    }

    @Override
    public List<Formation> findBydateDebut(LocalDate dateDebut) {
        return formationRepository.findBydateDebut(dateDebut);
    }
   /* @Override
    public Formation participateInFormation(Long formationId, Long userId) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new IllegalArgumentException("Formation not found with id: " + formationId));

        if (formation.getParticipants().size() < formation.getLimiteParticipants()) {
            User participant = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            formation.addParticipant(participant);
            return formationRepository.save(formation);
        } else {
            throw new IllegalStateException("La formation est pleine. Impossible d'ajouter plus de participants.");
        }
    }*/


    @Override
    public Formation participateInFormation(Long formationId, Integer userId) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation not found"));

        if (formation.getLimiteParticipants() != null && formation.getLimiteParticipants() <= formation.getParticipants().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formation limit reached");
        }

        // Votre logique pour participer à la formation
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        formation.getParticipants().add(user);
        formationRepository.save(formation);

        return formation; // Retournez l'objet Formation
    }


}
