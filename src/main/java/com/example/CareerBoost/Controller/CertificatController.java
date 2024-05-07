package com.example.CareerBoost.Controller;


import com.example.CareerBoost.Entity.Certificat;
import com.example.CareerBoost.Entity.ModuleFormation;
import com.example.CareerBoost.Entity.User;
import com.example.CareerBoost.ServiceImpl.PdfService;
import com.example.CareerBoost.ServiceImpl.ServiceEmail;
import com.example.CareerBoost.ServiceInterface.ICertificatService;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    @Autowired
    ICertificatService icertificat;
    @Autowired
    IModuleFormationService imoduleFormationService;
    @Autowired
    IFormationService iformationService;
    @Autowired
    ServiceEmail serviceEmail;
    @Autowired
    PdfService pdfService;
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
           /* // Envoi d'un message de test par e-mail
            String emailBody = "Bonjour,\n\nCeci est un message de test pour vérifier le fonctionnement de l'envoi d'e-mail.\n\nCordialement,\nVotre application";

            // Remplacez 'destination@example.com' par l'adresse e-mail à laquelle vous voulez envoyer le message
            serviceEmail.sendEmail("kbaiermolka@gmail.com", "Test d'envoi d'e-mail", emailBody);*/
            // Génération du PDF
            generateCertificatPDF(addedCertificat);

            // String pdfFilePath = generateCertificatPDF(addedCertificat);

            // Envoi de l'e-mail avec le certificat en pièce jointe
            /*if (pdfFilePath != null) {
                // Modifier le corps de l'e-mail selon vos besoins
                String emailBody = "Bonjour,\n\nVeuillez trouver ci-joint votre certificat.\n\nCordialement,\nCareerBoost";

                // Envoyer l'e-mail avec le certificat en pièce jointe
                serviceEmail.sendEmailWithAttachment("kbaiermolka@gmail.com", "Certificat de réussite", emailBody, pdfFilePath);
            }*/
            // Renvoyer l'URL du PDF généré
            String pdfFileName = "certificat_" + addedCertificat.getId() + ".pdf";
            String pdfUrl = "/download/" + pdfFileName; // URL pour télécharger le PDF
            response.put("pdfUrl", pdfUrl);
// Envoi d'un e-mail avec le certificat en pièce jointe
            String pdfFilePath = "C:/Users/kbaie/IdeaProjects/CareerBoost-master - Copie/src/main/resources/pdfs/" + pdfFileName; // Assurez-vous que le chemin est correct
            String emailBody = "Bonjour,\n\nVeuillez trouver ci-joint votre certificat.\n\nCordialement,\nCareerBoost";

// Envoyer l'e-mail avec le certificat en pièce jointe
            serviceEmail.sendEmailWithAttachment("kbaiermolka@gmail.com", "Certificat de réussite", emailBody, pdfFilePath);

            return ResponseEntity.ok().body(response);
        }
    }

    @PostMapping("/addCertificatt")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addCertificatt(@Valid @RequestBody Certificat certificat, BindingResult result, @RequestParam Long moduleId) {
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
            // Récupérez le module de formation correspondant à partir de son ID
            ModuleFormation existingModuleFormation = imoduleFormationService.retrieveMFormation(moduleId);
            if (existingModuleFormation == null) {
                // Gérer le cas où le module de formation n'existe pas
                response.put("success", false);
                response.put("message", "Le module de formation spécifié n'existe pas");
                return ResponseEntity.badRequest().body(response);
            }

            // Associez le module de formation au certificat
            certificat.setModuleFormation(existingModuleFormation);

            // Ajoutez le certificat normalement
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

            // Envoi d'un e-mail avec le certificat en pièce jointe
            String pdfFilePath = "C:/Users/kbaie/IdeaProjects/CareerBoost-master - Copie/src/main/resources/pdfs/" + pdfFileName; // Assurez-vous que le chemin est correct
            String emailBody = "Bonjour,\n\nVeuillez trouver ci-joint votre certificat.\n\nCordialement,\nCareerBoost";

            // Envoyer l'e-mail avec le certificat en pièce jointe
            serviceEmail.sendEmailWithAttachment("kbaiermolka@gmail.com", "Certificat de réussite", emailBody, pdfFilePath);

            return ResponseEntity.ok().body(response);
        }
    }

    /* private void generateCertificatPDF(Certificat certificat) {
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
// Sauvegarde du PDF dans le répertoire approprié
           byte[] pdfData = Files.readAllBytes(Paths.get(pdfFileName));
           pdfService.savePdf(pdfData, pdfFileName);
            System.out.println("PDF généré avec succès : " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
   private void generateCertificatPDF(Certificat certificat) {
       try {
           // Génération du nom de fichier PDF
           String pdfFileName = "certificat_" + certificat.getId() + ".pdf";

           // Création du document PDF
           PdfWriter writer = new PdfWriter(pdfFileName);
           PdfDocument pdfDocument = new PdfDocument(writer);
           Document document = new Document(pdfDocument);
          /* // Ajout du titre
           Color myColor = Color.RED;
           Paragraph title = new Paragraph("Certificat de Réussite").setFontColor(myColor).setFontSize(24).setBold();
           document.add(title);*/

