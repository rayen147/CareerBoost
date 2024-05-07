package com.example.techcloud.controller;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import com.example.techcloud.repository.IEvenementRepository;
import com.example.techcloud.serviceimpl.EvenementService;
import com.example.techcloud.serviceinterface.ICommentaireService;
import com.example.techcloud.serviceinterface.IEvenementService;
import com.example.techcloud.serviceinterface.ISMSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Tag(name="Gestion des événements")
@RequestMapping("/evenements")
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class EvenementController {
    IEvenementRepository iEvenementRepository;
    IEvenementService iEvenementService;
    ICommentaireService iCommentaireService;
    ISMSService ismsService;
    @PostMapping("/addEvenement")
    public Evenement addEvenement(@RequestParam("evenement") String evenement , @RequestParam("file")MultipartFile file ) throws JsonProcessingException {
        Evenement evenement1= new ObjectMapper().readValue(evenement , Evenement.class);
        return iEvenementService.addEvenement(evenement1,file);
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
       e.setLieu(evenement.getLieu());
       Evenement updateeEvenement = iEvenementService.updateEvenement(e);
       return ResponseEntity.ok(updateeEvenement);
    }

    @PostMapping("/commentaires/{idEvenement}")
    public ResponseEntity<Commentaire> ajouterCommentaire(@PathVariable Long idEvenement, @RequestBody String contenuCommentaire) {
        iEvenementService.ajouterCommentaire(idEvenement, contenuCommentaire);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/commentaires/{evenementId}")
    public List<Commentaire> getCommentaires(@PathVariable Long evenementId) {
        return iEvenementService.getCommentairesByEvenementId(evenementId);
    }
    @PutMapping("/{evenementId}/commentaires/{commentaireId}")
    public ResponseEntity<Void> updateCommentaire(@PathVariable Long evenementId, @PathVariable Long commentaireId, @RequestBody String nouvelleDescription) {
        iEvenementService.updateCommentaire(evenementId, commentaireId, nouvelleDescription);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{evenementId}/commentaires/{commentaireId}")
    public ResponseEntity<Void> supprimerCommentaire(@PathVariable Long evenementId, @PathVariable Long commentaireId) {
        iEvenementService.supprimerCommentaire(evenementId, commentaireId);
        return ResponseEntity.ok().build();
    }

    // ... (autres méthodes)

    @GetMapping("/stats")
    public Map<Long, Integer> getEventParticipationCounts() {
        Map<Long, Integer> eventParticipationCounts = iCommentaireService.getEventParticipationCounts();
        return eventParticipationCounts;
    }

@GetMapping("/sendSms")
    public void sendSms(String to ,String message)
{
    ismsService.sendSms(to,"Quelqu'un a ajouté un commentaire");
}
}




