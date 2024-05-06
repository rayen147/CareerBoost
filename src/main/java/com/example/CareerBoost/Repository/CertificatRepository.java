package com.example.CareerBoost.Repository;


import com.example.CareerBoost.Entity.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificatRepository extends JpaRepository<Certificat,Long> {
    @Query("SELECT c.moduleFormation.titre, COUNT(c) FROM Certificat c GROUP BY c.moduleFormation.titre")
    List<Object[]> countByModule();
}
