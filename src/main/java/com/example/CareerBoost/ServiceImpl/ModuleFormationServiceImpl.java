package com.example.CareerBoost.ServiceImpl;


import com.example.CareerBoost.Entity.ModuleFormation;
import com.example.CareerBoost.Repository.ModuleFormationRepository;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
=======
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import java.util.List;

@Service
@AllArgsConstructor
public class ModuleFormationServiceImpl implements IModuleFormationService {
    ModuleFormationRepository moduleFormationRepository;

    @Override
    public List<ModuleFormation> retrieveAllModuleFormations() {
        return moduleFormationRepository.findAll();
    }

<<<<<<< HEAD
    @Override
    public List<ModuleFormation> retrieveAllModuleFormations(Sort titre) {
        return moduleFormationRepository.findAll(Sort.by(Sort.Order.asc("titre")));
    }
=======

>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    @Override
    public ModuleFormation addMFormation(@NotNull @Valid ModuleFormation moduleformation) {
        return moduleFormationRepository.save(moduleformation);
    }

    @Override
    public ModuleFormation updateMFormation(@NotNull @Valid ModuleFormation moduleformation) {
        return moduleFormationRepository.save(moduleformation);
    }

    @Override
    public ModuleFormation retrieveMFormation(Long id) {
        return moduleFormationRepository.findById(id).orElse(null);
    }

    @Override
    public void removeModuleFormation(Long id) {
        moduleFormationRepository.deleteById(id);

    }
    @Override
    public List<ModuleFormation> searchModuleFormationsBytitre(String titre) {
        return moduleFormationRepository.findModuleFormationBytitreContainingIgnoreCase(titre);
    }
}
