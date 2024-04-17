package tn.esprit.spring.spring1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring1.Entity.Moniteur;
import tn.esprit.spring.spring1.serviceInterface.IMoniteurService;

import java.util.List;
@Tag(name="Gestion des moniteurs")
@RequestMapping("/Moniteur")
@RestController
@AllArgsConstructor
public class MoniteurController {
    IMoniteurService Moniteurcontroller;
    @GetMapping("/AllMoniteurs")
    public List<Moniteur> retrieveAllMoniteurs() {
        return Moniteurcontroller.retrieveAllMoniteurs();
    }
    @PostMapping("/addMoniteur")
    public Moniteur addMoniteur(@RequestBody Moniteur moniteur) {
        return Moniteurcontroller.addMoniteur(moniteur);
    }
    @PutMapping("/updateMoniteur")
    public Moniteur updateMoniteur(@RequestBody Moniteur moniteur) {
        return Moniteurcontroller.updateMoniteur(moniteur);
    }
    @GetMapping("/Moniteur/{numMoniteur}")
    public Moniteur retrieveMoniteur(@PathVariable("numMoniteur") Long numMoniteur) {
        return Moniteurcontroller.retrieveMoniteur(numMoniteur);
    }
}
