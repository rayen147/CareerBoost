package com.example.CareerBoost.ServiceImpl;


import com.example.CareerBoost.Entity.ModuleFormation;
import com.example.CareerBoost.Repository.ModuleFormationRepository;
import com.example.CareerBoost.ServiceInterface.IModuleFormationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleFormationServiceImpl implements IModuleFormationService {
    ModuleFormationRepository moduleFormationRepository;

    @Override
    public List<ModuleFormation> retrieveAllModuleFormations() {
        return moduleFormationRepository.findAll();
    }


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
