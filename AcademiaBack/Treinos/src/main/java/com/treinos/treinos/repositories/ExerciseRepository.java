package com.treinos.treinos.repositories;

import com.treinos.treinos.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findByName(String name);
=======
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
}
