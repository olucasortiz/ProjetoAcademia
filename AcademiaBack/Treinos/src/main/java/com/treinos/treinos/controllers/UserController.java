package com.treinos.treinos.controllers;

import com.treinos.treinos.models.Exercise;
import com.treinos.treinos.models.User;
<<<<<<< HEAD
import com.treinos.treinos.services.EmailService;
import com.treinos.treinos.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
=======
import com.treinos.treinos.services.UserService;
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym/user")
public class UserController {
    @Autowired
<<<<<<< HEAD
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "This endpoint retrieves all users in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
=======
    private UserService userService;

>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

<<<<<<< HEAD
    @Operation(summary = "Get a user by ID", description = "This endpoint retrieves a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
=======
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try{
            User user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

<<<<<<< HEAD
    @Operation(summary = "Create a new user", description = "This endpoint creates a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try{
            User createdUser = userService.createUser(user);
            String email = user.getEmail();
            String subject = "Bem vindo ao Sistema de Treinos";
            String message = "Olá "+ user.getName()+",\n\n"+
                    "Bem vindo ao nosso sistema de treinos. Estamos felizes em tê-lo conosco";
            emailService.sendSimpleMessage(email, subject, message);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

    }

    @Operation(summary = "Update a User", description = "This endpoint updates a user's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
=======
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try{
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
<<<<<<< HEAD

    @Operation(summary = "Delete a user", description = "This endpoint deletes a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
=======
>>>>>>> e4b9b1d18d73ace7054103483583cfad4a824eb6
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
