package com.treinos.treinos.services;


import com.treinos.treinos.models.User;
import jakarta.validation.constraints.Email;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final EmailService emailService;
    private final UserService userService;

    public NotificationService(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 9 * * ?")//vai agendar para todos os dias as 9h
    public void sendDailyWorkoutReminders(){
        List<User> users = userService.findAllUsers();
        for(User user : users){
            String email = user.getEmail();
            String message = "Ola" + user.getName() +"Nao se esqueça do seu treino de hoje!";
            emailService.sendSimpleMessage(email,"Lembrete de Treino",message);
        }
    }
}
