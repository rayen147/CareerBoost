package com.example.testspring.repository;

import com.example.testspring.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRepo extends JpaRepository<Exercise,Long> {
}
