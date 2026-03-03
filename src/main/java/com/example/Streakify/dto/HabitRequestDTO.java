package com.example.Streakify.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HabitRequestDTO {

    @NotBlank(message = "Habit name is required")
    private String name;

    @Min(value = 1, message = "Target must be at least 1 day")
    @Max(value = 7,message = "Maximum 7 days only")
    @NotNull(message = "Target days required")
    private int targetDaysPerWeek;

    @NotNull(message = "User ID is required")
    private Long userId;
}
