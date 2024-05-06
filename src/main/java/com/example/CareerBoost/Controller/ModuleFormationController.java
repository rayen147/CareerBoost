package com.example.CareerBoost.Controller;


import com.example.CareerBoost.Entity.Formation;
import com.example.CareerBoost.Entity.ModuleFormation;
import com.example.CareerBoost.ServiceInterface.ICertificatService;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "gestion ModuleFormation")
//@Controller
@RestController
@RequestMapping("/moduleformation")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ModuleFormationController {
    IModuleFormationService imoduleFormationService;
    ICertificatService icertificat;
    IFormationService iformationService;
    @CrossOrigin(origins = "http://localhost:4200")
    //postman
    @GetMapping("/retrieveallModuleFormation")
    public List<ModuleFormation> retrieveAllModuleFormations() {
        return imoduleFormationService.retrieveAllModuleFormations();
    }

    @GetMapping("/retrieveAllModuleFormationsSortedByTitle")
    public List<ModuleFormation> retrieveAllModuleFormations(Sort titre) {
        return imoduleFormationService.retrieveAllModuleFormations(titre);
    }

    @PostMapping("/addModuleFormation")
    @ResponseBody
    public Map<String, Object> addMFormation(@Valid @RequestBody ModuleFormation mformation, BindingResult result) {
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
            // Si la validation a réussi, ajoutez le module de formation normalement
            ModuleFormation addedModuleFormation = imoduleFormationService.addMFormation(mformation);
            response.put("success", true);
            response.put("message", "Module de formation ajouté avec succès");
            response.put("moduleFormation", addedModuleFormation);
        }

        return response;
    }

    @PostMapping("/addModuleFormationn")
    @ResponseBody
    public Map<String, Object> addMFormationn(@Valid @RequestBody ModuleFormation mformation, BindingResult result, @RequestParam Long formationId) {
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
            // Récupérez la formation correspondant à partir de son ID
            Formation existingFormation = iformationService.retrieveFormation(formationId);
            if (existingFormation == null) {
                // Gérer le cas où la formation n'existe pas
                response.put("success", false);
                response.put("message", "La formation spécifiée n'existe pas");
                return response;
            }

            // Associez la formation au module de formation
            mformation.setFormation(existingFormation);

            // Si la validation a réussi et que la formation est liée, ajoutez le module de formation normalement
            ModuleFormation addedModuleFormation = imoduleFormationService.addMFormation(mformation);
            response.put("success", true);
            response.put("message", "Module de formation ajouté avec succès");
            response.put("moduleFormation", addedModuleFormation);
        }

        return response;
    }






//postman
 /*   @PutMapping("/updateModuleFormation")
    @ResponseBody
    public Map<String, Object> updateMFormation(@Valid @RequestBody ModuleFormation mformation, BindingResult result) {
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
            // Si la validation a réussi, mettez à jour le module de formation normalement
            ModuleFormation updatedModuleFormation = imoduleFormationService.updateMFormation(mformation);
            response.put("success", true);
            response.put("message", "Module de formation mis à jour avec succès");
            response.put("moduleFormation", updatedModuleFormation);
        }

        return response;
    }

*/
@PutMapping("/updateModuleFormation/{id}")
public ResponseEntity<Map<String, Object>> updateMFormation(@PathVariable Long id, @Valid @RequestBody ModuleFormation mformation, BindingResult result) {
    Map<String, Object> response = new HashMap<>();

    if (result.hasErrors()) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }


        response.put("success", false);
        response.put("message", "Validation failed");
        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    } else {
        // Si la validation a réussi, mettez à jour le certificat avec l'ID spécifié
        ModuleFormation existingModuleFormation = imoduleFormationService.retrieveMFormation(id);
        if (existingModuleFormation == null) {
            // Gérer le cas où le certificat n'est pas trouvé
            response.put("success", false);
            response.put("message", "ModuleFormation not found");
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour les champs du certificat existant avec les valeurs fournies
        existingModuleFormation.setTitre(mformation.getTitre());
        existingModuleFormation.setDescription(mformation.getDescription());


        ModuleFormation updatedModuleFormation = imoduleFormationService.updateMFormation(mformation);
        response.put("success", true);
        response.put("message", "Formation mise à jour avec succès");
        response.put("formation", updatedModuleFormation);
        return ResponseEntity.ok(response);
    }
}



    @GetMapping("/retrieveModuleFormation/{id}")
    public ModuleFormation retrieveMFormation(@PathVariable Long id) {
        return imoduleFormationService.retrieveMFormation(id);
    }



    @DeleteMapping("/deleteModuleFormation/{id}")
    public ResponseEntity<String> removeModuleFormation(@PathVariable("id") Long id) {
        try {
            imoduleFormationService.removeModuleFormation(id);
            return ResponseEntity.ok("Formation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la formation");
        }
    }


    @GetMapping("/searchModuleFormationsBytitre/{titre}")
    public List<ModuleFormation> searchModuleFormationsBytitre(@PathVariable String titre) {
        return imoduleFormationService.searchModuleFormationsBytitre(titre);
    }

   /* @GetMapping("/retrieveallModuleFormationWithFormation")
    public List<Map<String, Object>> getAllModuleFormationsWithFormation() {
        List<ModuleFormation> allModuleFormations = imoduleFormationService.retrieveAllModuleFormations();
        List<Map<String, Object>> moduleFormationsWithFormation = new ArrayList<>();

        for (ModuleFormation moduleFormation : allModuleFormations) {
            Map<String, Object> moduleFormationMap = new HashMap<>();
            moduleFormationMap.put("moduleFormation", moduleFormation);
            moduleFormationMap.put("formation", moduleFormation.getFormation());
            moduleFormationsWithFormation.add(moduleFormationMap);
        }

        return moduleFormationsWithFormation;
    }*/
   @GetMapping("/retrieveallModuleFormationWithFormation")
   public List<Map<String, Object>> getAllModuleFormationsWithFormation(Sort titre) {
       List<ModuleFormation> allModuleFormations = imoduleFormationService.retrieveAllModuleFormations(titre);
       List<Map<String, Object>> moduleFormationsWithFormation = new ArrayList<>();

       for (ModuleFormation moduleFormation : allModuleFormations) {
           Map<String, Object> moduleFormationMap = new HashMap<>();
           moduleFormationMap.put("moduleFormation", moduleFormation);

           Formation formation = moduleFormation.getFormation();
           if (formation != null) {
               moduleFormationMap.put("formation", formation.getTitre());
           } else {
               moduleFormationMap.put("formation", null); // Ou une valeur par défaut appropriée si nécessaire
           }

           moduleFormationsWithFormation.add(moduleFormationMap);
       }

       return moduleFormationsWithFormation;
   }

}
