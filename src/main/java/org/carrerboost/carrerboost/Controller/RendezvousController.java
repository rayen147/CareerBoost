package org.carrerboost.carrerboost.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.carrerboost.carrerboost.entity.Note;
import org.carrerboost.carrerboost.entity.RendezVous;
import org.carrerboost.carrerboost.serviceinterface.IRendezvousService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "gestion rendezvous")
@RestController
@RequestMapping("/rendezvous")
@AllArgsConstructor
public class RendezvousController {

    IRendezvousService irendezvousserice;
    @GetMapping("/retrieveallNote")
    public List<RendezVous> getallrendezvous() {
        return irendezvousserice.getallrendezvous();}
    @GetMapping("/getlistrendezvousdateheure/{startDateTime}/{endDateTime}")
    public List<RendezVous> findByDateHeureBetween(@PathVariable  ("startDateTime")LocalDateTime startDateTime, @PathVariable("endDateTime") LocalDateTime endDateTime) {
        return irendezvousserice.findByDateHeureBetween(startDateTime, endDateTime);
    }
@GetMapping("/getlisterendezvouslieu/{lieu}")
    public List<RendezVous> findByLieu(@PathVariable  ("lieu")String lieu) {
        return irendezvousserice.findByLieu(lieu);
    }


    @PostMapping("/creerrendezvous")
    public RendezVous createRendezvous(RendezVous rendezvous) {
        return irendezvousserice.createRendezvous(rendezvous);
    }

    @GetMapping("/getrendezvous/{id}")
    public RendezVous getRendezvousById(@PathVariable("id") Long id) {
        return irendezvousserice.getRendezvousById(id);
    }

    @PutMapping("/updaterendzvous")
    public void updateRendezvous(RendezVous rendezvous) {
        irendezvousserice.updateRendezvous(rendezvous);
    }

    @DeleteMapping("/deleterendezvous/{id}")
    public void deleteRendezvousById(@PathVariable  ("id")Long id) {
        irendezvousserice.deleteRendezvousById(id);
    }

}
