package com.example.Streakify.controller;

import com.example.Streakify.dto.DashboardResponseDTO;
import com.example.Streakify.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{userId}/dashboard")
    public DashboardResponseDTO getDashboard(@PathVariable Long userId) {
        return dashboardService.getDashboard(userId);
    }
}