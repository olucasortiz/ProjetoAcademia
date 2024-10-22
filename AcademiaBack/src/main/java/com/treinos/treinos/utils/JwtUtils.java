package com.treinos.treinos.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "mySecretKey"; // Altere para uma chave mais segura em produção
    private final long jwtExpirationMs = 86400000;  // Token expira em 24 horas

    // Gera o token JWT usando o nome de usuário
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())  // Data de emissão
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))  // Data de expiração
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  // Assina o token com o algoritmo HS512
                .compact();  // Cria o token JWT
    }

    // Extrai o nome de usuário do token JWT
    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)  // Verifica a assinatura usando a chave secreta
                .parseClaimsJws(token)  // Decodifica o token
                .getBody()  // Obtém o corpo do token
                .getSubject();  // Retorna o nome de usuário (subject)
    }
}
