package org.carrerboost.carrerboost.repository;

import org.carrerboost.carrerboost.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByDateHeureBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<RendezVous> findByLieu(String lieu);
    //List<RendezVous> findByParticipants(Participant participant);

}