// Ajout du titre "Certificat de Réussite"
           Paragraph title = new Paragraph("Certificat ")
                   .setFontColor(Color.RED)
                   .setFontSize(24)
                   .setBold();
           // Calcul de la position horizontale du titre au centre de la page
           float pageWidth = pdfDocument.getDefaultPageSize().getWidth();
           float titleWidth = PdfFontFactory.createFont(FontConstants.HELVETICA).getWidth("Certificat");
           float titleX = (pageWidth - titleWidth) / 2;

// Ajout du titre à la position calculée
        //   title.setFixedPosition(titleX, 700, titleWidth); // Modifier les coordonnées Y selon vos besoins
           document.add(title);


// Ajout du nom de l'utilisateur au milieu du document
          /* Paragraph userName = new Paragraph(certificat.getUser().getFirstName() + " " + certificat.getUser().getLastName())
                   .setFontSize(18)
                   .setFontColor(Color.BLACK);

           document.add(new Paragraph("\n")); // Ajouter un espace vertical
           document.add(userName); // Ajouter le nom de l'utilisateur*/

// Ajout des informations du certificat avec une mise en forme spéciale
           Paragraph info = new Paragraph()
                  // .add("Nom : ").add(certificat.getUser().getFirstName() + " " + certificat.getUser().getLastName()).add("\n")
                  // .add("Module : ").add(certificat.getModuleFormation().getTitre()).add("\n")
                 // .add(new Text(certificat.getUser().getFirstName() + " " + certificat.getUser().getLastName()).setBold())
                   .add("Molka kbaier")
                   .add("\n")
                   .add(" has successfully completed a ")
                   .add(new Text("Careerboost sponsored").setItalic())
                   .add(" education program entitled: ")
                   .add(new Text(certificat.getModuleFormation().getTitre()).setBold())
                   .add("\n")
                   .add(" at the Date : ").add(certificat.getDateDelivrance().toString());
           document.add(info);
// Ajout de la reconnaissance
           Paragraph recognition = new Paragraph("And has been awarded this certificate in recognition of this effort and accomplishment in increasing professional ability");
           document.add(recognition);
