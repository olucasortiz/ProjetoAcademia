package com.treinos.treinos.repositories;

import com.treinos.treinos.models.User;
import com.treinos.treinos.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    List<Workout> findByTrainer(User user);


}
