package com.treinos.treinos.models;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(nullable = false, unique = true)//vai garantir qe o email seja unico
    private String email;
    private String password;
    private String role;
    private String cellphone;

    public User() {
    }

    public User(Integer id, String cellphone, String role, String password, String email, String nome) {
        this.id = id;
        this.cellphone = cellphone;
        this.role = role;
        this.password = password;
        this.email = email;
        this.name = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