// Ajout de la signature
          /* Paragraph signature = new Paragraph("Signature : ____________________").setTextAlignment(TextAlignment.RIGHT);
           document.add(signature);*/

         /*  // Ajout des informations du certificat au PDF
           document.add(new Paragraph("Certificat ID: " + certificat.getId()));
           document.add(new Paragraph("Description: " + certificat.getDescription()));
           document.add(new Paragraph("Date de délivrance: " + certificat.getDateDelivrance()));*/

           // Fermeture du document PDF
           document.close();

           // Sauvegarde du PDF dans le répertoire approprié
           byte[] pdfData = Files.readAllBytes(Paths.get(pdfFileName));
           pdfService.savePdf(pdfData, pdfFileName);

           System.out.println("PDF généré et enregistré avec succès : " + pdfFileName);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    // Endpoint pour télécharger le PDF généré
  /*  @GetMapping("/download/{fileName}")
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
*/
    /*@GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable String fileName) {
        try {
            // Chargement du fichier PDF en tant que ressource InputStream depuis le classpath
            File pdfFile = ResourceUtils.getFile("classpath:pdfs/" + fileName);
            if (!pdfFile.exists()) {
                return ResponseEntity.notFound().build();
            }

            InputStream inputStream = new FileInputStream(pdfFile);

            // Construction des en-têtes de réponse
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            // Renvoyer la réponse avec le PDF en tant que ressource InputStream
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
*/

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable String fileName) {
        try {
            // Chargement du fichier PDF en tant que ressource InputStream depuis le classpath
            Resource resource = new ClassPathResource("pdfs/" + fileName);
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            InputStream inputStream = resource.getInputStream();

            // Construction des en-têtes de réponse
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            // Renvoyer la réponse avec le PDF en tant que ressource InputStream
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //postman
  /*  @PutMapping("/updateCertificat/{id}")
    public Map<String, Object> updateCertificat(@PathVariable Long id,@Valid @RequestBody Certificat certificat, BindingResult result) {
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
*/
    @PutMapping("/updateCertificat/{id}")
    public ResponseEntity<Map<String, Object>> updateCertificat(@PathVariable Long id, @Valid @RequestBody Certificat certificat, BindingResult result) {
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
            // Si la validation a réussi, mettez à jour le certificat avec l'ID spécifié
            Certificat existingCertificat = icertificat.retrieveCertificat(id);
            if (existingCertificat == null) {
                // Gérer le cas où le certificat n'est pas trouvé
                response.put("success", false);
                response.put("message", "Certificat not found");
                return ResponseEntity.notFound().build();
            }

            // Mettre à jour les champs du certificat existant avec les valeurs fournies
            existingCertificat.setDateDelivrance(certificat.getDateDelivrance());
            existingCertificat.setDescription(certificat.getDescription());
            existingCertificat.setStatut(certificat.getStatut());

            // Mettre à jour le certificat dans la base de données
            Certificat updatedCertificat = icertificat.updateCertificat(existingCertificat);

            // Répondre avec les détails du certificat mis à jour
            response.put("success", true);
            response.put("message", "Certificat mis à jour avec succès");
            response.put("certificat", updatedCertificat);
            return ResponseEntity.ok(response);
        }
    }


    //postman
    @GetMapping("/retrieveCertificat/{id}")
    //@PostMapping("/retrieveCertificat/{id}")
    public Certificat retrieveCertificat(@PathVariable Long id) {
        return icertificat.retrieveCertificat(id);
    }


    //postman
   /* @DeleteMapping("/deleteCertificat/{id}")
    public ResponseEntity<String> removeCertificat(@PathVariable("id") Long id) {
        try {
            icertificat.removeCertificat(id);
            return ResponseEntity.ok("certificat supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression de la certificat");
        }
    }*/


    @DeleteMapping("/deleteCertificat/{id}")
    public ResponseEntity<?> removeCertificat(@PathVariable("id") Long id) {
        try {
            icertificat.removeCertificat(id);
            return ResponseEntity.ok("Certificat supprimé avec succès");
        } catch (Exception e) {
            // Si une exception se produit pendant la suppression, renvoyer un code de statut 500 (INTERNAL_SERVER_ERROR) avec le message d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression du certificat: " + e.getMessage());
        }
    }

    @GetMapping("/retrieveallCertificatWithModuleFormation")
    public List<Map<String, Object>> getallCertificatWithModuleFormation() {
        List<Certificat> allCertificats = icertificat.retrieveAllCertificat();
        List<Map<String, Object>> certificatsWithModuleFormation = new ArrayList<>();

        for (Certificat certificat : allCertificats) {
            Map<String, Object> certificatMap = new HashMap<>();
            certificatMap.put("certificat", certificat);

            ModuleFormation moduleFormation = certificat.getModuleFormation();
            if (moduleFormation != null) {
                certificatMap.put("moduleFormation", moduleFormation.getTitre());
            } else {
                certificatMap.put("moduleFormation", null); // Ou une valeur par défaut appropriée si nécessaire
            }

            certificatsWithModuleFormation.add(certificatMap);
        }

        return certificatsWithModuleFormation;
    }
    @GetMapping("/retrieveallCertificatWithUserAndModuleFormation")
    public List<Map<String, Object>> getAllCertificatWithUserAndModuleFormation() {
        List<Certificat> allCertificats = icertificat.retrieveAllCertificat();
        List<Map<String, Object>> certificatsWithUserAndModuleFormation = new ArrayList<>();

        for (Certificat certificat : allCertificats) {
            Map<String, Object> certificatMap = new HashMap<>();
            certificatMap.put("certificat", certificat);

            // Ajouter les informations sur l'utilisateur
            User user = certificat.getUser();
            if (user != null) {
                certificatMap.put("user", user.getFirstName());
            } else {
                certificatMap.put("user", null); // Ou une valeur par défaut appropriée si nécessaire
            }

            // Ajouter les informations sur le module de formation
            ModuleFormation moduleFormation = certificat.getModuleFormation();
            if (moduleFormation != null) {
                certificatMap.put("moduleFormation", moduleFormation.getTitre());
            } else {
                certificatMap.put("moduleFormation", null); // Ou une valeur par défaut appropriée si nécessaire
            }

            certificatsWithUserAndModuleFormation.add(certificatMap);
        }

        return certificatsWithUserAndModuleFormation;
    }

    @GetMapping("/allModuleFormations")
    public List<ModuleFormation> getAllModuleFormations() {
        return imoduleFormationService.retrieveAllModuleFormations();
    }
    @GetMapping("/certificat/module-count")
    public Map<String, Integer> getCertificatCountByModule() {
        return icertificat.getCertificatCountByModule();
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
