package com.treinos.treinos.repositories;

import com.treinos.treinos.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findByName(String name);
}
