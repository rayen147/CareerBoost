package org.carrerboost.carrerboost.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.carrerboost.carrerboost.entity.Note;
import org.carrerboost.carrerboost.serviceinterface.INoteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "gestion note")
@RestController
@RequestMapping("/note")
@AllArgsConstructor

public class NoteController {
   INoteService inoteService;

    @PostMapping("/createnote")
    public Note createNote(Note note) {
            return inoteService.createNote(note);
        }
    @GetMapping("/getnote/{id}")
    public Note getNoteById(@PathVariable  ("id")Long id) {

        return inoteService.getNoteById(id);


    }
    @GetMapping("/retrieveallNote")
    public List<Note> getallNote() {
        return inoteService.getallnote();
    }
    @PutMapping("/updateNote")
    public void updateNote(Note note) {
        inoteService.updateNote(note);
    }
@DeleteMapping("/deleteNote/{id}")
    public void deleteNoteById(@PathVariable  ("numCours")Long id) {
         inoteService.deleteNoteById(id);
    }
    @GetMapping("/by-category/{categorie}")
    public ResponseEntity<List<Note>> getNotesByCategory(@PathVariable("categorie") String category) {
        List<Note> notes = inoteService.getNotesByCategorie(category);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<Note>> getNotesByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Note> notes = inoteService.getNotesByDateRange(startDate, endDate);
        return ResponseEntity.ok(notes);
    }
}