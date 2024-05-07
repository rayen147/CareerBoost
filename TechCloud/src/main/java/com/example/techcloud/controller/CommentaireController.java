package com.example.techcloud.controller;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import com.example.techcloud.serviceimpl.CommentaireService;
import com.example.techcloud.serviceinterface.ICommentaireService;
//import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.techcloud.serviceinterface.IEvenementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Tag(name="Gestion des commentaires")
@RequestMapping("/commentaires")
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CommentaireController {
    IEvenementService eventService;
    ICommentaireService iCommentaireService;
    @PostMapping("/addCommentaire")
    public Commentaire addCommentaire(@RequestBody Commentaire commentaire) {
        return iCommentaireService.addCommentaire(commentaire);
    }
    @GetMapping("/AllCommentaires")
    public List<Commentaire> getAllCommentaire() {
        return iCommentaireService.getAllCommentaire();
    }
    @GetMapping("/Commentaire/{id}")


    public Commentaire getCommentaireById(@PathVariable("id") long id) {
        return iCommentaireService.getCommentaireById(id);
    }

    public void deleteCommentaire(long id) {
        iCommentaireService.deleteCommentaire(id);
    }
    @PutMapping("/updateCommentaire")
    public Commentaire updateCommentaire(@RequestBody Commentaire commentaire) {
        return iCommentaireService.updateCommentaire(commentaire);
    }
    /*
    @PutMapping("/affect/{idcommentaire}/{idevent}")
    public void affecterCommentaireEvenement(@PathVariable("idcommentaire") Long idcommentaire , @PathVariable("idevent")Long idevent) {
        iCommentaireService.affecterCommentaireEvenement(idcommentaire,idevent);
    }*/

    @GetMapping("/statistics")
    public Map<Long, Integer> getEventParticipationCounts() {
        Map<Long, Integer> eventParticipationCounts = iCommentaireService.getEventParticipationCounts();
        return eventParticipationCounts;
    }


}
