package com.example.techcloudpi.Repository;

import com.example.techcloudpi.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRep extends JpaRepository<Reponse,Long> {
}
