package com.treinos.treinos.repositories;

import com.treinos.treinos.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
