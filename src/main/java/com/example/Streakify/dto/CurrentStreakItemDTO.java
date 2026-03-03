package com.example.Streakify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentStreakItemDTO {
    private String habitName;
    private int currentStreak;
    private int longestStreak;
}