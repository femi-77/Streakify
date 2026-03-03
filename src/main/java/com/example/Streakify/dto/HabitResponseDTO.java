package com.example.Streakify.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class HabitResponseDTO {
    private Long id;
    private String name;
    private int targetDaysPerWeek;
}
