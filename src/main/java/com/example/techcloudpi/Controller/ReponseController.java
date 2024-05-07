package com.example.techcloudpi.Controller;

import com.example.techcloudpi.ServiceInterfaces.Ireponse;
import com.example.techcloudpi.entity.Reponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ReponseController {
    Ireponse ireponse;

    @GetMapping("/Getreponse")
    public List<Reponse> retrieveAllreponses() {

        return ireponse.retrieveAllreponses();
    }

    @PostMapping("/Addreponse/{idrec}")
    public Reponse addReponse(@RequestBody Reponse reponse, @PathVariable ("idrec")Long idrec) {
        return ireponse.addReponse(reponse,idrec);
    }
    @PutMapping("/Updatereponse")

    public Reponse updateReponse(Reponse reponse) {
        return ireponse.updateReponse(reponse);
    }
    @GetMapping("/Retrievereponse/{idrec}")
    public Reponse retrieveReponse(Long idrep) {
        return ireponse.retrieveReponse(idrep);
    }

    public void removeReponse(Long idrep) {
        ireponse.removeReponse(idrep);
    }
}
