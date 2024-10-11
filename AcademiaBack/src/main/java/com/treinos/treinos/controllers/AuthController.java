package com.treinos.treinos.controllers;

import com.treinos.treinos.dto.LoginRequest;
import com.treinos.treinos.dto.RegisterRequest;
import com.treinos.treinos.repositories.UserRepository;
import com.treinos.treinos.services.CustomUserDetailsService;
import jakarta.annotation.PostConstruct;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        return ResponseEntity.ok("Autenticado");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest){
        String name = registerRequest.getNome();
        String email = registerRequest.getEmail();
        String password = registerRequest.getSenha();
        String cellphone = registerRequest.getCellphone();

        return ResponseEntity.ok("Registro efetuado");
    }
}
