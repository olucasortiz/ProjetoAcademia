package com.treinos.treinos.services;

import com.treinos.treinos.models.User;
import com.treinos.treinos.models.Workout;
import com.treinos.treinos.repositories.UserRepository;
import com.treinos.treinos.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    // Cria um novo treino e associa a um treinador
<<<<<<< HEAD
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }


=======
    public Workout createWorkout(Workout workout, Integer trainerId) {
        return workoutRepository.save(workout);
    }

>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    // Encontra um treino pelo seu ID
    public Workout findWorkoutById(Integer id) {
        Optional<Workout> workout = workoutRepository.findById(id);
        if(workout.isPresent()) {
            return workout.get();
        }
        else throw new RuntimeException("Workout not found");
    }

    // Encontra todos os treinos associados a um usuário
    public List<Workout> findWorkoutsByUser(Integer userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return workoutRepository.findByTrainer(user);
        }
        throw new RuntimeException("User not found");
    }

    // Atualiza os dados de um treino
    public Workout updateWorkout(Integer id, Workout workoutDetails) {
        // Busca o treino pelo ID. Se não for encontrado, lança uma exceção ou retorna null.
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if(workoutOptional.isPresent()) {
            Workout workout = workoutOptional.get();
            if(workoutDetails.getName() != null)
                workout.setName(workoutDetails.getName());
            if(workoutDetails.getDescription() != null)
                workout.setDescription(workoutDetails.getDescription());
            return workoutRepository.save(workout);
        }
        else throw new RuntimeException("Workout not found");
    }

<<<<<<< HEAD
    public List<Workout> findAllWorkouts() {
        return workoutRepository.findAll();
    }

=======
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    // Deleta um treino pelo seu ID
    public void deleteWorkout(Integer id) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
       if(workoutOptional.isPresent()) {
           workoutRepository.deleteById(id);
       }
       else throw new RuntimeException("Workout not found");
    }
}
