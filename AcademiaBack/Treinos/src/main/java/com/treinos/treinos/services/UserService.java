package com.treinos.treinos.services;

import com.treinos.treinos.models.User;
import com.treinos.treinos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;

        public User createUser(User user) {
            return userRepository.save(user);
        }

        // Encontra um usuário pelo seu ID
        public User findUserById(Integer id) {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                return user.get();
            }
            else throw new RuntimeException("User not found");
        }

        // Encontra todos os usuários
        public List<User> findAllUsers() {
            // Busca e retorna todos os usuários registrados no banco de dados.
            // Retorna uma lista de todos os usuários encontrados.
            return userRepository.findAll();
        }

        // Atualiza os dados de um usuário
        public User updateUser(Integer id, User userDetails) {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                User updatedUser = user.get();
                updatedUser.setName(userDetails.getName());
                updatedUser.setEmail(userDetails.getEmail());
                updatedUser.setPassword(userDetails.getPassword());
                return userRepository.save(updatedUser);
            }
            else throw new RuntimeException("User not found");
        }

        // Deleta um usuário pelo seu ID
        public void deleteUser(Integer id) {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                userRepository.delete(user.get());
            }
            else throw new RuntimeException("User not found");
        }
}
