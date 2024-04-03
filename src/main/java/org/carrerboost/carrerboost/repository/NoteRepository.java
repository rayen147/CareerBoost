package org.carrerboost.carrerboost.repository;
import org.carrerboost.carrerboost.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCategorie(String categorie);
    List<Note> findByDateCreationBetween(LocalDateTime startDate, LocalDateTime endDate);
}