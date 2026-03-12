package com.example.Streakify.controller;

import com.example.Streakify.dto.HabitRequestDTO;
import com.example.Streakify.dto.HabitResponseDTO;

import com.example.Streakify.service.HabitService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HabitController {
    private final HabitService habitService;
    public HabitController(HabitService habitService){
        this.habitService=habitService;
    }

    @PostMapping("/habits")
    public HabitResponseDTO createHabit(@Valid @RequestBody HabitRequestDTO dto){
        return habitService.createHabit(dto);
    }
    @GetMapping("/users/{userId}/habits")
    public List<HabitResponseDTO> getHabitByUser(@PathVariable Long userId){
        return habitService.getHabitByUser(userId);
    }
    @DeleteMapping("/habits/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return ResponseEntity.ok("Habit deleted ");
    }
}
