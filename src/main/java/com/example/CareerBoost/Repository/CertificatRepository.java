package com.example.CareerBoost.Repository;


import com.example.CareerBoost.Entity.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificatRepository extends JpaRepository<Certificat,Long> {
    @Query("SELECT c.moduleFormation.titre, COUNT(c) FROM Certificat c GROUP BY c.moduleFormation.titre")
    List<Object[]> countByModule();
=======

public interface CertificatRepository extends JpaRepository<Certificat,Long> {
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
}
