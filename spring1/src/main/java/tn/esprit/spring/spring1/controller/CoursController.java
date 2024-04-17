package tn.esprit.spring.spring1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring1.Entity.Cours;
import tn.esprit.spring.spring1.serviceInterface.ICoursService;

import java.util.List;
@RequestMapping("/Cours")
@RestController
@AllArgsConstructor
@Tag(name="Gestion des cours")
public class CoursController {
    ICoursService Courscontroller ;
    @Operation(description = "Cette méthode permet d'afficher tout les cours")
    @GetMapping("/Allcours")
    public List<Cours> retrieveAllCourses() {
        return Courscontroller.retrieveAllCourses();
    }
    @PostMapping("/addCours")
    public Cours addCours(@RequestBody Cours cours) {
        return Courscontroller.addCours(cours);
    }
    @PutMapping("/updateCours")
    public Cours updateCours(@RequestBody Cours cours) {
        return Courscontroller.updateCours(cours);
    }
    @GetMapping("/Cours/{numCours}")
    public Cours retrieveCours(@PathVariable("numCours") Long numCours) {
        return Courscontroller.retrieveCours(numCours);
    }
}
