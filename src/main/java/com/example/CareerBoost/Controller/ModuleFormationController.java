package com.example.CareerBoost.Controller;


<<<<<<< HEAD
=======
import com.example.CareerBoost.Entity.Certificat;
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import com.example.CareerBoost.Entity.Formation;
import com.example.CareerBoost.Entity.ModuleFormation;
import com.example.CareerBoost.ServiceInterface.ICertificatService;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
import io.swagger.v3.oas.annotations.tags.Tag;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
=======
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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

<<<<<<< HEAD
    @GetMapping("/retrieveAllModuleFormationsSortedByTitle")
    public List<ModuleFormation> retrieveAllModuleFormations(Sort titre) {
        return imoduleFormationService.retrieveAllModuleFormations(titre);
    }

=======


    @GetMapping("/addModuleFormation")
    public String showAddModuleFormationForm(Model model) {
        ModuleFormation moduleFormation = new ModuleFormation();
        List<Formation> allFormations = iformationService.retrieveAllFormations();
        List<Certificat> allCertificats = icertificat.retrieveAllCertificat();

        model.addAttribute("moduleformation", moduleFormation); // Ajout de l'objet ModuleFormation au modèle
        model.addAttribute("allFormations", allFormations);
        model.addAttribute("allCertificats", allCertificats);

        return "moduleformation/addModuleFormation"; // Retourner le nom du fichier HTML
    }


>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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

<<<<<<< HEAD
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
=======

    @GetMapping("/updateModuleFormation/{id}")
    public String getUpdateModuleFormation(@PathVariable Long id, Model model) {
        ModuleFormation moduleformation = imoduleFormationService.retrieveMFormation(id);
        if (moduleformation == null) {
            // Gérer le cas où la formation n'est pas trouvée
            return "redirect:/moduleformation/retrieveallModuleFormation";
        }
        model.addAttribute("moduleformation", moduleformation);
        return "moduleformation/updateModuleFormation";
    }

    @PostMapping("/updateModuleFormation/{id}")
    public String postUpdateModuleFormation(@PathVariable Long id, @Valid @ModelAttribute("moduleFormation") ModuleFormation moduleFormation, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "moduleformation/updateModuleFormation";
        }

        // Mettez à jour la formation
        ModuleFormation updatedModuleFormation = imoduleFormationService.updateMFormation(moduleFormation);
        if (updatedModuleFormation != null) {
            return "redirect:/moduleformation/retrieveallModuleFormation";
        } else {
            // Gérer l'échec de la mise à jour de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/moduleformation/retrieveModuleFormation/" + id;
        }
    }


//postman
    @PutMapping("/updateModuleFormation")
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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

<<<<<<< HEAD
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



=======

    @GetMapping("/retrieveModuleFormation/{id}")
    public ModuleFormation retrieveMFormation(Long id) {
        return imoduleFormationService.retrieveMFormation(id);
    }

    @GetMapping("/deleteModuleFormation/{id}")
    public String getDeleteModuleFormation(@PathVariable Long id, Model model) {
        ModuleFormation moduleFormation = imoduleFormationService.retrieveMFormation(id);
        if (moduleFormation == null) {
            // Gérer le cas où la formation n'est pas trouvée
            return "redirect:/moduleFormation/retrieveallModuleFormation";
        }
        model.addAttribute("moduleFormation", moduleFormation); // Ajout de l'objet moduleFormation au modèle
        return "moduleformation/deleteModuleFormation";
    }

    @PostMapping("/deleteModuleFormation/{id}")
    public String deleteModuleFormation(@PathVariable Long id) {
        // Supprimer les certificats associés à ce moduleFormation
        icertificat.removeCertificat(id);

        // Ensuite, supprimer le moduleFormation
        imoduleFormationService.removeModuleFormation(id);
        // Retourner le nom du modèle de succès de suppression
        return "moduleformation/moduleformation deleted successfully";
    }
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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
<<<<<<< HEAD
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

=======
    public List<ModuleFormation> searchModuleFormationsBytitre(String titre) {
        return imoduleFormationService.searchModuleFormationsBytitre(titre);
    }
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
}
