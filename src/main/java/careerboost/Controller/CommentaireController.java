package careerboost.Controller;

import careerboost.Entity.Commentaire;
import careerboost.Entity.Post;
import careerboost.ServiceInterface.IcommentaireService;
import careerboost.ServiceInterface.IpostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "gestion Forum")
@RestController
//@Controller
@RequestMapping("/commentaire")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CommentaireController {
    IcommentaireService icommentaireService;
     IpostService ipostService;
    @GetMapping("/retrieveallCommentaires")
    public List<Commentaire> retrieveAllCommentaires() {
        return icommentaireService.retrieveAllCommentaires();
    }
    @GetMapping("/retrieveCommentairesForPost/{postId}")
    public List<Commentaire> retrieveCommentairesForPost(@PathVariable Long postId) {
        return icommentaireService.retrieveCommentairesForPost(postId);
    }

    /*@PostMapping("/addCommentaire")
    @ResponseBody
    public Commentaire addCommentaire(@Valid @RequestBody Commentaire commentaire) {
        return icommentaireService.addCommentaire(commentaire);
    }*/
    @PostMapping("/addCommentaire")
    public ResponseEntity<Map<String, Object>> addCommentaire(@Valid @RequestBody Commentaire commentaire, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Commentaire addedCommentaire = icommentaireService.addCommentaire(commentaire);
            response.put("success", true);
            response.put("message", "Commentaire ajouté avec succès");
            response.put("commentaire", addedCommentaire);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    @PostMapping("/add-commentaire/{postId}")
    public ResponseEntity<Map<String, Object>> addCommentaireToPost(@PathVariable Long postId, @Valid @RequestBody Commentaire commentaire, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            // Vérifiez si le post existe
            Post post = ipostService.retrievePost(postId);
            if (post == null) {
                response.put("success", false);
                response.put("message", "Post not found");
                return ResponseEntity.notFound().build();
            }

            // Ajoutez le commentaire au post
            commentaire.setPost(post);
            Commentaire addedCommentaire = icommentaireService.addCommentaire(commentaire);
            response.put("success", true);
            response.put("message", "Commentaire ajouté avec succès");
            response.put("commentaire", addedCommentaire);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    @PutMapping("/updateCommentaire/{id}")
    public ResponseEntity<Map<String, Object>> updateCommentaire(@PathVariable Long id, @Valid @RequestBody  Commentaire commentaire, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            Commentaire existingCommentaire = icommentaireService.retrieveCommentaire(id);
            if (existingCommentaire == null) {
            response.put("success", false);
            response.put("message", "Commentaire not found");

                return ResponseEntity.notFound().build();
        }


        // Mettre à jour les champs du certificat existant avec les valeurs fournies
            existingCommentaire.setDatePublication(commentaire.getDatePublication());
            existingCommentaire.setContenu(commentaire.getContenu());


        // Mettre à jour le certificat dans la base de données
            Commentaire updatedCommentaire = icommentaireService.updateCommentaire(existingCommentaire);

        // Répondre avec les détails du certificat mis à jour
        response.put("success", true);
        response.put("message", "commentaire mis à jour avec succès");
        response.put("commentaire", updatedCommentaire);
        return ResponseEntity.ok(response);
    }
    }


   /* @GetMapping("/retrieveCommentaire/{id}")
    public Commentaire retrieveCommentaire(@PathVariable Long id) {
        return icommentaireService.retrieveCommentaire(id);
    }*/
   @GetMapping("/retrieveCommentaire/{id}")
   public Commentaire retrieveCommentaire(@PathVariable Long id) {
       if (id == null) {
           // Gérer le cas où l'ID est null, peut-être renvoyer une exception ou un résultat vide
           return null;
       } else {
           return icommentaireService.retrieveCommentaire(id);
       }
   }
    @GetMapping("/commentaires/{postId}")
    public ResponseEntity<?> getCommentairesByPostId(@PathVariable Long postId) {
        try {
            List<Commentaire> commentaires = icommentaireService.retrieveCommentairesForPost(postId);
            return ResponseEntity.ok(commentaires);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite.");
        }
    }

    @DeleteMapping("/removeCommentaire/{id}")
    public ResponseEntity<String> removeCommentaire(@PathVariable("id") Long id) {
        try {
            icommentaireService.removeCommentaire(id);
            return ResponseEntity.ok("Commentaire supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression du commentaire");
        }
    }

    /* @GetMapping({"/retrieveAllCommentaire"})
    public String retrieveAllCommentaires(Model model) {
        //List<Commentaire> commentaire = this.icommentaireService.retrieveAllCommentaires(Sort.by(Sort.Direction.DESC, new String[]{"id"}));
        List<Commentaire> commentaire = this.icommentaireService.retrieveAllCommentaires();

        model.addAttribute("commentaire", commentaire);
        return "commentaire/index";
    }

    @GetMapping({"/addCommentaire"})
    public String getaddCommentaire(Model model) {
        Commentaire commentaire = new Commentaire();
        model.addAttribute("commentaire", commentaire);
        return "commentaire/addCommentaire";
    }

    @PostMapping({"/addCommentaire"})
    public String addCommentaire(@ModelAttribute("Commentaire") @Valid Commentaire commentaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Commentaire/addCommentaire";
        } else {
            Commentaire addedCommentaire = this.icommentaireService.addCommentaire(commentaire);
            return addedCommentaire != null ? "redirect:/commentaire/retrieveAllCommentaire" : "redirect:/commentaire/addCommentaire";
        }
    }

    @GetMapping({"/updatecommentaire/{id}"})
    public String getUpdateCommentaire(@PathVariable Long id, Model model) {
        Commentaire commentaire = this.icommentaireService.retrieveCommentaire(id);
        if (commentaire == null) {
            return "redirect:/commentaire/retrieveallCommentaire";
        } else {
            model.addAttribute("commentaire", commentaire);
            return "commentaire/updateCommentaire";
        }
    }

    @PostMapping({"/updatecommentaire/{id}"})
    public String postUpdateCommentaire(@PathVariable Long id, @ModelAttribute("commentaire") @Valid Commentaire commentaire, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "commentaire/updatecommentaire";
        } else {
            Commentaire updatedCommentaire = this.icommentaireService.updateCommentaire(commentaire);
            return updatedCommentaire != null ? "redirect:/commentaire/retrieveAllCommentaire" : "redirect:/commentaire/retrieveCommentaire/" + id;
        }
    }

    @PostMapping({"/retrieveCommentaire/{id}"})
    @ResponseBody
    public Commentaire retrieveCommentaire(@PathVariable Long id) {
        return this.icommentaireService.retrieveCommentaire(id);
    }

    @GetMapping({"/deletecommentaire/{id}"})
    public String getDeleteCommentaire(@PathVariable Long id, Model model) {
        Commentaire commentaire = this.icommentaireService.retrieveCommentaire(id);
        if (commentaire == null) {
            return "redirect:/commentaire/retrieveallCommentaire";
        } else {
            model.addAttribute("commentaire", commentaire);
            return "commentaire/deleteCommentaire";
        }
    }

    @PostMapping({"/deleteCommentaire/{id}"})
    public String deleteCommentaire(@RequestParam("id") Long id) {
        this.icommentaireService.removeCommentaire(id);
        return "commentaire/Commentaire deleted successfully";
    }

    public CommentaireController(final IcommentaireService icommentaireService) {
        this.icommentaireService = icommentaireService;
    }*/
}


