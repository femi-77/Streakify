package com.example.Streakify.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class HabitLogGetDTO {
    private Long id;
    private LocalDate logDate;
    private boolean completed;
}
