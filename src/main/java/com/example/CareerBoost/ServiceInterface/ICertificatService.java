package com.example.CareerBoost.ServiceInterface;



import com.example.CareerBoost.Entity.Certificat;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15

public interface ICertificatService {
    List<Certificat> retrieveAllCertificat();
    Certificat addCertificat(Certificat certificat );
    Certificat updateCertificat (Certificat certificat);
    Certificat retrieveCertificat ( Long id);
    void removeCertificat ( Long id);
<<<<<<< HEAD
    Map<String, Integer> getCertificatCountByModule();
=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15

}
