package com.example.techcloud.controller;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.serviceimpl.EvenementService;
import com.example.techcloud.serviceinterface.IEvenementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Gestion des événements")
@RequestMapping("/evenements")
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class EvenementController {
    IEvenementService iEvenementService;
    @PostMapping("/addEvenement")
    public Evenement addEvenement(@RequestBody Evenement evenement) {
        return iEvenementService.addEvenement(evenement);
    }
    @GetMapping("/AllEvenement")

    public List<Evenement> getAllEvenement() {
        return iEvenementService.getAllEvenement();
    }
    @GetMapping("/Evenement/{id}")
    public Evenement getEvenementById(@PathVariable("id") long id) {
        return iEvenementService.getEvenementById(id);
    }
    @DeleteMapping("/Evenement/{id}")
    public ResponseEntity<Evenement> deleteEvenement(@PathVariable long id){
        Evenement e =iEvenementService.getEvenementById(id);
        if (e==null)
            return ResponseEntity.notFound().build();
        iEvenementService.deleteEvenement(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/Evenement/{id}")

    public ResponseEntity<Evenement> updateEvenement(@PathVariable Long id , @RequestBody Evenement evenement) {
       Evenement e =iEvenementService.getEvenementById(id);
       if (e==null)
           return ResponseEntity.notFound().build();
       e.setNom(evenement.getNom());
       e.setNbrPlace(evenement.getNbrPlace());
       e.setDate_deb(evenement.getDate_deb());
       e.setDate_fin(evenement.getDate_fin());
       e.setLieu(evenement.getLieu());
       Evenement updateeEvenement = iEvenementService.updateEvenement(e);
       return ResponseEntity.ok(updateeEvenement);
    }
}


