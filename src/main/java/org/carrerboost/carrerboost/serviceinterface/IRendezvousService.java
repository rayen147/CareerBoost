package org.carrerboost.carrerboost.serviceinterface;

import org.carrerboost.carrerboost.entity.RendezVous;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface IRendezvousService {
    List<RendezVous> getallrendezvous();
    RendezVous createRendezvous(RendezVous rendezvous);
    RendezVous getRendezvousById(Long id);
    void updateRendezvous(RendezVous rendezvous);
    void deleteRendezvousById(Long id);
    List<RendezVous> findByDateHeureBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<RendezVous> findByLieu(String lieu);
}
