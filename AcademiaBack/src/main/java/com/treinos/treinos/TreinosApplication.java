package com.treinos.treinos;

import com.treinos.treinos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TreinosApplication{

    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(TreinosApplication.class, args);
    }
/*
    @Override
    public void run(String... args) throws Exception {
        // Codifica todas as senhas na inicialização da aplicação
        userService.encodePasswordsForAllUsers();
    }
*/

}
