package com.example.Streakify.controller;

import com.example.Streakify.dto.StreakResponseDTO;
import com.example.Streakify.service.StreakService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habits")
public class StreakController {

    private final StreakService streakService;

    public StreakController(StreakService streakService) {
        this.streakService = streakService;
    }

    @GetMapping("/{habitId}/streak")
    public StreakResponseDTO getStreak(@PathVariable Long habitId) {
        return streakService.getStreak(habitId);
    }
}