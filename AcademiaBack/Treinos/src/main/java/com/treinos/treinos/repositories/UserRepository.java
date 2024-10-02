package com.treinos.treinos.repositories;
import com.treinos.treinos.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<Object> findByEmail(String email);

=======
public interface UserRepository extends JpaRepository<User, Integer> {
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
}
