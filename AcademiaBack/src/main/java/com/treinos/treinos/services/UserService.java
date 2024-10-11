package com.treinos.treinos.services;

import com.treinos.treinos.models.User;
import com.treinos.treinos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        // Metodo temporário para gerar senha codificada
        public void encodePassword(String rawPassword) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("Senha codificada: " + encodedPassword);
        }

        /*public void encodePasswordsForAllUsers() {
            List<User> users = userRepository.findAll();

            for (User user : users) {
                String currentPassword = user.getPassword();

                // Verifica se a senha já está criptografada em BCrypt
                if (!currentPassword.startsWith("$2a$") && !currentPassword.startsWith("$2b$")) {
                    // Codifica a senha
                    String encodedPassword = passwordEncoder.encode(currentPassword);

                    // Atualiza a senha no banco de dados
                    user.setPassword(encodedPassword);
                    userRepository.save(user);
                }
            }
        }*/

        public User createUser(User user) {
            if(userRepository.findByEmail(user.getEmail()).isPresent())
                throw new RuntimeException("Email ja cadastrado");

            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        public List<User> findUsersByRole(String role) {
            return userRepository.findByRole(role);
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
                updatedUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                updatedUser.setRole(userDetails.getRole());
                updatedUser.setCellphone(userDetails.getCellphone());
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
