package com.example.CareerBoost.Controller;


import com.example.CareerBoost.Entity.Formation;
import com.example.CareerBoost.Entity.ModuleFormation;
import com.example.CareerBoost.Entity.User;
import com.example.CareerBoost.ServiceInterface.ICertificatService;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "gestion Formation")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@Controller
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationController {
    IFormationService iformationService;
    IModuleFormationService imoduleFormationService;
    ICertificatService icertificat;




    //postman
    @GetMapping("/retrieveallFormation")
    public List<Formation> retrieveAllFormations() {
        return iformationService.retrieveAllFormations();
    }
    @GetMapping("/retrieveAllFormationsSortedByTitle")
    public List<Formation> retrieveAllFormationsSortedByTitle() {
        return iformationService.retrieveAllFormations(Sort.by("titre"));
    }

    //postman
    @PostMapping("/addFormation")
    @ResponseBody
    public Map<String, Object> addFormation(@Valid @RequestBody Formation formation, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            // Construction de la liste des erreurs de validation
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            // Ajout des erreurs à la réponse
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
        } else {
            // Si la validation a réussi, ajoutez la formation normalement
            Formation addedFormation = iformationService.addFormation(formation);
            response.put("success", true);
            response.put("message", "Formation ajoutée avec succès");
            response.put("formation", addedFormation);
        }

        return response;
    }

    @PutMapping("/updateFormation/{id}")
    public Map<String, Object> updateFormation(@PathVariable Long id, @Valid @RequestBody Formation formation, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            // Construction de la liste des erreurs de validation
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            // Ajout des erreurs à la réponse
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", errors);
        } else {
            // Si la validation a réussi, mettez à jour la formation normalement
            Formation updatedFormation = iformationService.updateFormation(formation);
            response.put("success", true);
            response.put("message", "Formation mise à jour avec succès");
            response.put("formation", updatedFormation);
        }

        return response;
    }


    @GetMapping("/retrieveFormation/{id}")
    @ResponseBody
    public Formation retrieveFormation(@PathVariable Long id) {
        return iformationService.retrieveFormation(id);
    }

    @DeleteMapping("/deleteFormation/{id}")
    public ResponseEntity<?> removeFormation(@PathVariable("id") Long id) {
        try {
            iformationService.removeFormation(id);
            return ResponseEntity.ok().build(); // Renvoyer une réponse 200 OK sans corps de réponse
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la formation");
        }
    }


    @GetMapping("/searchFormationsByTitle/{titre}")
    public List<Formation> searchFormationsBytitre(@PathVariable String titre) {
        return iformationService.searchFormationsBytitre(titre);
    }

    @GetMapping("/findByDateDebut/{dateDebut}")

    public List<Formation> findBydateDebutFormation(@PathVariable("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut) {
        return iformationService.findBydateDebut(dateDebut);
    }

    @GetMapping("/formation/{formationId}/modules")
    public List<ModuleFormation> getModulesByFormationId(@PathVariable Long formationId) {
        Formation formation = iformationService.retrieveFormation(formationId);
        if (formation != null) {
            return formation.getModules();
        } else {
            // Gérer le cas où la formation n'est pas trouvée
            return Collections.emptyList();
        }
    }
   /*@PostMapping("/participate/{formationId}")
    public ResponseEntity<?> participateFormation(@PathVariable Long formationId, @RequestBody User participant) {
        try {
            Formation formation = iformationService.retrieveFormation(formationId);
            if (formation != null) {
                // Vérifiez si le nombre de participants est inférieur à la limite
                if (formation.getParticipants().size() < formation.getLimiteParticipants()) {
                    // Ajouter le participant à la formation
                    formation.addParticipant(participant);
                    // Mettez à jour la formation
                    Formation updatedFormation = iformationService.updateFormation(formation);
                    return ResponseEntity.ok(updatedFormation); // Renvoyer la formation mise à jour
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La formation est pleine. Vous ne pouvez pas participer pour le moment.");
                }
            } else {
                return ResponseEntity.notFound().build(); // Renvoyer une réponse 404 si la formation n'est pas trouvée
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la participation à la formation");
        }
    }

*/
   @PostMapping("/participate/{formationId}")
   public ResponseEntity<?> participateFormation(@PathVariable Long formationId, @RequestBody Map<String, String> participantDetails) {
       try {
           Formation formation = iformationService.retrieveFormation(formationId);
           if (formation != null) {
               // Vérifiez si le nombre de participants est inférieur à la limite
               if (formation.getParticipants().size() < formation.getLimiteParticipants()) {
                   // Créez un nouvel utilisateur à partir des détails du participant envoyés dans le corps de la requête
                   User participant = new User();
                   participant.setFirstName(participantDetails.get("nom"));
                   participant.setLastName(participantDetails.get("prenom"));
                   participant.setEmail(participantDetails.get("email"));

                   // Ajouter le participant à la formation
                   formation.addParticipant(participant);
                   // Mettez à jour la formation
                   Formation updatedFormation = iformationService.updateFormation(formation);
                   return ResponseEntity.ok(updatedFormation); // Renvoyer la formation mise à jour
               } else {
                   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La formation est pleine. Vous ne pouvez pas participer pour le moment.");
               }
           } else {
               return ResponseEntity.notFound().build(); // Renvoyer une réponse 404 si la formation n'est pas trouvée
           }
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la participation à la formation");
       }
   }

}
