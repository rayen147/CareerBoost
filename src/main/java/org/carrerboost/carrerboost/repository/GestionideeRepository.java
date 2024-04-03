package org.carrerboost.carrerboost.repository;

import org.carrerboost.carrerboost.entity.Gestionidee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GestionideeRepository   extends JpaRepository<Gestionidee, Long> {

    List<Gestionidee> findByDescriptionContainingIgnoreCase(String description);

        List<Gestionidee> findByTitleContainingIgnoreCase(String title);
    }

