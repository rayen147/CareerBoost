package com.example.techcloud.repository;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikesRepository extends JpaRepository <Likes, Long> {
    List<Likes> findAllByEvent(Evenement event);
}
