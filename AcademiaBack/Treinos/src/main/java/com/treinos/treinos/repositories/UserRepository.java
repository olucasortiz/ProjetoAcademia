package com.treinos.treinos.repositories;
import com.treinos.treinos.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
