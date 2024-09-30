package com.treinos.treinos.controllers;

import com.treinos.treinos.models.Exercise;
import com.treinos.treinos.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym/exercises")

public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAllExercises();
        return ResponseEntity.ok(exercises);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Integer id) {
        try{
            Exercise exercise = exerciseService.findExerciseById(id);
            return ResponseEntity.ok(exercise);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Integer id, @RequestBody Exercise exercise) {
        try{
            Exercise updatedExercise = exerciseService.updateExercise(id, exercise);
            return ResponseEntity.ok(updatedExercise);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> deleteExercise(@PathVariable Integer id) {
        try{
            exerciseService.deleteExercise(id);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
