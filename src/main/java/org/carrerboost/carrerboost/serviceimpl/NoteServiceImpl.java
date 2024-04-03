package org.carrerboost.carrerboost.serviceimpl;

import lombok.AllArgsConstructor;
import org.carrerboost.carrerboost.entity.Note;
import org.carrerboost.carrerboost.repository.NoteRepository;
import org.carrerboost.carrerboost.serviceinterface.INoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements INoteService {
NoteRepository noteRepository;
   public  List<Note> getallnote(){
        return noteRepository.findAll();
    };
    public Note createNote(Note note) {
        // Logique pour créer une nouvelle note
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        // Logique pour récupérer une note par son identifiant
        return noteRepository.findById(id).orElse(null);
    }

    public void updateNote(Note note) {
        // Logique pour mettre à jour une note existante
        noteRepository.save(note);
    }

    public void deleteNoteById(Long id) {
        // Logique pour supprimer une note par son identifiant
        noteRepository.deleteById(id);
    }
    //par categorie
    public List<Note> getNotesByCategorie(String categorie) {
        return noteRepository.findByCategorie(categorie);
    }
//par date specfifique
    public List<Note> getNotesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return noteRepository.findByDateCreationBetween(startDate, endDate);
    }

}
