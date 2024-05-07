package com.example.techcloud.repository;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Going;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IGoingRepository extends JpaRepository<Going, Long> {
    List<Going> findAllByEvent(Evenement event);
}
