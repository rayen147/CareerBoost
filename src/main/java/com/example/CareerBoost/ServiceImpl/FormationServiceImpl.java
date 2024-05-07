package com.example.CareerBoost.ServiceImpl;


import com.example.CareerBoost.Entity.Formation;
import com.example.CareerBoost.Repository.FormationRepository;
import com.example.CareerBoost.ServiceInterface.IFormationService;
<<<<<<< HEAD
=======
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService {
    FormationRepository formationRepository;

    @Override
<<<<<<< HEAD
    public List<Formation> retrieveAllFormations(Sort titre) {
        return formationRepository.findAll(Sort.by(Sort.Order.asc("titre")));
    }


=======
    public List<Formation> retrieveAllFormations(Sort id) {
        return formationRepository.findAll();
    }
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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
