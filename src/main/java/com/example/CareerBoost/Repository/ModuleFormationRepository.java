package com.example.CareerBoost.Repository;


import com.example.CareerBoost.Entity.ModuleFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleFormationRepository extends JpaRepository<ModuleFormation,Long> {
    List<ModuleFormation> findModuleFormationBytitreContainingIgnoreCase(String titre);
}
