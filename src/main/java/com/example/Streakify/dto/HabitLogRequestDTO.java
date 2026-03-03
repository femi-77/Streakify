package com.example.Streakify.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HabitLogRequestDTO {

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Completion status is required")
    private boolean completed;
}
