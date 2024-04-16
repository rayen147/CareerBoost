package com.example.CareerBoost.Repository;


import com.example.CareerBoost.Entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> findFormationBytitreContainingIgnoreCase(String titre);
    List<Formation> findBydateDebut(LocalDate dateDebut);
}
