package com.example.CareerBoost.Controller;


import com.example.CareerBoost.Entity.Certificat;
import com.example.CareerBoost.ServiceInterface.ICertificatService;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Tag(name = "gestion Formation")
//@Controller
@RestController
@RequestMapping("/certificat")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CertificatController {
    ICertificatService icertificat;
    IModuleFormationService imoduleFormationService;
    IFormationService iformationService;
    //postman
    @GetMapping("/retrieveallCertificat")
    public List<Certificat> retrieveAllCertificat() {
        return icertificat.retrieveAllCertificat();
    }

    @PostMapping("/addCertificat")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addCertificat(@Valid @RequestBody Certificat certificat, BindingResult result) {
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

            return ResponseEntity.badRequest().body(response);
        } else {
            // Si la validation a réussi, ajoutez le certificat normalement
            Certificat addedCertificat = icertificat.addCertificat(certificat);
            response.put("success", true);
            response.put("message", "Certificat ajouté avec succès");
            response.put("certificat", addedCertificat);
            // Génération du PDF
            generateCertificatPDF(addedCertificat);

            // Renvoyer l'URL du PDF généré
            String pdfFileName = "certificat_" + addedCertificat.getId() + ".pdf";
            String pdfUrl = "/download/" + pdfFileName; // URL pour télécharger le PDF
            response.put("pdfUrl", pdfUrl);

            return ResponseEntity.ok().body(response);
        }
    }

   /* // Méthode pour générer le PDF du certificat
    private void generateCertificatPDF(Certificat certificat) {
        try {
            String pdfFileName = "certificat_" + certificat.getId() + ".pdf";
            PdfWriter writer = new PdfWriter(pdfFileName);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Ajout des informations du certificat au PDF
            document.add(new Paragraph("Certificat ID: " + certificat.getId()));
            document.add(new Paragraph("Description: " + certificat.getDescription()));
            document.add(new Paragraph("Date de délivrance: " + certificat.getDateDelivrance()));

            // Fermeture du document PDF
            document.close();

            System.out.println("PDF généré avec succès : " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void generateCertificatPDF(Certificat certificat) {
        try {
            String pdfFileName = ResourceUtils.getFile("classpath:pdfs/certificat_" + certificat.getId() + ".pdf").getAbsolutePath();


            PdfWriter writer = new PdfWriter(pdfFileName);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Ajout des informations du certificat au PDF
            document.add(new Paragraph("Certificat ID: " + certificat.getId()));
            document.add(new Paragraph("Description: " + certificat.getDescription()));
            document.add(new Paragraph("Date de délivrance: " + certificat.getDateDelivrance()));

            // Fermeture du document PDF
            document.close();

            System.out.println("PDF généré avec succès : " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Endpoint pour télécharger le PDF généré
    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable String fileName) {
        try {
            // Chargement du fichier PDF en tant que ressource InputStream
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileName));

            // Construction des en-têtes de réponse
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            // Renvoyer la réponse avec le PDF en tant que ressource InputStream
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/updateCertificat/{id}")
    public String getUpdateCertificat(@PathVariable Long id, Model model) {
        Certificat certificat = icertificat.retrieveCertificat(id);
        if (certificat == null) {

            return "redirect:/certificat/retrieveallCertificat";
        }
        model.addAttribute("certificat", certificat);
        return "certificat/updateCertificat";
    }
    @PostMapping("/updateCertificat/{id}")
    public String postUpdateCertificat(@PathVariable Long id, @Valid @ModelAttribute("certificat") Certificat certificat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si des erreurs de validation sont détectées, renvoyer à la vue avec les erreurs
            return "certificat/updateCertificat";
        }


        Certificat updatedCertificat = icertificat.updateCertificat(certificat);
        if (updatedCertificat!= null) {
            return "redirect:/certificat/retrieveallCertificat";
        } else {
            // Gérer l'échec de la mise à jour de la formation ici, peut-être en affichant un message d'erreur à l'utilisateur
            return "redirect:/certificat/retrieveCertificat/" + id;
        }
    }

    //postman
    @PutMapping("/updateCertificat")
    @ResponseBody
    public Map<String, Object> updateCertificat(@Valid @RequestBody Certificat certificat, BindingResult result) {
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
            // Si la validation a réussi, mettez à jour le certificat normalement
            Certificat updatedCertificat = icertificat.updateCertificat(certificat);
            response.put("success", true);
            response.put("message", "Certificat mis à jour avec succès");
            response.put("certificat", updatedCertificat);
        }

        return response;
    }


    //postman
    @GetMapping("/retrieveCertificat/{id}")
    //@PostMapping("/retrieveCertificat/{id}")
    public Certificat retrieveCertificat(@PathVariable Long id) {
        return icertificat.retrieveCertificat(id);
    }


    //postman
    @DeleteMapping("/deleteCertificat/{id}")
    public ResponseEntity<String> removeCertificat(@PathVariable("id") Long id) {
        try {
            icertificat.removeCertificat(id);
            return ResponseEntity.ok("Formation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la formation");
        }
    }


    @GetMapping("/deleteCertificat/{id}")
    public String getDeleteCertificat(@PathVariable Long id, Model model) {
        Certificat certificat = icertificat.retrieveCertificat(id);
        if (certificat == null) {
            // Gérer le cas où la formation n'est pas trouvée
            return "redirect:/certificat/retrieveallCertificat";
        }
        model.addAttribute("certificat", certificat);
        return "certificat/deleteCertificat";
    }
    @PostMapping("/deleteCertificat/{id}")
    public String deleteCertificat(@RequestParam("id") Long id) {
        icertificat.removeCertificat(id);
        return "certificat/Certificat deleted successfully";
    }
    @GetMapping("/getImageForStatus/{id}")
    public ResponseEntity<?> getImageForStatus(@PathVariable Long id) {
        try {
            Certificat certificat = this.icertificat.retrieveCertificat(id);
            if (certificat != null) {
                String status = certificat.getStatut();
                String imagePath = "";
                switch (status) {
                    case "valide":
                        imagePath = "/images/tick.png"; // Chemin de l'image pour le statut "valide"
                        break;
                    case "invalide":
                        imagePath = "/images/cross.png"; // Chemin de l'image pour le statut "invalide"
                        break;
                    // Ajoutez d'autres cas selon vos besoins
                    default:
                        imagePath = "/images/default.png"; // Chemin de l'image par défaut
                        break;
                }
                return ResponseEntity.ok(imagePath);
            } else {
                // Retourner une image par défaut si le certificat n'est pas trouvé
                return ResponseEntity.ok("/images/default.png");
            }
        } catch (Exception ex) {
            String errorMessage = "Erreur: certificat non trouvé. Cause: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
