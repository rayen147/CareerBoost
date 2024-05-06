package com.example.CareerBoost.ServiceInterface;



import com.example.CareerBoost.Entity.ModuleFormation;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IModuleFormationService {
    List<ModuleFormation> retrieveAllModuleFormations();
    public List<ModuleFormation> retrieveAllModuleFormations(Sort titre);
    ModuleFormation addMFormation(ModuleFormation moduleformation );
    ModuleFormation updateMFormation (ModuleFormation mmoduleformation);
    ModuleFormation retrieveMFormation ( Long id);
    void removeModuleFormation ( Long id);
    List<ModuleFormation> searchModuleFormationsBytitre(String titre);
}
