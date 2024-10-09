package com.treinos.treinos.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Novos campos
    private String target;     // Grupo muscular alvo
    private String bodyPart;   // Parte do corpo
    private String equipment;  // Equipamento usado

    @ManyToMany(mappedBy = "exercises") //indica que a tabela de junção é controlada por workout
    private List<Workout> workouts;

    // Construtor vazio
    public Exercise() {
    }

    // Construtor completo
    public Exercise(String name, String description, String target, String bodyPart, String equipment) {
        this.name = name;
        this.description = description;
        this.target = target;
        this.bodyPart = bodyPart;
        this.equipment = equipment;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
