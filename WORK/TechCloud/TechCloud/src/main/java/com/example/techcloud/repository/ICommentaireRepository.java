package com.example.techcloud.repository;

import com.example.techcloud.entity.Commentaire;
import com.example.techcloud.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentaireRepository extends JpaRepository<Commentaire,Long> {
}
