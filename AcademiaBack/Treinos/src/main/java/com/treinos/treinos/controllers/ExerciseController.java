package com.treinos.treinos.controllers;

import com.treinos.treinos.models.Exercise;
import com.treinos.treinos.services.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Get all exercises", description = "This endpoint retrieves all exercises in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercises retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @Operation(summary = "Get an exercise by ID", description = "This endpoint retrieves an exercise by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise found"),
            @ApiResponse(responseCode = "404", description = "Exercise not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Integer id) {
        try{
            Exercise exercise = exerciseService.findExerciseById(id);
            return ResponseEntity.ok(exercise);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create a new exercise", description = "This endpoint creates a new exercise in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercise successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }


    @Operation(summary = "Update an exercise", description = "This endpoint updates an exercise's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Exercise not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

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


    @Operation(summary = "Delete an exercise", description = "This endpoint deletes an exercise by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Exercise not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
