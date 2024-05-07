package com.example.CareerBoost.ServiceInterface;


import com.example.CareerBoost.Entity.Formation;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

public interface IFormationService {
<<<<<<< HEAD
    List<Formation> retrieveAllFormations(Sort titre);
=======
    List<Formation> retrieveAllFormations(Sort id);
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    List<Formation> retrieveAllFormations();
    Formation addFormation(Formation formation );
    Formation updateFormation (Formation formation);
    Formation retrieveFormation ( Long id);
    void removeFormation ( Long id);
    List<Formation> searchFormationsBytitre(String titre);
    List<Formation> findBydateDebut(LocalDate dateDebut);
}
