package tn.esprit.spring.spring1.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring1.Entity.Inscription;
import tn.esprit.spring.spring1.Entity.Skieur;
import tn.esprit.spring.spring1.Repository.InscriptionRepository;
import tn.esprit.spring.spring1.Repository.SkieurRepository;
import tn.esprit.spring.spring1.serviceInterface.IInscriptionService;
import java.util.List;
@Service
@AllArgsConstructor
public class InscriptionServiceImpl implements IInscriptionService{
    SkieurRepository skieurRepository;
    InscriptionRepository inscriptionRepository;

    @Override
    public Inscription addInscriptionAndAssignToSkier(Inscription inscription, Long numSkieur) {
        Skieur skieur = skieurRepository.findById(numSkieur).orElse(null);
        inscription.setSkieur(skieur);
        return inscriptionRepository.save(inscription);
    }
}



