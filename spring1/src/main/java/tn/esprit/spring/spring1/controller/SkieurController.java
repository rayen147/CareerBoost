package tn.esprit.spring.spring1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring1.Entity.Skieur;
import tn.esprit.spring.spring1.serviceInterface.ISkieurService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/Skieur")
@Tag(name="Gestion des skieurs")
public class SkieurController {
    ISkieurService Skieurcontroller ;
    @GetMapping("/AllSkieurs")
    public List<Skieur> retrieveAllSkieurs() {
        return Skieurcontroller.retrieveAllSkieurs();
    }
    @PostMapping("/addSkieur")
    public Skieur addSkieur(@RequestBody Skieur skieur) {
        return Skieurcontroller.addSkieur(skieur);
    }
    @DeleteMapping("/deleteSkieur/{numSkieur}")
    public void removeSkieur(@PathVariable("numSkieur") Long numSkieur) {
        Skieurcontroller.removeSkieur(numSkieur);
    }
    @GetMapping("/Skieur/{numSkieur}")
    public Skieur retrieveSkieur(@PathVariable("numSkieur") Long numSkieur) {
        return Skieurcontroller.retrieveSkieur(numSkieur);
    }
}
