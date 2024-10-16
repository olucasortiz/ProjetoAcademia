package com.treinos.treinos.controllers;

import com.treinos.treinos.dto.LoginRequest;
import com.treinos.treinos.dto.RegisterRequest;
import com.treinos.treinos.models.User;
import com.treinos.treinos.repositories.UserRepository;
import com.treinos.treinos.services.CustomUserDetails;
import com.treinos.treinos.services.CustomUserDetailsService;
import com.treinos.treinos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/auth")  // Define a URL base para autenticação
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return ResponseEntity.ok().body(Map.of("message", "Autenticação bem-sucedida", "user", userDetails.getUser()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Autenticação falhou"));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User newUser = new User();
            newUser.setName(registerRequest.getNome());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerRequest.getSenha()));
            newUser.setCellphone(registerRequest.getCellphone());
            newUser.setRole("ROLE_USER");  // Define o role padrão

            // Salva o usuário no banco de dados
            userService.createUser(newUser);

            return ResponseEntity.ok().body(Map.of("message", "Registro efetuado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao registrar usuário, tente novamente."));
        }
    }

}
