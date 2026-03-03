package com.example.Streakify.controller;

import com.example.Streakify.dto.UserRequestDTO;
import com.example.Streakify.dto.UserResponseDTO;
import com.example.Streakify.model.User;
import com.example.Streakify.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto){
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
