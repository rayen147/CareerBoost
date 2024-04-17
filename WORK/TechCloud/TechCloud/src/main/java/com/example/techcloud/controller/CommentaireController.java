package com.example.techcloud.controller;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.serviceinterface.ICommentaireService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Gestion des commentaires")
@RequestMapping("/commentaires")
@RestController
@AllArgsConstructor
public class CommentaireController {
    ICommentaireService iCommentaireService;
    @PostMapping("/addCommentaire")
    public Commentaire addCommentaire(@RequestBody Commentaire commentaire) {
        return iCommentaireService.addCommentaire(commentaire);
    }
    @GetMapping("/AllCommentaires")
    public List<Commentaire> getAllCommentaire() {
        return iCommentaireService.getAllCommentaire();
    }
    @GetMapping("/Commentaire/{idCommentaire}")

    public Commentaire getCommentaireById(@PathVariable("idCommentaire") long idCommentaire) {
        return iCommentaireService.getCommentaireById(idCommentaire);
    }

    public void deleteCommentaire(long idCommentaire) {
        iCommentaireService.deleteCommentaire(idCommentaire);
    }
    @PutMapping("/updateCommentaire")
    public Commentaire updateCommentaire(@RequestBody Commentaire commentaire) {
        return iCommentaireService.updateCommentaire(commentaire);
    }
}
