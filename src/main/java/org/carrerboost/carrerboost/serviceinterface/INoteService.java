package org.carrerboost.carrerboost.serviceinterface;

import org.carrerboost.carrerboost.entity.Note;
import org.carrerboost.carrerboost.entity.RendezVous;

import java.time.LocalDateTime;
import java.util.List;

public interface INoteService {
    List<Note> getallnote();

    Note createNote(Note note);


    Note getNoteById(Long id);

    void updateNote(Note note);

    void deleteNoteById(Long id);
    List<Note> getNotesByCategorie(String categorie);
    List<Note> getNotesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}