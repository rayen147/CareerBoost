package com.example.techcloudpi.Controller;

import com.example.techcloudpi.ServiceIMPL.EmailServiceImp;
import com.example.techcloudpi.ServiceIMPL.SmsService;
import com.example.techcloudpi.ServiceInterfaces.Ireclamation;
import com.example.techcloudpi.entity.EmailRequest;
import com.example.techcloudpi.entity.Reclamation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.techcloudpi.ServiceIMPL.SmsService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/reclamations")
@CrossOrigin("*")
public class ReclamationController {
Ireclamation ireclamation;

    @Autowired
    private EmailServiceImp emailService;


    @GetMapping("/Getreclamation")
    public List<Reclamation> retrieveAllreclamations() {
        return ireclamation.retrieveAllreclamations();
    }
    @PostMapping("/Addreclamation/{idUser}")
    public Reclamation addReclamation(@RequestBody  Reclamation reclamation, @PathVariable ("idUser") Long idUser) {
        return ireclamation.addReclamation(reclamation,idUser);
    }

    @PutMapping("/Updatereclamation/{idrec}")

    public Reclamation updateReclamation(@PathVariable Long idrec , @RequestBody Reclamation updatedReclamation) {
        return ireclamation.updateReclamation(idrec, updatedReclamation);
    }


    @GetMapping("/Retrievereclamation/{idrec}")
    public Reclamation retrieveReclamation(@PathVariable Long idrec) {
        return ireclamation.retrieveReclamation(idrec);
    }

    @DeleteMapping("/deletereclamation/{idrec}")
    public ResponseEntity<Reclamation> deleteReclamation(@PathVariable long idrec){
        Reclamation e =ireclamation.retrieveReclamation(idrec);
        if (e==null)
            return ResponseEntity.notFound().build();
        ireclamation.removeReclamation(idrec);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendMail(emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getBody());
    }
    @GetMapping("/reclamationsUtilisateur/{idUser}")
    public List<Reclamation> getReclamationsUtilisateur(@PathVariable Long idUser) {
        return ireclamation.getReclamationsUtilisateur(idUser);
    }
}
