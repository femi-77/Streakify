package com.example.Streakify.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
   // 👈 HERE
public class DashboardResponseDTO {

    private int totalHabits;
    private long activeHabits;
    private int completedToday;
    private List<CurrentStreakItemDTO> currentStreaks;
    private int consistencyScore;

}