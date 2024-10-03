package com.treinos.treinos.services;


import com.treinos.treinos.models.Exercise;
import com.treinos.treinos.repositories.ExerciseRepository;
import com.treinos.treinos.services.ExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    private Exercise exercise;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exercise = new Exercise("Push-up", "A basic push-up exercise");

    }

    @Test
    void testCreateExercise() {
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        Exercise createdExercise = exerciseService.createExercise(exercise);

        assertNotNull(createdExercise);
        assertEquals(exercise.getName(), createdExercise.getName());
        verify(exerciseRepository, times(1)).save(exercise);
    }

    @Test
    void testGetExerciseById() {
        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));

        Exercise foundExercise = exerciseService.findExerciseById(1);

        assertNotNull(foundExercise);
        assertEquals(exercise.getName(), foundExercise.getName());
        verify(exerciseRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateExercise() {
        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        Exercise updatedExercise = exerciseService.updateExercise(1, exercise);

        assertNotNull(updatedExercise);
        assertEquals(exercise.getName(), updatedExercise.getName());
        verify(exerciseRepository, times(1)).save(exercise);
    }

    @Test
    void testDeleteExercise() {
        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));

        exerciseService.deleteExercise(1);

        verify(exerciseRepository, times(1)).deleteById(1);
    }
    @Test
    public void testCreateExerciseWithDuplicateName() {
        // Simula um exercício com nome duplicado
        Exercise exercise = new Exercise("Squat", "Exercise for legs");

        // Simula o comportamento esperado de encontrar um nome duplicado
        when(exerciseRepository.findByName("Squat")).thenReturn(Optional.of(exercise));

        // Verifica se a exceção é lançada ao tentar criar um exercício com nome duplicado
        assertThrows(RuntimeException.class, () -> {
            exerciseService.createExercise(exercise);
        });
    }

}
