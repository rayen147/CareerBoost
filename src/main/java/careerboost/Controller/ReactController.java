package careerboost.Controller;

import careerboost.Entity.React;
import careerboost.Entity.TypeReact;
import careerboost.ServiceInterface.IreactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "gestion Forum")
@RestController
//@Controller
@RequestMapping("/react")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ReactController {
    IreactService ireactService;


    @GetMapping("/retrieveAllReacts")
    public List<React> retrieveAllReacts() {
        return ireactService.retrieveAllReacts();
    }
   /* @PostMapping("/addReact")
    public React addReact(React react) {
        return ireactService.addReact(react);
    }*/
  /* @PostMapping("/addReact")
   public ResponseEntity<Map<String, Object>> addReact(@Valid @RequestBody React react, BindingResult result) {
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

           React addedReact = ireactService.addReact(react);
           response.put("success", true);
           response.put("message", "Réaction ajoutée avec succès");
           response.put("react", addedReact);
           return ResponseEntity.status(HttpStatus.OK).body(response);
       }
   }*/
   @PostMapping("/addReact")
   public ResponseEntity<Map<String, Object>> addReact(@Valid @RequestBody React react, BindingResult result) {
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
           // Convertir la chaîne JSON en TypeReact
           String typeReactString = react.getTypeReact().name(); // Utilisation de .name() pour obtenir le nom de l'énumération
           TypeReact typeReact = React.getTypeReactFromString(typeReactString);
           // Attribuer la valeur d'énumération à l'objet React
           react.setTypeReact(typeReact);

           React addedReact = ireactService.addReact(react);
           response.put("success", true);
           response.put("message", "Réaction ajoutée avec succès");
           response.put("react", addedReact);
           return ResponseEntity.status(HttpStatus.OK).body(response);
       }
   }

    /*@PutMapping("/updateReact")
    public ResponseEntity<Map<String, Object>> updateReact(@Valid @RequestBody React react, BindingResult result) {
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
            React updatedReact = ireactService.updateReact(react);
            response.put("success", true);
            response.put("message", "Réaction mise à jour avec succès");
            response.put("react", updatedReact);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }*/
    @PutMapping("/updateReact/{id}")
    public ResponseEntity<Map<String, Object>> updateReact(@PathVariable Long id, @Valid @RequestBody  React react, BindingResult result) {
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
            React existingReact = ireactService.retrieveReact(id);
            if (existingReact == null) {
                response.put("success", false);
                response.put("message", "React not found");

                return ResponseEntity.notFound().build();
            }


            // Mettre à jour les champs du certificat existant avec les valeurs fournies
            existingReact.setDate(react.getDate());



            // Mettre à jour le certificat dans la base de données
            React updatedReact = ireactService.updateReact(existingReact);

            // Répondre avec les détails du certificat mis à jour
            response.put("success", true);
            response.put("message", "React mis à jour avec succès");
            response.put("React", updatedReact);
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/retrieveReact/{id}")
    public React retrieveReact(@PathVariable Long id) {
        if (id == null) {
            // Gérer le cas où l'ID est null, peut-être renvoyer une exception ou un résultat vide
            return null;
        } else {
            return ireactService.retrieveReact(id);
        }
    }


    /*@GetMapping("/retrieveReact/{id}")
    public React retrieveReact(Long id) {
        return ireactService.retrieveReact(id);
    }*/
    @DeleteMapping("/removeReact/{id}")
    public ResponseEntity<String> removeReact(@PathVariable("id") Long id) {
        try {
            ireactService.removeReact(id);
            return ResponseEntity.ok("Réaction supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la réaction");
        }
    }

    @GetMapping("/retrieveReactsForPost/{postId}")
    public List<React> retrieveReactsForPost(@PathVariable Long postId) {
        return ireactService.retrieveReactsForPost(postId);
    }
/*
    @PostMapping("/add-react/{postId}")
    public ResponseEntity<Map<String, Object>> addReactToPost(@PathVariable Long postId, @Valid @RequestBody React react, BindingResult result) {
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
            react.setPost(post);
            React addedReact = ireactService.addReact(react);
            response.put("success", true);
            response.put("message", "Reaction ajouté avec succès");
            response.put("react", addedReact);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }*/


    /* @GetMapping({"/retrieveallReact"})
    public String retrieveAllReacts(Model model) {
        List<React> react = this.ireactService.retrieveAllReacts();
        model.addAttribute("react", react);
        return "react/index";
    }

    @GetMapping({"/addReact"})
    public String getaddReact(Model model) {
        React reacts = new React();
        model.addAttribute("react", reacts);
        return "react/addReact";
    }

    @PostMapping({"/addReact"})
    public String addReact(@ModelAttribute("react") @Valid React react, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "react/addReact";
        } else {
            React addedReact = this.ireactService.addReact(react);
            return addedReact != null ? "redirect:/react/retrieveallReact" : "redirect:/react/addReact";
        }
    }

    @GetMapping({"/updateReact/{id}"})
    public String getUpdateReact(@PathVariable Long id, Model model) {
        React react = this.ireactService.retrieveReact(id);
        if (react == null) {
            return "redirect:/react/retrieveallReact";
        } else {
            model.addAttribute("react", react);
            return "react/updateReact";
        }
    }

    @PostMapping({"/updateReact/{id}"})
    public String postUpdateReact(@PathVariable Long id, @ModelAttribute("react") @Valid React react, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "react/updateReact";
        } else {
            React updatedReact = this.ireactService.updateReact(react);
            return updatedReact != null ? "redirect:/react/retrieveallReact" : "redirect:/react/retrieveReact/" + id;
        }
    }

    @PostMapping({"/retrieveReact/{id}"})
    public React retrieveReact(@PathVariable Long id) {
        return this.ireactService.retrieveReact(id);
    }

    @GetMapping({"/deleteReact/{id}"})
    public String getDeleteReact(@PathVariable Long id, Model model) {
        React react = this.ireactService.retrieveReact(id);
        if (react == null) {
            return "redirect:/react/retrieveallReact";
        } else {
            model.addAttribute("react", react);
            return "react/deleteReact";
        }
    }

    @PostMapping({"/deleteReact/{id}"})
    public String deleteReact(@RequestParam("id") Long id) {
        this.ireactService.removeReact(id);
        return "react/React deleted successfully";
    }
/*
    @GetMapping({"/getImageForStatus/{id}"})
    public ResponseEntity<?> getImageForStatus(@PathVariable Long id) {
        String status;
        try {
            React react = this.ireactService.retrieveReact(id);
            if (react != null) {
                status = react.getStatut();
                String imagePath = "";
                switch (status) {
                    case "valide":
                        imagePath = "/images/tick.png";
                        break;
                    case "invalide":
                        imagePath = "/images/cross.png";
                        break;
                    default:
                        imagePath = "/images/default.png";
                }

                return ResponseEntity.ok(imagePath);
            } else {
                return ResponseEntity.ok("/images/default.png");
            }
        } catch (Exception var7) {
            status = "Erreur: react non trouvé. Cause: " + var7.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    public ReactController(final IreactService ireactService) {
        this.ireactService = ireactService;
    }*/
}
