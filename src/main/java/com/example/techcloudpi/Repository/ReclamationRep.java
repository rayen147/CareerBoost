package com.example.techcloudpi.Repository;

import com.example.techcloudpi.entity.Reclamation;
import com.example.techcloudpi.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRep extends JpaRepository<Reclamation,Long> {
    List<Reclamation> findByUserIdUser(Long idUser);
}
