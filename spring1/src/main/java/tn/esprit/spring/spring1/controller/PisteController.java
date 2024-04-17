package tn.esprit.spring.spring1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.spring1.Entity.Piste;
import tn.esprit.spring.spring1.serviceInterface.IPisteService;

import java.util.List;
@Tag(name="Gestion des pistes")
@RequestMapping("/piste")
@RestController
@AllArgsConstructor
public class PisteController {
    IPisteService Pistecontroller;

    @GetMapping("/AllPiste")
    public List<Piste> retrieveAllPistes() {
        return Pistecontroller.retrieveAllPistes();
    }
    @PostMapping("/addPiste")
    public Piste addPiste(@RequestBody Piste piste) {
        return Pistecontroller.addPiste(piste);
    }
    @PutMapping("/updatePiste")
    public Piste updatePiste(@RequestBody Piste piste) {
        return Pistecontroller.updatePiste(piste);
    }
    @GetMapping("/getById/{numPiste}")
    public Piste retrievePiste(@PathVariable ("numPiste") long numPiste) {
        return Pistecontroller.retrievePiste(numPiste);
    }
}
