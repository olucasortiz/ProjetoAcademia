package com.treinos.treinos.services;

import com.treinos.treinos.models.User;
import com.treinos.treinos.repositories.UserRepository;
import com.treinos.treinos.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Certifique-se de que os parâmetros do User estão na ordem correta
        user = new User(1, "123456789", "ROLE_USER", "senhaSegura123", "lucas.santos@example.com", "Lucas Santos");
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getEmail(), createdUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1);

        assertNotNull(foundUser);
        assertEquals(user.getEmail(), foundUser.getEmail());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(1, user);

        assertNotNull(updatedUser);
        assertEquals(user.getEmail(), updatedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void testCreateUserWithDuplicateEmail() {
        // Simula o comportamento esperado de encontrar um email duplicado
        when(userRepository.findByEmail("lucas.santos@example.com")).thenReturn(Optional.of(user));

        // Verifica se a exceção é lançada ao tentar criar um usuário com e-mail duplicado
        assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(0)).save(user); // Certifica-se de que não salva o usuário duplicado
    }
}
