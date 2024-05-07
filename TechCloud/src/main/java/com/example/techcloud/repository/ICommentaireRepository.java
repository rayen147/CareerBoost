package com.example.techcloud.repository;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentaireRepository extends JpaRepository<Commentaire,Long> {
    @Query("SELECT p.Evenement.id, COUNT(p.id) FROM Commentaire p GROUP BY p.Evenement.id")
    List<Object[]> countParticipationsByEvent();

}
