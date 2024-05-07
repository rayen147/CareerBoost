package com.example.CareerBoost.ServiceInterface;



import com.example.CareerBoost.Entity.ModuleFormation;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15

import java.util.List;

public interface IModuleFormationService {
    List<ModuleFormation> retrieveAllModuleFormations();
<<<<<<< HEAD
    public List<ModuleFormation> retrieveAllModuleFormations(Sort titre);
=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    ModuleFormation addMFormation(ModuleFormation moduleformation );
    ModuleFormation updateMFormation (ModuleFormation mmoduleformation);
    ModuleFormation retrieveMFormation ( Long id);
    void removeModuleFormation ( Long id);
    List<ModuleFormation> searchModuleFormationsBytitre(String titre);
}
