package com.treinos.treinos.models;

import jakarta.persistence.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Entity
public class Exercise{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @ManyToMany(mappedBy = "exercises") //indica que a tabela de junção é controlada por workout
    private List<Workout> workouts;
    public Exercise() {
    }

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
