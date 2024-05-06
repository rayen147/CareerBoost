package com.example.CareerBoost.ServiceInterface;



import com.example.CareerBoost.Entity.Certificat;

import java.util.List;
import java.util.Map;

public interface ICertificatService {
    List<Certificat> retrieveAllCertificat();
    Certificat addCertificat(Certificat certificat );
    Certificat updateCertificat (Certificat certificat);
    Certificat retrieveCertificat ( Long id);
    void removeCertificat ( Long id);
    Map<String, Integer> getCertificatCountByModule();

}
