package com.treinos.treinos.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
<<<<<<< HEAD
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
=======

    private String name;
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User trainer;

    @ManyToMany
    @JoinTable(
            name = "workout_exercise", // nome da tabela de junção ao bd
            joinColumns = @JoinColumn(name="workout_id"),//chave estrangeira de workout
            inverseJoinColumns = @JoinColumn(name = "exercise_id")//chave estrangeira de exercise
    )
    private List<Exercise> exercises;

    public Workout() {
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Workout(String name, String description, User trainer) {
        this.name = name;
        this.description = description;
        this.trainer = trainer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
