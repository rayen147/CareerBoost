package com.example.CareerBoost.ServiceInterface;



import com.example.CareerBoost.Entity.ModuleFormation;

import java.util.List;

public interface IModuleFormationService {
    List<ModuleFormation> retrieveAllModuleFormations();
    ModuleFormation addMFormation(ModuleFormation moduleformation );
    ModuleFormation updateMFormation (ModuleFormation mmoduleformation);
    ModuleFormation retrieveMFormation ( Long id);
    void removeModuleFormation ( Long id);
    List<ModuleFormation> searchModuleFormationsBytitre(String titre);
}
