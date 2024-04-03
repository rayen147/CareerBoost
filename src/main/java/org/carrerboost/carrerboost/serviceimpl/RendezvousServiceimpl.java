package org.carrerboost.carrerboost.serviceimpl;

import lombok.AllArgsConstructor;
import org.carrerboost.carrerboost.entity.RendezVous;
import org.carrerboost.carrerboost.repository.RendezVousRepository;
import org.carrerboost.carrerboost.serviceinterface.IRendezvousService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class RendezvousServiceimpl  implements IRendezvousService {
    RendezVousRepository rendezVousRepository;
    public List<RendezVous> getallrendezvous() {
        return rendezVousRepository.findAll();
    }
    public List<RendezVous> findByDateHeureBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return rendezVousRepository.findByDateHeureBetween(startDateTime, endDateTime);
    }

    public List<RendezVous> findByLieu(String lieu) {
        return rendezVousRepository.findByLieu(lieu);
    }

    @Override
    public RendezVous createRendezvous(RendezVous rendezvous) {
        return rendezVousRepository.save(rendezvous);
    }

    @Override
    public RendezVous getRendezvousById(Long id) {
        return rendezVousRepository.getById(id);
    }

    @Override
    public void updateRendezvous(RendezVous rendezvous) {
rendezVousRepository.save(rendezvous);
    }

    @Override
    public void deleteRendezvousById(Long id) {
rendezVousRepository.deleteById(id);
    }
}
