package com.example.CareerBoost.ServiceImpl;


import com.example.CareerBoost.Entity.Certificat;
import com.example.CareerBoost.Repository.CertificatRepository;
import com.example.CareerBoost.ServiceInterface.ICertificatService;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15

@Service
@AllArgsConstructor
public class CertificatServiceImpl implements ICertificatService {
    CertificatRepository certificatRepository;

    @Override
    public List<Certificat> retrieveAllCertificat() {
        return certificatRepository.findAll();
    }

    @Override
    public Certificat addCertificat(@NotNull @Valid Certificat certificat) {
        return certificatRepository.save(certificat);
    }
//@Valid est utilisée pour activer la validation des contraintes définies dans l'entité Certificat
    @Override
    public Certificat updateCertificat(@NotNull @Valid Certificat certificat) {
        return certificatRepository.save(certificat);
    }

    @Override
    public Certificat retrieveCertificat(Long id) {
        return certificatRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCertificat(Long id) {
        certificatRepository.deleteById(id);

    }
<<<<<<< HEAD
    public Map<String, Integer> getCertificatCountByModule() {
        Map<String, Integer> moduleCounts = new HashMap<>();
        List<Object[]> counts = certificatRepository.countByModule();
        for (Object[] row : counts) {
            moduleCounts.put((String) row[0], ((Number) row[1]).intValue());
        }
        return moduleCounts;
    }
=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
}
