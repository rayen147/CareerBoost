package tn.esprit.spring.spring1.serviceInterface;

import tn.esprit.spring.spring1.Entity.Cours;
import tn.esprit.spring.spring1.Entity.Inscription;

import java.util.List;

public interface IInscriptionService {

    Inscription addInscriptionAndAssignToSkier(Inscription inscription, Long numSkieur);

}
