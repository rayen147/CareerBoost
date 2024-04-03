package org.carrerboost.carrerboost.serviceimpl;

import lombok.AllArgsConstructor;
import org.carrerboost.carrerboost.entity.Gestionidee;
import org.carrerboost.carrerboost.entity.Note;
import org.carrerboost.carrerboost.repository.GestionideeRepository;
import org.carrerboost.carrerboost.serviceinterface.IGestionideeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GestionideeserviceImpl implements IGestionideeService {
    GestionideeRepository gestionideeRepository;
    public List<Gestionidee> searchIdeas(String keyword) {
        // Recherche par titre
        List<Gestionidee> ideasByTitle = gestionideeRepository.findByTitleContainingIgnoreCase(keyword);

        // Recherche par description
        List<Gestionidee> ideasByDescription = gestionideeRepository.findByDescriptionContainingIgnoreCase(keyword);

        // Combinez les résultats si nécessaire
        List<Gestionidee> combinedResults = new ArrayList<>();
        combinedResults.addAll(ideasByTitle);
        combinedResults.addAll(ideasByDescription);

        return combinedResults;
    }

    public List<Gestionidee> findByDescriptionContainingIgnoreCase(String motcle) {
        return gestionideeRepository.findByDescriptionContainingIgnoreCase(motcle);
    }

    public List<Gestionidee> findByTitleContainingIgnoreCase(String motcle) {
        return gestionideeRepository.findByTitleContainingIgnoreCase(motcle);
    }
    public  List<Gestionidee> getallidee(){
        return gestionideeRepository.findAll();
    };
    public Gestionidee createIdee(Gestionidee gestionidee) {

        return gestionideeRepository.save(gestionidee);
    }

    public Gestionidee getideeById(Long id) {

        return gestionideeRepository.findById(id).orElse(null);
    }

    public void updateIdee(Gestionidee gestionidee) {

        gestionideeRepository.save(gestionidee);
    }

    public void deleteideeById(Long id) {
        // Logique pour supprimer une note par son identifiant
        gestionideeRepository.deleteById(id);
    }
}
