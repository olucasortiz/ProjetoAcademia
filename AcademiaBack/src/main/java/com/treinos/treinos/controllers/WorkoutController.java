package com.treinos.treinos.controllers;

import com.treinos.treinos.models.User;
import com.treinos.treinos.models.Workout;
import com.treinos.treinos.services.CustomUserDetails;
import com.treinos.treinos.services.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;


    @Operation(summary = "Get workouts for the logged-in user", description = "This endpoint retrieves workouts for the logged-in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workouts retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/my-workouts")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_TRAINER')")
    public ResponseEntity<List<Workout>> getUserWorkouts(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser(); // Acessa o usuário encapsulado
        List<Workout> workouts = workoutService.findWorkoutsByUser(user.getId());
        return ResponseEntity.ok(workouts);
    }

    @Operation(summary = "Get all workouts", description = "This endpoint retrieves all workouts in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workouts retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService .findAllWorkouts();
         return ResponseEntity.ok(workouts);
    }

    @Operation(summary = "Get a workout by ID", description = "This endpoint retrieves a workout by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout found"),
            @ApiResponse(responseCode = "404", description = "Workout not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Integer id) {
        try {
            Workout workout = workoutService.findWorkoutById(id);
            return ResponseEntity.ok(workout);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create a new workout", description = "This endpoint creates a new workout in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ROLE_TRAINER')") //apenas os trainers podem criar treinos
    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        Workout createdWorkout = workoutService.createWorkout(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }

    @Operation(summary = "Update a workout", description = "This endpoint updates a workout's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Workout not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Integer id, @RequestBody Workout workout) {
        try {
            Workout updatedWorkout = workoutService.updateWorkout(id, workout);
            return ResponseEntity.ok(updatedWorkout);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Delete a workout", description = "This endpoint deletes a workout by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Workout not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Integer id) {
        try {
            workoutService.deleteWorkout(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
