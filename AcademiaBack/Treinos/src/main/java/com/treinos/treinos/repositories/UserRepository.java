package com.treinos.treinos.repositories;
import com.treinos.treinos.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<Object> findByEmail(String email);

}
