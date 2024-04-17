package tn.esprit.spring.spring1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.spring.spring1.Entity.Inscription;
import tn.esprit.spring.spring1.serviceInterface.IInscriptionService;
import tn.esprit.spring.spring1.serviceInterface.IMoniteurService;

public class InscriptionController {
    IInscriptionService Inscriptioncontroller;
    @PostMapping("/addInscriptionAndAssignToSkier")
    public Inscription addInscriptionAndAssignToSkier(@RequestBody Inscription inscription, Long numSkieur) {
        return Inscriptioncontroller.addInscriptionAndAssignToSkier(inscription, numSkieur);
    }
}
