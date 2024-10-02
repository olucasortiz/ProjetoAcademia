package com.treinos.treinos.services;

import com.treinos.treinos.models.Exercise;
import com.treinos.treinos.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    // Cria um novo exercício
    public Exercise createExercise(Exercise exercise) {
        if(exerciseRepository.findByName(exercise.getName()).isPresent())
            throw new RuntimeException("Exercise already exists");
        return exerciseRepository.save(exercise);
    }

    // Encontra um exercício pelo seu ID
    public Exercise findExerciseById(Integer id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isPresent()) {
            return exerciseOptional.get();
        }
        else throw new RuntimeException("Exercise not found");
    }

    // Encontra todos os exercícios
    public List<Exercise> findAllExercises() {
        // Busca e retorna todos os exercícios registrados no banco de dados.
        // Retorna uma lista de todos os exercícios encontrados.
        return exerciseRepository.findAll();
    }

    // Atualiza os dados de um exercício
    public Exercise updateExercise(Integer id, Exercise exerciseDetails) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isPresent()) {
            Exercise exercise = exerciseOptional.get();
            if(exerciseDetails.getName() != null) {
                exercise.setName(exerciseDetails.getName());
            }
            if(exerciseDetails.getDescription() != null) {
                exercise.setDescription(exerciseDetails.getDescription());
            }
            return exerciseRepository.save(exercise);
        }
        else throw new RuntimeException("Exercise not found");
    }

    // Deleta um exercício pelo seu ID
    public void deleteExercise(Integer id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isPresent()) {
            exerciseRepository.deleteById(id);
        }
        else throw new RuntimeException("Exercise not found");
    }
}
