package com.example.CareerBoost.ServiceImpl;


import com.example.CareerBoost.Entity.Formation;
import com.example.CareerBoost.Repository.FormationRepository;
import com.example.CareerBoost.ServiceInterface.IFormationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService {
    FormationRepository formationRepository;

    @Override
    public List<Formation> retrieveAllFormations(Sort titre) {
        return formationRepository.findAll(Sort.by(Sort.Order.asc("titre")));
    }


    @Override
    public List<Formation> retrieveAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation addFormation(@NotNull @Valid Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation updateFormation(@NotNull @Valid Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation retrieveFormation(Long id) {
        return formationRepository.findById(id).orElse(null);
    }

    @Override
    public void removeFormation(Long id) {
        formationRepository.deleteById(id);

    }
    @Override
    public List<Formation> searchFormationsBytitre(String titre) {
        return formationRepository.findFormationBytitreContainingIgnoreCase(titre);
    }

    @Override
    public List<Formation> findBydateDebut(LocalDate dateDebut) {
        return formationRepository.findBydateDebut(dateDebut);
    }

}
