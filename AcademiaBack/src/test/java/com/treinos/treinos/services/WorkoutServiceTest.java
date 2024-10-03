package com.treinos.treinos.services;

import com.treinos.treinos.models.Workout;
import com.treinos.treinos.repositories.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    @Mock//cria uma simulação da classe workout repository para ser usada nos testes.
    private WorkoutRepository workoutRepository;

    @InjectMocks//injeta os mocks criados, no caso workout repository na instancia real da classe workout service
    private WorkoutService workoutService;

    //configura o ambiente antes de cada teste, inicializando os mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllWorkouts() {
        Workout workout1 = new Workout();
        workout1.setId(1);
        workout1.setName("Treino A");

        Workout workout2 = new Workout();
        workout2.setId(2);
        workout2.setName("Treino B");

        when(workoutRepository.findAll()).thenReturn(Arrays.asList(workout1, workout2));

        List<Workout> workouts = workoutService.findAllWorkouts();

        assertNotNull(workouts);
        assertEquals(2, workouts.size());
        assertEquals("Treino A", workouts.get(0).getName());
        assertEquals("Treino B", workouts.get(1).getName());
    }

    @Test
    void testFindWorkoutById_Success() {
        Workout workout = new Workout();
        workout.setId(1);
        workout.setName("Treino A");

        when(workoutRepository.findById(1)).thenReturn(Optional.of(workout));

        Workout foundWorkout = workoutService.findWorkoutById(1);

        assertNotNull(foundWorkout);
        assertEquals(1, foundWorkout.getId());
        assertEquals("Treino A", foundWorkout.getName());
    }

    @Test
    void testFindWorkoutById_NotFound() {
        when(workoutRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> workoutService.findWorkoutById(1));
    }

    @Test
    void testCreateWorkout() {
        Workout workout = new Workout();
        workout.setName("Novo Treino");

        when(workoutRepository.save(workout)).thenReturn(workout);

        Workout createdWorkout = workoutService.createWorkout(workout);

        assertNotNull(createdWorkout);
        assertEquals("Novo Treino", createdWorkout.getName());
    }

    @Test
    void testUpdateWorkout() {
        Workout workout = new Workout();
        workout.setId(1);
        workout.setName("Treino Atualizado");

        when(workoutRepository.findById(1)).thenReturn(Optional.of(workout));
        when(workoutRepository.save(workout)).thenReturn(workout);

        Workout updatedWorkout = workoutService.updateWorkout(1, workout);

        assertNotNull(updatedWorkout);
        assertEquals("Treino Atualizado", updatedWorkout.getName());
    }

    @Test
    void testDeleteWorkout() {
        // Mock do retorno de findById
        Workout workout = new Workout();
        workout.setId(1);

        // Simula o comportamento do findById retornando o workout
        when(workoutRepository.findById(1)).thenReturn(Optional.of(workout));

        // Executa o metodo deleteWorkout
        workoutService.deleteWorkout(1);

        // Verifica se o metodo deleteById foi chamado corretamente com o ID 1
        verify(workoutRepository, times(1)).deleteById(1);
    }

}
